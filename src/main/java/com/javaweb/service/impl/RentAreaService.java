package com.javaweb.service.impl;

import com.javaweb.converter.RentAreaConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.rentAreaRepository;
import com.javaweb.service.IRentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RentAreaService implements IRentAreaService {
    @Autowired
    private BuildingRepository buildingRepo;

    @Autowired
    private rentAreaRepository rentAreaRepo;

    @Autowired
    private RentAreaConverter rentAreaConverter;

    @Override
    public void addRentArea(BuildingDTO buildingDTO) {
        BuildingEntity entity = buildingRepo.findById(buildingDTO.getId()).get();
        rentAreaRepo.deleteByBuilding(entity);

        String[] rentAreas = buildingDTO.getRentArea().split(",");

        for (String rentArea : rentAreas) {
            RentAreaEntity rentAreaEntity = rentAreaConverter.convertToEntity(entity, Long.parseLong(rentArea));
            rentAreaRepo.save(rentAreaEntity);
        }
    }

    @Override
    public void deleteByBuildingIdIn(List<Long> ids) {
        rentAreaRepo.deleteByBuildingIdIn(ids);
    }


}
