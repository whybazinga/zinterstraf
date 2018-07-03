package com.vvopaa.zinterstraf.spring.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Registers a client with client-id ‘my-trusted-client’ and password ‘secret’ and roles & scope he is allowed for.
 * Specifies that any generated access token will be valid for only 120 seconds
 * Specifies that any generated refresh token will be valid for only 600 seconds
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final ClientDetailsService clientDetailsService;
    private final TokenStore tokenStore;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthorizationServerConfiguration(
      @Qualifier("userServiceImp") UserDetailsService userDetailsService,
            @Qualifier("clientServiceImpl") ClientDetailsService clientDetailsService,
            TokenStore tokenStore,
            @Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.clientDetailsService = clientDetailsService;
        this.tokenStore = tokenStore;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            .tokenStore(tokenStore) // Persist tokens to database
            .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
            /*
            .approvalStoreDisabled()
            .userDetailsService(customUserDetailService);
            */
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
            .tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()");
        /*Don't allow tokens to be delivered from token access point as well as for tokens to be validated from this point*/
    }

}

