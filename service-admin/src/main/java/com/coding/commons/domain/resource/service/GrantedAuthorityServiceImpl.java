package com.coding.commons.domain.resource.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.coding.commons.base.BizException;
import com.coding.commons.base.data.redis.RedisUtils;
import com.coding.commons.domain.resource.model.Resource;
import com.coding.commons.domain.resource.repository.ResourceCacheRepository;
import com.coding.commons.domain.role.model.Role;
import com.coding.commons.domain.role.model.RoleResource;
import com.coding.commons.domain.role.repository.RoleCacheRepository;
import com.coding.commons.domain.role.repository.RoleResourceCacheRepository;
import com.coding.commons.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class GrantedAuthorityServiceImpl implements GrantedAuthorityService {
    private final Map<String, Set<String>> urlRoleCodesMap = new HashMap<>();
    @Inject
    private RoleCacheRepository roleCacheRepository;
    @Inject
    private ResourceCacheRepository resourceCacheRepository;
    @Inject
    private RoleResourceCacheRepository roleResourceCacheRepository;
    @Inject
    private RedisUtils redisUtils;

    @Transactional
    @Override
    public Map<String, Set<String>> init() {
        urlRoleCodesMap.clear();
        initUrlRoleCodesMap();

        for (Map.Entry<String, Set<String>> entry : urlRoleCodesMap.entrySet()) {
            redisUtils.setList(Resource.GRANTED_URL_PREFIX + entry.getKey(), new ArrayList<>(entry
                .getValue()), String.class, 0L);
        }
        return urlRoleCodesMap;
    }

    @Override
    public void reset(Long roleId, List<Long> resourceIds) throws BizException {
        Role role = roleCacheRepository.findById(roleId).orElseThrow(() -> new BizException(""));
        urlRoleCodesMap.forEach((url, codes) -> codes.remove(role.getCode()));
        resourceIds.forEach(resourceId -> resourceCacheRepository.findById(resourceId).ifPresent(resource -> {
            if (StringUtils.isNotBlank(resource.getUrl())) {
                urlRoleCodesMap.get(resource.getUrl()).add(role.getCode());
            }
        }));
        for (Map.Entry<String, Set<String>> entry : urlRoleCodesMap.entrySet()) {
            redisUtils.setList(Resource.GRANTED_URL_PREFIX + entry.getKey(), new ArrayList<>(entry
                .getValue()), String.class, 0L);
        }
    }

    private void initUrlRoleCodesMap() {
        List<RoleResource> roleResourceList = roleResourceCacheRepository.findAll();
        for (RoleResource roleResource : roleResourceList) {
            Resource resource = resourceCacheRepository.getOne(roleResource.getResourceId());
            if (StringUtils.isBlank(resource.getUrl())) {
                continue;
            }
            if (!urlRoleCodesMap.containsKey(resource.getUrl())) {
                urlRoleCodesMap.put(resource.getUrl(), new LinkedHashSet<>());
            }
            Role role = roleCacheRepository.getOne(roleResource.getRoleId());
            urlRoleCodesMap.get(resource.getUrl()).add(role.getCode());
        }
    }
}
