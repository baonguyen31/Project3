package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BuildingSearchResponse convertToDto (BuildingEntity entity){
        BuildingSearchResponse result= modelMapper.map(entity, BuildingSearchResponse.class);
        result.setAddress(entity.getStreet() +","+ entity.getWard() +","+ entity.getDistrict());
        List<RentAreaEntity> rentAreas = entity.getRentArea();
        result.setRentArea(rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(",")));
        return result;
    }

    public List<BuildingSearchResponse> convertToDto (List<BuildingEntity> entities){
//        List<BuildingSearchResponse> result = new ArrayList<>();
//        BuildingSearchResponse buildingSearchResponse  =  modelMapper.map(entity, BuildingSearchResponse.class);
//        result.add(buildingSearchResponse);
        return entities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public BuildingDTO ToDto (BuildingEntity entity){
        BuildingDTO result = modelMapper.map(entity, BuildingDTO.class);
        List<RentAreaEntity> rentAreas = entity.getRentArea();
        result.setRentArea(rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(",")));
        return result;
    }

    public BuildingEntity convertToEntity (BuildingDTO dto){
        BuildingEntity result = modelMapper.map(dto, BuildingEntity.class);
        List<String> typeCodes = new ArrayList<>();
        result.setTypeCode(dto.getTypeCode().stream().map(typeCode -> typeCode.toString()).collect(Collectors.joining(",")));
        if (dto.getRentArea() != null && !dto.getRentArea().isEmpty()) {
            List<RentAreaEntity> rentAreas = Arrays.stream(dto.getRentArea().split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Long::valueOf) // ép về số
                    .map(value -> {
                        RentAreaEntity rentArea = new RentAreaEntity();
                        rentArea.setValue(value);
                        rentArea.setBuilding(result);// gắn quan hệ
                        return rentArea;
                    })
                    .collect(Collectors.toList());

            result.setRentArea(rentAreas);
        }

        return result;
    }


//    public UserEntity convertToEntity (UserDTO dto){
//        UserEntity result = modelMapper.map(dto, UserEntity.class);
//        return result;
//    }
}
