package com.vvopaa.zinterstraf.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name="client_grant_types")
class ClientGrantTypes extends AbstractEntity {

    @Column(name = "grant_type", unique = true)
    private String grantType;

}
