package com.coding.commons.domain.user.model;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

@Getter
@Setter
@Entity
public class UserToken implements OAuth2AccessToken {
    @Id
    private String authenticationId;

    @Type(type = "jsonb")
    private OAuth2AccessToken accessToken;

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return accessToken.getAdditionalInformation();
    }

    @Override
    public Set<String> getScope() {
        return accessToken.getScope();
    }

    @Override
    public OAuth2RefreshToken getRefreshToken() {
        return accessToken.getRefreshToken();
    }

    public String getTokenType() {
        return accessToken.getTokenType();
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public Date getExpiration() {
        return accessToken.getExpiration();
    }

    @Override
    public int getExpiresIn() {
        return accessToken.getExpiresIn();
    }

    @Override
    public String getValue() {
        return accessToken.getValue();
    }
}
