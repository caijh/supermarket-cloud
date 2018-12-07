package com.coding.supermarket.domain.express.mapper;

import java.util.List;

import com.coding.supermarket.domain.express.model.Express;
import com.coding.supermarket.domain.express.model.ExpressClientSetting;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ExpressMapper {
    Express get(Long expressId);

    List<ExpressClientSetting> findExpressClientSetting(Long expressId);
}
