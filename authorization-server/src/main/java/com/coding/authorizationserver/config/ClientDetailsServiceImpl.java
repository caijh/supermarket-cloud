package com.coding.authorizationserver.config;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.domain.client.model.ClientApp;
import com.coding.commons.domain.client.model.ClientDetailsWrapper;
import com.coding.commons.domain.client.repository.ClientAppRepository;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.NoSuchClientException;

@Named(value = "clientDetailsServiceImpl")
public class ClientDetailsServiceImpl implements ClientDetailsService, ClientRegistrationService {
    @Inject
    private ClientAppRepository clientAppRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientAppRepository.findById(clientId).map(ClientDetailsWrapper::new).orElse(null);
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        ClientDetails existsClientDetails = loadClientByClientId(clientDetails.getClientId());
        if (existsClientDetails != null) {
            throw new ClientAlreadyExistsException("client already exists");
        }

        ClientApp clientApp = ((ClientDetailsWrapper) clientDetails).getClientApp();
        clientAppRepository.save(clientApp);
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        ClientDetails existsClientDetails = loadClientByClientId(clientDetails.getClientId());

        if (existsClientDetails == null) {
            throw new NoSuchClientException("no such client");
        }

        clientAppRepository.save(((ClientDetailsWrapper) clientDetails).getClientApp());
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        clientAppRepository.findById(clientId)
            .ifPresent(clientApp -> {
                clientApp.setClientSecret(secret);
                clientAppRepository.save(clientApp);
            });
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        clientAppRepository.deleteById(clientId);
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        List<ClientApp> clientApps = clientAppRepository.findAll();
        return clientApps.stream().map(ClientDetailsWrapper::new).collect(Collectors.toList());
    }
}
