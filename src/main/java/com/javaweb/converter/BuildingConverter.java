package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.district;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.rentAreaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private rentAreaRepository rentAreaRepository;

    @Autowired
    private RentAreaConverter rentAreaConverter;


    public BuildingSearchResponse convertToDto (BuildingEntity entity){
        BuildingSearchResponse result= modelMapper.map(entity, BuildingSearchResponse.class);
        Map<String,String> districtString = district.type();
        String districtName = districtString.get(entity.getDistrict());
        result.setAddress(entity.getStreet() +","+ entity.getWard() +","+ districtName);
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
        List<String> type = Arrays.stream(entity.getTypeCode().trim().split(",")).collect(Collectors.toList());
        List<RentAreaEntity> rentAreas = entity.getRentArea();
        result.setTypeCode(type);
        result.setRentArea(rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(",")));
        return result;
    }

    public BuildingEntity convertToEntity(BuildingDTO dto){
        BuildingEntity result = modelMapper.map(dto, BuildingEntity.class);
        result.setTypeCode(removeAccent(dto.getTypeCode()));
        result.setRentArea(rentAreaConverter.convertToEntity(dto, result));
        return result;
 }
    public static String removeAccent(List<String> typeCodes) { return String.join(",", typeCodes); }

}
