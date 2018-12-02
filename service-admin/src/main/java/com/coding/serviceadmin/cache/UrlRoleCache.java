package com.coding.serviceadmin.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;

import com.coding.commons.base.BizException;
import com.coding.commons.domain.resource.service.GrantedAuthorityService;
import org.springframework.beans.factory.InitializingBean;

@Named
public class UrlRoleCache implements InitializingBean {

    @Inject
    private GrantedAuthorityService grantedAuthorityService;

    private Map<String, Set<String>> urlRoleCodeSetMap;

    @Override
    public void afterPropertiesSet() throws Exception {
        urlRoleCodeSetMap = grantedAuthorityService.init();
    }

    public Map<String, Set<String>> getUrlRoleCodeSetMap() {
        return urlRoleCodeSetMap;
    }

    public void reset(Long roleId, List<Long> resourceIds) throws BizException {
        grantedAuthorityService.reset(roleId, resourceIds);
    }
}
