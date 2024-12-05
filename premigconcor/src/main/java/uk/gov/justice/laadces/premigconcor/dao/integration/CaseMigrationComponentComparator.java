package uk.gov.justice.laadces.premigconcor.dao.integration;

import java.lang.reflect.RecordComponent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * This exists to provide a consistent ordering of fields in the Change class when OpenCSV outputs them as CSV rows.
 * Will also sort unknown fields to the end (used by ChangeLog).
 */
class CaseMigrationComponentComparator implements Comparator<String> {
    static final CaseMigrationComponentComparator INSTANCE = new CaseMigrationComponentComparator();

    private final List<String> fieldOrder;

    private CaseMigrationComponentComparator() {
        this.fieldOrder = new ArrayList<>();
        for (RecordComponent component : CaseMigration.class.getRecordComponents()) {
            fieldOrder.add(component.getName().toLowerCase(Locale.ROOT));
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
