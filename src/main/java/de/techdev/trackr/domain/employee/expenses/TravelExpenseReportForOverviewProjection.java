package de.techdev.trackr.domain.employee.expenses;

import de.techdev.trackr.domain.employee.Employee;
import org.springframework.data.rest.core.config.Projection;

/**
 * @author Moritz Schulze
 */
@Projection(types = TravelExpenseReport.class, name = "overview")
public interface TravelExpenseReportForOverviewProjection {
    Long getId();

    Employee getEmployee();

    TravelExpenseReportStatus getStatus();
}
