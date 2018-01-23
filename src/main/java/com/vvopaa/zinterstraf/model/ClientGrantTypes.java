package com.vvopaa.zinterstraf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="client_grant_types")
public class ClientGrantTypes extends AbstractEntity {

    @Column(name = "grant_type", unique = true)
    private String grantType;

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

}
