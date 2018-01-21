package com.vvopaa.universalsite.model;

import com.vvopaa.universalsite.util.ArrayUtil;
import com.vvopaa.universalsite.util.StringUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="oauth_clients")
public class Client extends AbstractEntity implements ClientDetails, Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name="client_id", unique = true)
    private String clientId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "link_client_resource",
            joinColumns = { @JoinColumn(name = "id_client") },
            inverseJoinColumns = { @JoinColumn(name = "id_resource") }
    )
    private Set<ClientResourceIds> resourceIds = new HashSet<>();

    @Transient
    private Set<String> resourceSpring = new HashSet<>();

    @Column(name="client_secret")
    private String clientSecret;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "link_client_scope",
            joinColumns = { @JoinColumn(name = "id_client") },
            inverseJoinColumns = { @JoinColumn(name = "id_scope") }
    )
    private Set<ClientScopes> scope = new HashSet<>();

    @Transient
    private Set<String> scopeSpring = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "link_client_grant_type",
            joinColumns = { @JoinColumn(name = "id_client") },
            inverseJoinColumns = { @JoinColumn(name = "id_grant_type") }
    )
    private Set<ClientGrantTypes> authGrantTypes = new HashSet<>();

    @Transient
    private Set<String> authGrantTypesSpring = new HashSet<>();

    @Column(name="server_redirect_uri")
    private String registeredRedirectUri = null;

    @Column(name="authorities")
    private String authorities = null;

    @Column(name="access_token_validity")
    private Integer accessTokenValiditySeconds;

    @Column(name="refresh_token_validity")
    private Integer refreshTokenValiditySeconds;

    @Column(name="auto_approve", columnDefinition = "boolean default 0")
    private Boolean autoApprove;

    @Column(name="additional_information")
    private String additionalInformation = null;


    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        if(ArrayUtil.isNotEmptyCollection(resourceIds)) {
            if(!ArrayUtil.isNotEmptyCollection(resourceSpring)) {
                resourceIds.forEach(item->resourceSpring.add(item.getResourceId()));
            }
            return resourceSpring;
        }
        return new HashSet<>();
    }

    @Override
    public boolean isSecretRequired() {
        return StringUtil.isNotEmptyString(clientSecret);
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return ArrayUtil.isNotEmptyCollection(scope);
    }

    @Override
    public Set<String> getScope() {
        if(ArrayUtil.isNotEmptyCollection(scope)) {
            if(!ArrayUtil.isNotEmptyCollection(scopeSpring)) {
                scope.forEach(item->scopeSpring.add(item.getScope()));
            }
            return resourceSpring;
        }
        return new HashSet<>();
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        if(ArrayUtil.isNotEmptyCollection(authGrantTypes)) {
            if(!ArrayUtil.isNotEmptyCollection(authGrantTypesSpring)) {
                authGrantTypes.forEach(item->authGrantTypesSpring.add(item.getGrantType()));
            }
            return resourceSpring;
        }
        return new HashSet<>();
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return new HashSet<>();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return autoApprove;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return new HashMap<>();
    }

    public Set<ClientGrantTypes> getAuthGrantTypes() {
        return authGrantTypes;
    }

    public Boolean getAutoApprove() {
        return autoApprove;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setResourceIds(Set<ClientResourceIds> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setScope(Set<ClientScopes> scope) {
        this.scope = scope;
    }

    public void setAuthGrantTypes(Set<ClientGrantTypes> authGrantTypes) {
        this.authGrantTypes = authGrantTypes;
    }

    public void setRegisteredRedirectUri(String registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public void setAutoApprove(Boolean autoApprove) {
        this.autoApprove = autoApprove;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
