package com.github.caijh.supermarket.base.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.github.caijh.supermarket.base.model.Country;
import com.github.caijh.supermarket.base.repository.CountryRepository;
import com.github.caijh.supermarket.base.service.CountryService;
import org.springframework.data.jpa.repository.JpaRepository;

@Named
public class CountryServiceImpl implements CountryService {

    @Inject
    private CountryRepository countryRepository;

    @Override
    public JpaRepository<Country, Long> getRepository() {
        return this.countryRepository;
    }

}
