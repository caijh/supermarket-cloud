package com.coding.supermarket.serviceadmin.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

import com.coding.commons.base.BizException;
import com.coding.commons.base.constant.Operate;
import com.coding.commons.base.locale.LocaleMessageService;
import com.coding.commons.domain.resource.model.Resource;
import com.coding.commons.domain.resource.model.ResourceHelper;
import com.coding.commons.domain.role.model.Role;
import com.coding.commons.domain.role.sevice.RoleService;
import com.coding.commons.domain.user.vo.UserRoleListVo;
import com.coding.commons.util.BeanUtils;
import com.coding.supermarket.serviceadmin.cache.UrlRoleCache;
import com.coding.supermarket.serviceadmin.request.role.RoleAddReqBody;
import com.coding.supermarket.serviceadmin.request.role.RoleAddUserReqBody;
import com.coding.supermarket.serviceadmin.request.role.RoleListReqBody;
import com.coding.supermarket.serviceadmin.request.role.RoleResourceReqBody;
import com.coding.supermarket.serviceadmin.request.role.RoleUpdateReqBody;
import com.coding.supermarket.serviceadmin.request.role.RoleUserDeleteReqBody;
import com.coding.supermarket.serviceadmin.request.role.RoleUserListReqBody;
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
@RequestMapping(value = "/admin/role")
public class AdminRoleController {

    @Inject
    private RoleService roleService;

    @Inject
    private LocaleMessageService localeMessageService;

    @Inject
    private UrlRoleCache urlRoleCache;

    /**
     * role list.
     *
     * @param reqBody RoleListReqBody
     * @return ResponseEntity
     */
    @PostMapping(value = "/list")
    public ResponseEntity<Page<Role>> list(@RequestBody RoleListReqBody reqBody) {
        Role role = new Role();
        role.setCode(reqBody.getCode());
        role.setName(reqBody.getName());
        Pageable pageable = PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize());
        Page<Role> page = roleService.list(role, pageable);

        return ResponseEntity.ok(page);
    }

    /**
     * role detail.
     *
     * @param id id
     * @return ResponseEntity
     */
    @GetMapping(value = "/detail")
    public ResponseEntity<Role> detail(@RequestParam("id") Long id) throws BizException {
        Role role = roleService.detail(id);

        return ResponseEntity.ok(role);
    }


    /**
     * add role.
     *
     * @param reqBody RoleAddReqBody
     * @return ResponseEntity
     */
    @PostMapping(value = "/add")
    public ResponseEntity<String> add(@RequestBody RoleAddReqBody reqBody) {
        Role role = new Role();
        role.setName(reqBody.getName());
        role.setCreatedBy(reqBody.getCreatedBy());
        role.setCreateTime(new Date());
        role.setIsSysDefined(false);
        roleService.add(role);

        return ResponseEntity.ok(localeMessageService.getMessage(Operate.ADD_SUCCESS));
    }

    /**
     * role update.
     */
    @PostMapping(value = "/update")
    public ResponseEntity<String> update(@RequestBody RoleUpdateReqBody reqBody) throws BizException {
        Role role = new Role();
        BeanUtils.copyIgnoreNullProperties(reqBody, role);
        role.setUpdatedBy(reqBody.getUpdatedBy());
        role.setUpdateTime(new Date());

        roleService.update(role);

        return ResponseEntity.ok(localeMessageService.getMessage(Operate.UPDATE_SUCCESS));
    }

    /**
     * 删除角色.
     *
     * @param roleId 角色id
     * @throws BizException if delete fail
     */
    @GetMapping(value = "/delete")
    public ResponseEntity<String> delete(@RequestParam(value = "id") Long roleId) throws BizException {
        roleService.delete(roleId);
        urlRoleCache.reset(roleId, Collections.emptyList());
        return ResponseEntity.ok(localeMessageService.getMessage(Operate.DEL_SUCCESS));
    }

    /**
     * 获取角色的所有资源权限.
     *
     * @param roleId role id
     * @return ResponseEntity
     * @throws BizException bizException
     */
    @GetMapping(value = "/resources")
    public ResponseEntity<List<Resource>> resources(@RequestParam(value = "id") Long roleId) throws BizException {
        List<Resource> resources = roleService.listAllResources(roleId);
        List<Resource> rootResources = ResourceHelper.sortAsRootResources(resources);

        return ResponseEntity.ok(rootResources);
    }

    /**
     * 设置角色权限前加载所有权限.
     */
    @GetMapping(value = "/set/resources", params = "action=pre")
    public List<Resource> preSetRoleResources(@RequestParam(value = "id") Long roleId) throws BizException {
        return roleService.preSetRoleResources(roleId);
    }

    /**
     * 设置角色权限.
     */
    @PostMapping(value = "/set/resources")
    public ResponseEntity<String> setResources(@RequestBody RoleResourceReqBody reqBody) throws BizException {
        roleService.setResources(reqBody.getRoleId(), reqBody.getResourceIds());
        urlRoleCache.reset(reqBody.getRoleId(), reqBody.getResourceIds());
        return ResponseEntity.ok(localeMessageService.getMessage(Operate.UPDATE_SUCCESS));
    }

    /**
     * 查询角色下用户列表.
     */
    @PostMapping(value = "/user/list")
    public ResponseEntity<Page<UserRoleListVo>> listUser(@RequestBody RoleUserListReqBody reqBody) {
        Pageable pageable = PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize());
        Page<UserRoleListVo> page = roleService.listUser(reqBody.getRoleId(), pageable);

        return ResponseEntity.ok(page);
    }

    /**
     * 角色下新增用户.
     */
    @PostMapping(value = "/user/add")
    public ResponseEntity<String> addUsers(@RequestBody RoleAddUserReqBody reqBody) throws BizException {
        roleService.addUsers(reqBody.getRoleId(), reqBody.getUserIds());

        return ResponseEntity.ok(localeMessageService.getMessage("role.add_user_success"));
    }

    /**
     * 删除角色下的用户.
     */
    @PostMapping(value = "/user/delete")
    public ResponseEntity<String> deleteUsers(RoleUserDeleteReqBody reqBody) {
        roleService.deleteUsers(reqBody.getRoleId(), reqBody.getUserIds());
        return ResponseEntity.ok(localeMessageService.getMessage("role.delete_user_success"));
    }
}
