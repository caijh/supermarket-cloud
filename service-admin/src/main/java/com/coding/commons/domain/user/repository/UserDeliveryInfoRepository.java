package com.coding.commons.domain.user.repository;

import com.coding.commons.domain.user.model.UserDeliveryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDeliveryInfoRepository extends JpaRepository<UserDeliveryInfo, Long> {
}
