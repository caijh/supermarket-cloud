package com.coding.commons.domain.user.service;

import java.util.List;

import com.coding.commons.base.BizException;
import com.coding.commons.base.data.jpa.service.JpaBaseService;
import com.coding.commons.domain.resource.model.Resource;
import com.coding.commons.domain.user.model.User;

public interface UserService extends JpaBaseService<User, Long> {
    /**
     * get user's resources.
     *
     * @param userId 用户id
     * @return resources.
     */
    List<Resource> listUserResources(Long userId);

    void lock(Long id) throws BizException;

    void delete(Long id) throws BizException;

    void add(User user) throws BizException;
}
