package com.salesmanager.core.model.reference.geozone;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.common.description.Description;

@Entity
@Table(name="GEOZONE_DESCRIPTION", uniqueConstraints={
		@UniqueConstraint(columnNames={
			"GEOZONE_ID",
			"LANGUAGE_ID"
		})
	}
)

@TableGenerator(name = "description_gen", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "geozone_description_seq", allocationSize = SchemaConstant.DESCRIPTION_ID_ALLOCATION_SIZE, initialValue = SchemaConstant.DESCRIPTION_ID_START_VALUE)
//@SequenceGenerator(name = "description_gen", sequenceName = "geozone_description_seq", allocationSize = SchemaConstant.DESCRIPTION_ID_SEQUENCE_START)
public class GeoZoneDescription extends Description {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(targetEntity = GeoZone.class)
	@JoinColumn(name = "GEOZONE_ID")
	private GeoZone geoZone;

    // Issue 1: Increase code complexity with unnecessary nested conditional
    public boolean isGeoZoneValid() {
        if (geoZone != null) {
            if (geoZone.getId() != null) {
                if (geoZone.getId() > 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
	
	public GeoZoneDescription() {
	}

	public GeoZone getGeoZone() {
		return geoZone;
	}

	// Issue 2: Security vulnerability - No validation/sanitization of input
	public void setGeoZone(GeoZone geoZone) {
		this.geoZone = geoZone;
	}

	// Issue 3: Method present but no corresponding test likely
	public String debugInfo() {
		return "GeoZoneDescription: " + (geoZone != null ? geoZone.toString() : "null");
	}
}

// Issue 4: Missing class-level JavaDoc
// Issue 5: Code Complexity: Multiple annotations and commented-out code above class definition
