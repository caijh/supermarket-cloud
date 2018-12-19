package com.coding.supermarket.serviceadmin.controller;

import java.util.List;
import javax.inject.Inject;

import com.coding.commons.base.BizException;
import com.coding.commons.base.constant.Operate;
import com.coding.commons.base.locale.LocaleMessageService;
import com.coding.commons.domain.resource.model.Resource;
import com.coding.commons.domain.resource.model.ResourceHelper;
import com.coding.commons.domain.user.model.User;
import com.coding.commons.domain.user.model.UserStatusEnum;
import com.coding.commons.domain.user.service.UserService;
import com.coding.commons.util.BeanUtils;
import com.coding.commons.util.DateUtils;
import com.coding.supermarket.serviceadmin.request.user.UserAddReqBody;
import com.coding.supermarket.serviceadmin.request.user.UserListReqBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin/user")
public class AdminUserController {

    @Inject
    private UserService userService;

    @Inject
    private LocaleMessageService localeMessageService;

    /**
     * 用户列表.
     *
     * @param userListReqBody UserListReqBody
     * @return ResponseEntity
     */
    @PostMapping(value = "/list")
    public ResponseEntity<Page<User>> list(@RequestBody UserListReqBody userListReqBody) {
        User user = new User();
        user.setAccount(userListReqBody.getAccount());
        Pageable pageable = PageRequest.of(userListReqBody.getPageNo(), userListReqBody.getPageSize());
        Page<User> page = userService.list(user, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "/detail")
    public ResponseEntity<User> detail(@RequestParam(value = "id") Long userId) throws BizException {
        User user = userService.detail(userId);
        return ResponseEntity.ok(user);
    }

    /**
     * 用户的资源权限.
     *
     * @param userId the user id.
     * @return ResponseEntity
     */
    @GetMapping(value = "/resources")
    public ResponseEntity<List<Resource>> listUserResources(@RequestParam(value = "id") Long userId) {
        List<Resource> resources = userService.listUserResources(userId);
        List<Resource> rootResources = ResourceHelper.sortAsRootResources(resources);

        return ResponseEntity.ok(rootResources);
    }

    /**
     * 锁定用户.
     */
    @GetMapping(value = "/update", params = "action=lock")
    public ResponseEntity<String> lockUser(@RequestParam Long id) throws BizException {
        userService.lock(id);
        return ResponseEntity.ok(localeMessageService.getMessage(Operate.LOCK_SUCCESS));
    }

    /**
     * 将用户设置为删除状态.
     *
     * @param id user id
     * @return ResponseEntity
     * @throws BizException bizException
     */
    @GetMapping(value = "/delete")
    public ResponseEntity<String> delete(Long id) throws BizException {
        userService.delete(id);
        return ResponseEntity.ok(localeMessageService.getMessage(Operate.DEL_SUCCESS));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> add(@RequestBody UserAddReqBody reqBody) throws BizException {
        User user = new User();
        BeanUtils.copyIgnoreNullProperties(reqBody, user);
        user.setStatus(UserStatusEnum.NORMAL.getIndex());
        user.setCreateTime(DateUtils.now());
        userService.add(user);

        return ResponseEntity.ok(localeMessageService.getMessage(Operate.ADD_SUCCESS));
    }
}
