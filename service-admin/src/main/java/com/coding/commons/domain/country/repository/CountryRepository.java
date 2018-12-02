package com.coding.commons.domain.country.repository;

import com.coding.commons.domain.country.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
