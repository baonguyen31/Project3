package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentAreaConverter {
    @Autowired
    private ModelMapper modelMapper;

    public RentAreaEntity convertToEntity(BuildingDTO dto, Long value) {
        BuildingEntity buildingEntity = modelMapper.map(dto, BuildingEntity.class);
        buildingEntity.setId(dto.getId());
        RentAreaEntity rentArea = new RentAreaEntity();
        rentArea.setValue(value);
        rentArea.setBuilding(buildingEntity);
        return rentArea;
    }
}
