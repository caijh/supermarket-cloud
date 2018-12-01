package com.coding.commons.domain.client.repository;

import com.coding.commons.domain.client.model.ClientApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientAppRepository extends JpaRepository<ClientApp, String> {
}
