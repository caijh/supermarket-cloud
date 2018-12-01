package com.coding.commons.domain.client.service;

import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.domain.client.model.ClientApp;
import com.coding.commons.domain.client.repository.ClientAppCacheRepository;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class ClientAppServiceImpl implements ClientAppService {
    @Inject
    private ClientAppCacheRepository clientAppCacheRepository;

    @Override
    public JpaRepository<ClientApp, String> getRepository() {
        return clientAppCacheRepository;
    }
}
