package com.github.caijh.supermarket.base.service.impl;

import javax.inject.Inject;

import com.github.caijh.supermarket.base.model.Area;
import com.github.caijh.supermarket.base.repository.AreaRepository;
import com.github.caijh.supermarket.base.service.AreaService;
import org.springframework.stereotype.Service;

@Service
public class AreaServiceImpl implements AreaService {

    @Inject
    private AreaRepository areaRepository;


    @Override
    public void add(Area area) {
        this.areaRepository.save(area);
    }

}
