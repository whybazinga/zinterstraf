package com.vvopaa.universalsite.model;

import com.vvopaa.universalsite.util.ArrayUtil;
import com.vvopaa.universalsite.util.StringUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name="oauth_clients")
public class Client implements ClientDetails, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="client_id")
    private String clientId;

    @Column(name="resource_ids")
    private String resourceIds;

    @Column(name="client_secret")
    private String clientSecret;

    @Column(name="scope")
    private String scope;

    @Column(name="authorized_grant_types")
    private String authorizedGrantTypes;

    @Column(name="server_redirect_uri")
    private String registeredRedirectUri;

    @Column(name="authorities")
    private String authorities;

    @Column(name="access_token_validity")
    private Integer accessTokenValiditySeconds;

    @Column(name="refresh_token_validity")
    private Integer refreshTokenValiditySeconds;

    @Column(name="autoapprove")
    private Boolean autoApprove;

    @Column(name="additional_information")
    private String additionalInformation;

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
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
        return false;
    }

    @Override
    public Set<String> getScope() {
        return null;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return null;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
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

    public Boolean getAutoApprove() {
        return autoApprove;
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
        return null;
    }
}
