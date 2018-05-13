package com.vvopaa.zinterstraf.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name="client_resource_ids")
class ClientResourceIds extends AbstractEntity {

    @Column(name = "resource_id", unique = true)
    private String resourceId;

}
