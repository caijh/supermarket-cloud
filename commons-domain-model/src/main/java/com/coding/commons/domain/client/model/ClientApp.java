package com.coding.commons.domain.client.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.coding.commons.base.IndexEnum;
import com.coding.commons.base.PersistentObject;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

/**
 * 客户端App.
 */
@Getter
@Setter
@Entity
public class ClientApp implements PersistentObject<String>, Serializable {
    @Id
    private String clientId;
    private String clientSecret;
    private Integer clientType;
    private String name;

    @Type(type = "jsonb")
    private Set<String> resourceIds;

    @Type(type = "jsonb")
    private Set<String> scope;

    @Type(type = "jsonb")
    private Set<String> authorizedGrantTypes;

    @Type(type = "jsonb")
    private Set<String> redirectUri;

    @Type(type = "jsonb")
    private Set<String> authorities;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    public void addRedirectUri(String redirectUri) {
        this.redirectUri.add(redirectUri);
    }

    public ClientApp addAuthorizedGrantType(String authorizedGrantType) {
        this.authorizedGrantTypes.add(authorizedGrantType);
        return this;
    }

    public ClientApp addScope(String scope) {
        this.scope.add(scope);
        return this;
    }

    @Override
    public String getId() {
        return clientId;
    }

    public Map<String, Object> getAdditionalInformation() {
        Map<String, Object> additionalInformation = new HashMap<>();
        additionalInformation.put("client_type", IndexEnum.valueOf(this.clientType, ClientType.class).name());
        additionalInformation.put("name", this.name);
        return additionalInformation;
    }
}
