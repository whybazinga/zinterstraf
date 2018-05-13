package com.vvopaa.zinterstraf.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name="client_scopes")
class ClientScopes extends AbstractEntity {

    @Column(name = "scope", unique = true)
    private String scope;

}
