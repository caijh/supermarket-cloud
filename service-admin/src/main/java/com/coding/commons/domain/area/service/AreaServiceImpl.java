package com.coding.commons.domain.area.service;

import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.coding.commons.base.BizException;
import com.coding.commons.base.locale.LocaleMessageService;
import com.coding.commons.domain.area.model.Area;
import com.coding.commons.domain.area.repository.AreaCacheRepository;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class AreaServiceImpl implements AreaService {

    @Inject
    private AreaCacheRepository areaCacheRepository;

    @Inject
    private LocaleMessageService localeMessageService;

    @Override
    public JpaRepository<Area, String> getRepository() {
        return areaCacheRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void add(Area area) throws BizException {
        Optional<Area> dbAreaOptional = areaCacheRepository.findById(area.getId());
        if (dbAreaOptional.isPresent()) {
            throw localeMessageService.getLocaleException("area.exists");
        }
        getRepository().save(area);
    }
}
