package com.coding.commons.domain.area.service;

import com.coding.commons.base.BizException;
import com.coding.commons.base.data.jpa.service.JpaBaseService;
import com.coding.commons.domain.area.model.Area;

public interface AreaService extends JpaBaseService<Area, String> {

    void add(Area area) throws BizException;
}
