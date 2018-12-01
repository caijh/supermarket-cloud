package com.coding.authorizationserver.controller;

import java.util.HashSet;
import java.util.UUID;
import javax.inject.Inject;
import javax.validation.Valid;

import com.coding.authorizationserver.request.ClientRegistryReqBody;
import com.coding.commons.base.AbstractListReqBody;
import com.coding.commons.domain.client.model.ClientApp;
import com.coding.commons.domain.client.model.ClientDetailsWrapper;
import com.coding.commons.domain.client.model.ClientType;
import com.coding.commons.domain.client.service.ClientAppService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/client")
public class ClientAppController {
    private static final String ATTRIBUTE_NAME_CLIENTS = "clients";
    private static final String ATTRIBUTE_NAME_REGISTRY = "registry";
    private static final int REFRESH_TOKEN_VALIDITY_SECONDS = 2592000; // 30 days

    @Inject
    private ClientRegistrationService clientRegistrationService;
    @Inject
    private ClientAppService clientAppService;

    /**
     * 进入注册client页面.
     *
     * @param modelAndView ModelAndView
     * @return ModelAndView
     */
    @GetMapping(value = "/register")
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.setViewName("client/register");
        modelAndView.addObject(ATTRIBUTE_NAME_REGISTRY, new ClientRegistryReqBody());
        return modelAndView;
    }

    /**
     * 注册client.
     *
     * @param reqBody       ClientRegistryReqBody
     * @param bindingResult BindingResult
     * @return ModelAndView
     */
    @PostMapping(value = "/save")
    public ModelAndView save(@Valid ClientRegistryReqBody reqBody, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("client/register");
            modelAndView.addObject(ATTRIBUTE_NAME_REGISTRY, reqBody);
            return modelAndView;
        }

        ClientApp clientApp = constructClientApp(reqBody);
        clientRegistrationService.addClientDetails(new ClientDetailsWrapper(clientApp));

        ModelAndView mv = new ModelAndView("redirect:/client/dashboard");
        ClientApp qryClientApp = new ClientApp();
        Pageable pageable = PageRequest.of(AbstractListReqBody.DEFAULT_PAGE_NO - 1, AbstractListReqBody.DEFAULT_PAGE_SIZE);
        mv.addObject(ATTRIBUTE_NAME_CLIENTS, clientAppService.list(qryClientApp, pageable));
        return mv;
    }

    private ClientApp constructClientApp(@Valid ClientRegistryReqBody reqBody) {
        ClientApp clientApp = new ClientApp();
        clientApp.setName(reqBody.getName());
        clientApp.setClientType(ClientType.valueOf(reqBody.getClientType()).getIndex());
        clientApp.setRedirectUri(new HashSet<>());
        clientApp.addRedirectUri(reqBody.getRedirectUri());
        clientApp.setClientId(reqBody.getClientId());
        if (clientApp.getClientId() == null) {
            clientApp.setClientId(UUID.randomUUID().toString());
        }
        clientApp.setClientSecret(UUID.randomUUID().toString());
        clientApp.setAccessTokenValiditySeconds(reqBody.getAccessTokenValiditySeconds());
        clientApp.setRefreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
        clientApp.setAuthorizedGrantTypes(new HashSet<>());
        clientApp.addAuthorizedGrantType("authorization_code")
            .addAuthorizedGrantType("implicit")
            .addAuthorizedGrantType("password")
            .addAuthorizedGrantType("client_credentials")
            .addAuthorizedGrantType("refresh_token");
        clientApp.setScope(new HashSet<>());
        clientApp.addScope(reqBody.getName());
        clientApp.setResourceIds(new HashSet<>());
        return clientApp;
    }

    /**
     * remove client.
     *
     * @param clientId client id
     * @return ModelAndView
     */
    @GetMapping(value = "/remove")
    public ModelAndView remove(@RequestParam(value = "client_id") String clientId) {
        this.clientRegistrationService.removeClientDetails(clientId);
        ModelAndView mv = new ModelAndView("redirect:/client/dashboard");
        mv.addObject(ATTRIBUTE_NAME_CLIENTS, clientRegistrationService.listClientDetails());
        return mv;
    }

    @GetMapping(value = "/dashboard")
    public ModelAndView dashboard(ModelAndView modelAndView,
                                  @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ClientApp clientApp = new ClientApp();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        modelAndView.addObject(ATTRIBUTE_NAME_CLIENTS, clientAppService.list(clientApp, pageable));
        return modelAndView;
    }

}
