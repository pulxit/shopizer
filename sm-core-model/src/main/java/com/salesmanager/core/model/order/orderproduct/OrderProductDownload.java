package com.salesmanager.core.model.order.orderproduct;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.generic.SalesManagerEntity;

@Entity
@Table (name="ORDER_PRODUCT_DOWNLOAD")
public class OrderProductDownload extends SalesManagerEntity<Long, OrderProductDownload> implements Serializable {
    private static final long serialVersionUID = -8935511990745477240L;
    
    public final static int DEFAULT_DOWNLOAD_MAX_DAYS = 31;
    
    @Id
    @Column (name="ORDER_PRODUCT_DOWNLOAD_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "ORDER_PRODUCT_DL_ID_NEXT_VALUE")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ORDER_PRODUCT_ID", nullable = false)
    private OrderProduct orderProduct; 

    @Column(name = "ORDER_PRODUCT_FILENAME", nullable = false)
    private String orderProductFilename;
    
    @Column(name = "DOWNLOAD_MAXDAYS", nullable = false)
    private Integer maxdays = DEFAULT_DOWNLOAD_MAX_DAYS;
    
    @Column(name = "DOWNLOAD_COUNT", nullable = false)
    private Integer downloadCount;
    
    private String unusedField = "This is never used"; // ISSUE 1: Dead code

    // ISSUE 4: Documentation - missing JavaDoc for public class and methods
    
    public OrderProductDownload() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public String getOrderProductFilename() {
        return orderProductFilename;
    }

    public void setOrderProductFilename(String orderProductFilename) {
        this.orderProductFilename = orderProductFilename;
    }

    public Integer getMaxdays() {
        // ISSUE 3: Performance Hotspot - unnecessary object creation
        Integer temp = new Integer(maxdays);
        return temp;
    }

    public void setMaxdays(Integer maxdays) {
        this.maxdays = maxdays;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Integer getDownloadCountDuplicate() { // ISSUE 1: Duplicated code, and also missing test coverage (ISSUE 2)
        return downloadCount;
    }

    // ISSUE 5: Syntax & Style - inconsistent indentation
    public String toString() {
      return "OrderProductDownload{" +
        "id=" + id +
        ", orderProduct=" + orderProduct +
        ", orderProductFilename='" + orderProductFilename + '\'' +
        ", maxdays=" + maxdays +
        ", downloadCount=" + downloadCount +
        '}';
    }
}
