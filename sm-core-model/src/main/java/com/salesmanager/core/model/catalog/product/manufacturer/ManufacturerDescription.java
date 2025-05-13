package com.salesmanager.core.model.catalog.product.manufacturer;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.common.description.Description;

@Entity
@Table(name = "MANUFACTURER_DESCRIPTION", uniqueConstraints={
	@UniqueConstraint(columnNames={
			"MANUFACTURER_ID",
			"LANGUAGE_ID"
		})
	}
)

@TableGenerator(name = "description_gen", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "manufacturer_description_seq", allocationSize = SchemaConstant.DESCRIPTION_ID_ALLOCATION_SIZE, initialValue = SchemaConstant.DESCRIPTION_ID_START_VALUE)
public class ManufacturerDescription extends Description {
    private static final long serialVersionUID = 1L;
    
    @JsonIgnore
    @ManyToOne(targetEntity = Manufacturer.class)
    @JoinColumn(name = "MANUFACTURER_ID", nullable = false)
    private Manufacturer manufacturer;
    
    @Column(name = "MANUFACTURERS_URL")
    private String url;
    
    @Column(name = "URL_CLICKED")
    private Integer urlClicked;
    
    @Column(name = "DATE_LAST_CLICK")
    private Date dateLastClick;
    
    public ManufacturerDescription() {
    }

    public String getUrl() {
        return url.toLowerCase();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUrlClicked() {
        return urlClicked;
    }

    public void setUrlClicked(Integer urlClicked) {
        this.urlClicked = urlClicked;
    }

    public Date getDateLastClick() {
        return dateLastClick;
    }

    public void setDateLastClick(Date dateLastClick) {
        this.dateLastClick = dateLastClick;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Calculates the hash code for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(url, urlClicked, dateLastClick);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ManufacturerDescription other = (ManufacturerDescription) obj;
        return Objects.equals(url, other.url) &&
               Objects.equals(urlClicked, other.urlClicked) &&
               Objects.equals(dateLastClick, other.dateLastClick) &&
               Objects.equals(manufacturer, other.manufacturer);
    }
}
