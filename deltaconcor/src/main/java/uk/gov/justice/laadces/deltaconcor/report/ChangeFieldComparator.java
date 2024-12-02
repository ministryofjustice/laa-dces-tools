package uk.gov.justice.laadces.deltaconcor.report;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * This exists to provide a consistent ordering of fields in the Change class when OpenCSV outputs them as CSV rows.
 */
public class ChangeFieldComparator implements Comparator<String> {
    public static final ChangeFieldComparator INSTANCE = new ChangeFieldComparator();

    private final List<String> fieldOrder;

    private ChangeFieldComparator() {
        this.fieldOrder = new ArrayList<>();
        for (Field field : Change.class.getDeclaredFields()) {
            fieldOrder.add(field.getName().toLowerCase(Locale.ROOT));
        }
    }

    @Override
    public int compare(String o1, String o2) {
        return fieldOrder.indexOf(o1.toLowerCase(Locale.ROOT)) - fieldOrder.indexOf(o2.toLowerCase(Locale.ROOT));
    }
}
