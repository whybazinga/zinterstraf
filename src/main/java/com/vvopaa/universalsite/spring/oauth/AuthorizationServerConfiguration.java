package com.vvopaa.universalsite.spring.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Registers a client with client-id ‘my-trusted-client’ and password ‘secret’ and roles & scope he is allowed for.
 * Specifies that any generated access token will be valid for only 120 seconds
 * Specifies that any generated refresh token will be valid for only 600 seconds
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private static String REALM = "MY_OAUTH_REALM";

    private final HibernateTransactionManager transactionManager;

    @Autowired
    public AuthorizationServerConfiguration(HibernateTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(this.transactionManager.getDataSource())
                .withClient("clientIdPassword")
                .secret("secret")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("read");
                /*
                .and()
                .withClient("sampleClientId")
                .authorizedGrantTypes("implicit")
                .scopes("read")
                .autoApprove(true)
                //
                .accessTokenValiditySeconds(120).//Access token is only valid for 2 minutes.
                refreshTokenValiditySeconds(600);//Refresh token is only valid for 10 minutes.
                */
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            .tokenStore(tokenStore) // Persist tokens to database
            .authenticationManager(authenticationManager);
            /*
            .approvalStoreDisabled()
            .userDetailsService(customUserDetailService);
            */
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
            .tokenKeyAccess("permitAll()")
            .checkTokenAccess("permitAll()");
        /*Don't allow tokens to be delivered from token access point as well as for tokens to be validated from this point*/
    }

}

