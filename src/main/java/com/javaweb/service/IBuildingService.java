package com.javaweb.service;

import java.util.List;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;

public interface IBuildingService {
    List<BuildingSearchResponse> getBuildings();
    ResponseDTO listStaffs(Long buildingId);
    BuildingDTO insert(BuildingDTO dto);
    BuildingDTO findById(Long Id);
    BuildingDTO update(BuildingDTO dto, Long Id);
    void deleteByBuidling(List<Long> Id);
}
