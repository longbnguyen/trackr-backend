package de.techdev.trackr.domain.project;

import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * @author Moritz Schulze
 */
@Projection(types = BillableTime.class, name = "withProject")
public interface BillableTimeWithProjectProjection {
    Long getId();

    Integer getVersion();

    Project getProject();

    Date getDate();

    Integer getMinutes();
}
