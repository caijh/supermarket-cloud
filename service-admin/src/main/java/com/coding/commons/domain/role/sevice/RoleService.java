package com.coding.commons.domain.role.sevice;

import java.util.List;

import com.coding.commons.base.BizException;
import com.coding.commons.base.data.jpa.service.JpaBaseService;
import com.coding.commons.domain.resource.model.Resource;
import com.coding.commons.domain.role.model.Role;
import com.coding.commons.domain.user.vo.UserRoleListVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService extends JpaBaseService<Role, Long> {
    void add(Role role);

    void delete(Long id) throws BizException;

    void update(Role role) throws BizException;

    void setResources(Long roleId, List<Long> resourceIds) throws BizException;

    Page<UserRoleListVo> listUser(Long roleId, Pageable pageable);

    void addUsers(Long roleId, List<Long> userIds) throws BizException;

    void deleteUsers(Long roleId, List<Long> userIds);

    List<Resource> listAllResources(Long id) throws BizException;

    List<Resource> preSetRoleResources(Long roleId) throws BizException;
}
