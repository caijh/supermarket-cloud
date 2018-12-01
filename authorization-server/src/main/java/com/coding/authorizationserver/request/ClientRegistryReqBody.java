package com.coding.authorizationserver.request;

import javax.validation.constraints.NotNull;

import com.coding.commons.base.ReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRegistryReqBody implements ReqBody {
    private String clientId;

    @NotNull
    private String clientType;

    @NotNull
    private String name;

    @NotNull
    private String redirectUri;

    @NotNull
    private Integer accessTokenValiditySeconds;
}
