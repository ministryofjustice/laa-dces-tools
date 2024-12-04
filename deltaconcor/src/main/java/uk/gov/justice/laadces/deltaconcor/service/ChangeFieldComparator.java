package uk.gov.justice.laadces.deltaconcor.service;

import uk.gov.justice.laadces.deltaconcor.report.Change;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * This exists to provide a consistent ordering of fields in the Change class when OpenCSV outputs them as CSV rows.
 * Will also sort unknown fields to the end (used by ChangeLog).
 */
class ChangeFieldComparator implements Comparator<String> {
    static final ChangeFieldComparator INSTANCE = new ChangeFieldComparator();

    private final List<String> fieldOrder;

    private ChangeFieldComparator() {
        this.fieldOrder = new ArrayList<>();
        for (Field field : Change.class.getDeclaredFields()) {
            fieldOrder.add(field.getName().toLowerCase(Locale.ROOT));
        }
    }

    @Override
    public int compare(String o1, String o2) {
        int leftIndex = fieldOrder.indexOf(o1.toLowerCase(Locale.ROOT));
        int rightIndex = fieldOrder.indexOf(o2.toLowerCase(Locale.ROOT));
        if (leftIndex != -1 && rightIndex != -1) {
            return leftIndex - rightIndex;
        } else if (leftIndex != -1) {
            return -1; // unknown fields go to the end
        } else if (rightIndex != -1) {
            return 1; // unknown fields go to the end
        } else {
            // unknown fields are sorted by name
            return String.CASE_INSENSITIVE_ORDER.compare(o1, o2);
        }
    }
}
