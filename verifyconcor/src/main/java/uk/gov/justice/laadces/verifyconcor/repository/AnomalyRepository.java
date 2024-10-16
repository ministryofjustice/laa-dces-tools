package uk.gov.justice.laadces.verifyconcor.repository;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple repository to store anomalies found during verification.
 * <p>
 * This is a Spring component so it can be injected into other classes.
 */
@Component
@Getter
public class AnomalyRepository {
    private List<Anomaly> diffs;
    private List<Anomaly> violations;

    public AnomalyRepository() {
        clear();
    }

    public void clear() {
        this.diffs = new ArrayList<>();
        this.violations = new ArrayList<>();
    }

    public void addDiff(Long id, List<String> messages) {
        diffs.add(new Anomaly(id, messages));
    }

    public void addViolation(Long id, List<String> messages) {
        violations.add(new Anomaly(id, messages));
    }

    public record Anomaly(Long id, List<String> messages) {
    }
}
