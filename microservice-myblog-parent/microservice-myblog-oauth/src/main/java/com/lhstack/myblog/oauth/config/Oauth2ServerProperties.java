package com.lhstack.myblog.oauth.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth2")
@Data
@Accessors(chain = true)
public class Oauth2ServerProperties {
    private String clientId;
    private String secret;
    private String authorizedGrantTypes;
    private String scopes;
    private String redirectUrl;
    private Boolean isUseInMemory;
    private Integer accessTokenValiditySeconds = 18000;
    private Integer refreshTokenValiditySeconds = 18000;
}
