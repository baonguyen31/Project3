package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RentAreaConverter {
    @Autowired
    private ModelMapper modelMapper;

    public RentAreaEntity convertToEntity(BuildingEntity buildingEntity, Long value) {
//        BuildingEntity buildingEntity = modelMapper.map(dto, BuildingEntity.class);
        RentAreaEntity rentArea = new RentAreaEntity();
        rentArea.setValue(value);
        rentArea.setBuilding(buildingEntity);
        return rentArea;
    }

    public List<RentAreaEntity> convertToEntity(BuildingDTO dto, BuildingEntity buildingEntity) {
        String[] rentArea = dto.getRentArea().split(",");
        List<RentAreaEntity> rentAreaList = new ArrayList<>();

        for(String rentAreaStr : rentArea) {
            rentAreaList.add(convertToEntity(buildingEntity,Long.valueOf(rentAreaStr)));
        }
        return rentAreaList;
    }


}
