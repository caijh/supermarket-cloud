package com.coding.commons.domain.resource.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.coding.commons.base.BizException;

public interface GrantedAuthorityService {
    Map<String, Set<String>> init();

    void reset(Long roleId, List<Long> resourceIds) throws BizException;
}
