package com.vvopaa.zinterstraf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="client_resource_ids")
public class ClientResourceIds extends AbstractEntity {

    @Column(name = "resource_id", unique = true)
    private String resourceId;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

}
