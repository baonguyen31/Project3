package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

public interface IBuildingService {
    List<BuildingSearchResponse> getBuildings();
    ResponseDTO listStaffs(Long buildingId);
    BuildingDTO insert(BuildingDTO dto);
    BuildingDTO findById(Long Id);
    BuildingDTO update(BuildingDTO dto, Long Id);
    void deleteByBuidling(List<Long> Id);
//    List<BuildingSearchResponse> findAllBuildings(Map<String, Object> params, List<String> typeCode);
    List<BuildingSearchResponse> findAllBuildings(BuildingSearchRequest request, Pageable pageable);
    int countTotalItems(BuildingSearchBuilder builder);
}
