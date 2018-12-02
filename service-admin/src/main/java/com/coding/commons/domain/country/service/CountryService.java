package com.coding.commons.domain.country.service;

import com.coding.commons.base.BizException;
import com.coding.commons.base.data.jpa.service.JpaBaseService;
import com.coding.commons.domain.country.model.Country;

public interface CountryService extends JpaBaseService<Country, Long> {
    void add(Country country) throws BizException;

    void update(Country country) throws BizException;
}
