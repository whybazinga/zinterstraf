package com.vvopaa.zinterstraf.model;

import com.vvopaa.zinterstraf.util.ArrayUtil;
import com.vvopaa.zinterstraf.util.StringUtil;
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

    @Column(name="client_secret")
    private String clientSecret;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "link_client_scope",
            joinColumns = { @JoinColumn(name = "id_client") },
            inverseJoinColumns = { @JoinColumn(name = "id_scope") }
    )
    private Set<ClientScopes> scope = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "link_client_grant_type",
            joinColumns = { @JoinColumn(name = "id_client") },
            inverseJoinColumns = { @JoinColumn(name = "id_grant_type") }
    )
    private Set<ClientGrantTypes> authGrantTypes = new HashSet<>();

    @Column(name="server_redirect_uri")
    private String registeredRedirectUri = null;

    @Column(name="authorities")
    private String authorities = null;

    @Column(name="access_token_validity", columnDefinition = "mediumint unsigned default 172800")
    private Integer accessTokenValiditySeconds;

    @Column(name="refresh_token_validity", columnDefinition = "mediumint unsigned default 0")
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
        Set<String> resources = new HashSet<>();
        if(ArrayUtil.isNotEmptyCollection(resourceIds)) {
            resourceIds.forEach(item->resources.add(item.getResourceId()));
        }
        return resources;
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
        Set<String> scopes = new HashSet<>();
        if(ArrayUtil.isNotEmptyCollection(scope)) {
            scope.forEach(item->scopes.add(item.getScope()));
        }
        return scopes;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        Set<String> grantTypes = new HashSet<>();
        if(ArrayUtil.isNotEmptyCollection(authGrantTypes)) {
            authGrantTypes.forEach(item->grantTypes.add(item.getGrantType()));
        }
        return grantTypes;
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
