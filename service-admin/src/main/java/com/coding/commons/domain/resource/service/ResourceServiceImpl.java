package com.coding.commons.domain.resource.service;

import javax.inject.Inject;

import com.coding.commons.domain.resource.model.Resource;
import com.coding.commons.domain.resource.repository.ResourceRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Cacheable(value = "resources")
@Service
public class ResourceServiceImpl implements ResourceService {

    @Inject
    private ResourceRepository resourceRepository;

    @Override
    public JpaRepository<Resource, Long> getRepository() {
        return resourceRepository;
    }
}
