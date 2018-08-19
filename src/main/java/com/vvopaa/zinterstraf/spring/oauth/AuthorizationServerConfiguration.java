package com.vvopaa.zinterstraf.spring.oauth;
/*
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
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final ClientDetailsService clientDetailsService;
    private final TokenStore tokenStore;
    private final AuthenticationManager authenticationManager;
    private final JwtAccessTokenConverter jwtAccessTokenConverter;
    private final DefaultTokenServices defaultTokenServices;

    @Autowired
    public AuthorizationServerConfiguration(
      @Qualifier("userServiceImp") UserDetailsService userDetailsService,
      @Qualifier("clientServiceImpl") ClientDetailsService clientDetailsService,
      TokenStore tokenStore,
      @Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager, JwtAccessTokenConverter jwtAccessTokenConverter, DefaultTokenServices defaultTokenServices) {
        this.userDetailsService = userDetailsService;
        this.clientDetailsService = clientDetailsService;
        this.tokenStore = tokenStore;
        this.authenticationManager = authenticationManager;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.defaultTokenServices = defaultTokenServices;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            .tokenStore(tokenStore) // Persist tokens to database
            .tokenServices(defaultTokenServices)
            .authenticationManager(authenticationManager)
            .accessTokenConverter(jwtAccessTokenConverter)
            .userDetailsService(userDetailsService);

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
            .tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()");
        //Don't allow tokens to be delivered from token access point as well as for tokens to be validated from this point
    }

}

*/