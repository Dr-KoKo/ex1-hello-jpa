package hellojpa;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class Period {
    private LocalDate startDate;
    private LocalDate endDate;

    protected Period() {
    }

    public Period(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(startDate, period.startDate) && Objects.equals(endDate, period.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
