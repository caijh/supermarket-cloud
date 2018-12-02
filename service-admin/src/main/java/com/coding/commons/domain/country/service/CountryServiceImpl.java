package com.coding.commons.domain.country.service;

import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.coding.commons.base.BizException;
import com.coding.commons.base.locale.LocaleMessageService;
import com.coding.commons.domain.country.model.Country;
import com.coding.commons.domain.country.repository.CountryCacheRepository;
import com.coding.commons.util.AssertUtils;
import com.coding.commons.util.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;

@CacheConfig(cacheNames = {"countries"})
@Named
public class CountryServiceImpl implements CountryService {

    @Inject
    private CountryCacheRepository countryCacheRepository;

    @Inject
    private LocaleMessageService localeMessageService;

    @Override
    public JpaRepository<Country, Long> getRepository() {
        return countryCacheRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void add(Country country) throws BizException {
        AssertUtils.notNull(country.getId(), BizException.class,
            localeMessageService.getMessage("country.id_is_required"));

        Optional<Country> countryOptional = getRepository().findById(country.getId());
        if (countryOptional.isPresent()) {
            throw localeMessageService.getLocaleException("country.exists");
        }

        getRepository().save(country);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void update(Country country) throws BizException {
        Country oldCountry = getRepository().findById(country.getId())
            .orElseThrow(() -> localeMessageService.getLocaleException("country.not_found"));
        BeanUtils.copyIgnoreNullProperties(country, oldCountry);

        getRepository().save(country);
    }
}
