package uk.gov.justice.laadces.deltaconcor.report;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

class ConcorChangeCountsFieldComparator implements Comparator<String> {
    static final ConcorChangeCountsFieldComparator INSTANCE = new ConcorChangeCountsFieldComparator();

    private final List<String> fieldOrder;

    private ConcorChangeCountsFieldComparator() {
        this.fieldOrder = new ArrayList<>();
        for (Field field : ConcorChangeCounts.class.getDeclaredFields()) {
            fieldOrder.add(field.getName().toLowerCase(Locale.ROOT));
        }
    }

    @Override
    public int compare(String o1, String o2) {
        return fieldOrder.indexOf(o1.toLowerCase(Locale.ROOT)) - fieldOrder.indexOf(o2.toLowerCase(Locale.ROOT));
    }
}
