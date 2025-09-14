package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;

import java.util.List;

public interface IRentAreaService {
    void addRentArea(BuildingDTO buildingDTO);
    void deleteByBuildingIdIn(List<Long> ids);
}
