package uk.gov.justice.laadces.verifyconcor.repository;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple in-memory repository for statistics.
 * <p>
 * Not currently implemented.
 */
@Component
public class StatisticRepository {
    private final static String[] STATUSES = {"ACTIVE", "REPLACED", "SENT"};

    private LocalDate prev;
    private GroupStat totals;

    int currRecsPerDay, minRecsPerDay, maxRecsPerDay, totalRecsPerDay;
    long currSizePerDay, minSizePerDay, maxSizePerDay, totalSizePerDay;

    public StatisticRepository() {
        clear();
    }

    public void clear() {
        prev = LocalDate.of(1970, 1, 1);
        currRecsPerDay = minRecsPerDay = maxRecsPerDay = totalRecsPerDay = 0;
        currSizePerDay = minSizePerDay = maxSizePerDay = totalSizePerDay = 0L;
        totals = new GroupStat();
    }

    private void endDay() {
        minRecsPerDay = Math.min(minRecsPerDay, currRecsPerDay);
        maxRecsPerDay = Math.max(maxRecsPerDay, currRecsPerDay);
        totalRecsPerDay += currRecsPerDay;
        currRecsPerDay = 0;
        minSizePerDay = Math.min(minSizePerDay, currSizePerDay);
        maxSizePerDay = Math.max(maxSizePerDay, currSizePerDay);
        totalSizePerDay += currSizePerDay;
        currSizePerDay = 0L;
    }

    public void addStat(final ConcorContribution dto) {
        // not currently implemented.
        LocalDate curr = dto.dateCreated().toLocalDate();
        BaseStat totStat = totals.totals;
        BaseStat dayStat = totals.byDayOfWeek.get(curr.getDayOfWeek());
        BaseStat monStat = totals.byMonth.get(curr.getMonth());
        BaseStat stsStat = totals.byStatus.get(dto.status());

        if (curr.isAfter(prev)) {
            endDay();
            prev = curr;
            ++totStat.days;
            ++dayStat.days;
            ++monStat.days;
            ++stsStat.days;
        } else if (curr.isBefore(prev)) {
            endDay();
            prev = curr;
            ++totStat.backs;
            ++dayStat.backs;
            ++monStat.backs;
            ++stsStat.backs;
        }
        ++totStat.recs;
        ++dayStat.recs;
        ++monStat.recs;
        ++stsStat.recs;
        ++currRecsPerDay;

        long size = dto.fullXml().length();
        totStat.size += size;
        dayStat.size += size;
        monStat.size += size;
        stsStat.size += size;
        currSizePerDay += size;
    }

    public String toString() {
        return "TOTALS: " + totals.totals.toString() +
                "\nBY DAY: " + totals.byDayOfWeek.toString() +
                "\nBY MONTH: " + totals.byMonth.toString() +
                "\nBY STATUS: " + totals.byStatus.toString() +
                "\nRECS PER DAY: min=" + minRecsPerDay + ", max=" + maxRecsPerDay + ", tot=" + totalRecsPerDay +
                "\nSIZE PER DAY: min=" + minSizePerDay + ", max=" + maxSizePerDay + ", tot=" + totalSizePerDay;
    }

    @Data
    public static class BaseStat {
        private int days;
        private int backs;
        private int recs;
        private long size;
    }

    @Data
    public static class GroupStat {
        private BaseStat totals = new BaseStat();
        private Map<DayOfWeek, BaseStat> byDayOfWeek = new HashMap<>();
        private Map<Month, BaseStat> byMonth = new HashMap<>();
        private Map<String, BaseStat> byStatus = new HashMap<>();

        GroupStat() {
            for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
                byDayOfWeek.put(dayOfWeek, new BaseStat());
            }
            for (Month month : Month.values()) {
                byMonth.put(month, new BaseStat());
            }
            for (String state : STATUSES) {
                byStatus.put(state, new BaseStat());
            }
        }
    }
}
