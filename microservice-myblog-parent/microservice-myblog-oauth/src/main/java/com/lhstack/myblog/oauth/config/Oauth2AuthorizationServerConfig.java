package com.lhstack.myblog.oauth.config;

import com.lhstack.myblog.oauth.service.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@AllArgsConstructor
@EnableConfigurationProperties(Oauth2ServerProperties.class)
@EnableAuthorizationServer
@Configuration
public class Oauth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    //Nacos配置中心的客户端配置
    private Oauth2ServerProperties oauth2ServerProperties;
    //BCryPasswordEncoder
    private PasswordEncoder passwordEncoder;
    //用户服务
    private UserDetailsService userDetailsService;
    //权限管理
    private AuthenticationManager authenticationManager;
    //DataSource
    private DataSource dataSource;

    private Oauth2TokenEnhancer oauth2TokenEnhancer;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        if(oauth2ServerProperties.getIsUseInMemory()){
            clients.inMemory()
                    .withClient(oauth2ServerProperties.getClientId())
                    .secret(passwordEncoder.encode(oauth2ServerProperties.getSecret()))
                    .authorizedGrantTypes(oauth2ServerProperties.getAuthorizedGrantTypes().split(","))
                    .scopes(oauth2ServerProperties.getScopes().split(","))
                    .redirectUris(oauth2ServerProperties.getRedirectUrl().split(","))
                    .accessTokenValiditySeconds(oauth2ServerProperties.getAccessTokenValiditySeconds())
                    .refreshTokenValiditySeconds(oauth2ServerProperties.getRefreshTokenValiditySeconds());
        }else{
            clients.withClientDetails(jdbcClientDetailsService());
        }
    }

    //使用数据库的客户端信息
    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService(){
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new JdbcTokenStore(dataSource))
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenEnhancer(oauth2TokenEnhancer);
    }

}
