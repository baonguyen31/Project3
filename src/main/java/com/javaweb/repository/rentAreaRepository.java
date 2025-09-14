package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface rentAreaRepository extends JpaRepository<RentAreaEntity, Long> {
    void deleteByBuilding(BuildingEntity buildingEntity);
    void deleteByBuildingIdIn(List<Long> ids);
}
