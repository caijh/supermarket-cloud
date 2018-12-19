package com.coding.supermarket.serviceadmin.controller;

import javax.inject.Inject;

import com.coding.commons.base.BizException;
import com.coding.commons.domain.resource.model.Resource;
import com.coding.commons.domain.resource.service.ResourceService;
import com.coding.supermarket.serviceadmin.request.resource.ResourceListReqBody;
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
@RequestMapping(value = "/admin/resource")
public class AdminResourceController {

    @Inject
    private ResourceService resourceService;

    /**
     * list resource.
     *
     * @param reqBody ResourceListReqBody
     * @return ResponseEntity
     */
    @PostMapping(value = "/list")
    public ResponseEntity<Page<Resource>> list(@RequestBody ResourceListReqBody reqBody) {
        Resource resource = new Resource();
        Pageable pageable = PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize());
        Page<Resource> page = resourceService.list(resource, pageable);

        return ResponseEntity.ok(page);
    }

    /**
     * get resource detail.
     *
     * @param id id
     * @return ResponseEntity
     * @throws BizException e, if resource not found.
     */
    @GetMapping(value = "/detail")
    public ResponseEntity<Resource> detail(@RequestParam("id") Long id) throws BizException {
        Resource resource = resourceService.detail(id);

        return ResponseEntity.ok(resource);
    }

}
