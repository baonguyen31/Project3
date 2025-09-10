package com.javaweb.converter;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.utils.MapUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchConverter(BuildingSearchRequest request, List<String> typeCode) {
        BuildingSearchBuilder builldingSearchbuilder= new BuildingSearchBuilder.Builder()
                .setName(MapUtils.getObject(request.getName(), String.class))
                .setNumberOfBasement(MapUtils.getObject( request.getNumberOfBasement(), Long.class))
//                .setAddress(MapUtils.getObject( "address", String.class))
                .setFloorarea(MapUtils.getObject( request.getFloorArea(), Long.class))
                .setStreet(MapUtils.getObject( request.getStreet(), String.class))
                .setWard(MapUtils.getObject( request.getWard(), String.class))
                .setDistrict(MapUtils.getObject( request.getDistrict(), String.class))
                .setLevel(MapUtils.getObject( request.getLevel(), String.class))
                .setDirection(MapUtils.getObject( request.getDirection(), String.class))
                .setTypeCode(typeCode)
                .setManagername(MapUtils.getObject( request.getManagerName(), String.class))
                .setManagerphone(MapUtils.getObject( request.getManagerPhone(), String.class))
                .setRentAreaFrom(MapUtils.getObject( request.getAreaFrom(), Long.class))
                .setRentAreaTo(MapUtils.getObject( request.getAreaTo(), Long.class))
                .setRentPriceFrom(MapUtils.getObject( request.getRentPriceFrom(), Long.class))
                .setRentPriceTo(MapUtils.getObject( request.getRentPriceTo(), Long.class))
                .setStaffid(MapUtils.getObject( request.getStaffId(), Long.class))
                .build();
        return builldingSearchbuilder;
    }
}