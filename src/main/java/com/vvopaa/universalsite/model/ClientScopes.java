package com.vvopaa.universalsite.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="client_scopes")
public class ClientScopes extends AbstractEntity {

    @Column(name = "scope", unique = true)
    private String scope;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

}
