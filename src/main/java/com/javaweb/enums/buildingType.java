package com.javaweb.enums;

import java.util.Map;
import java.util.TreeMap;

public enum buildingType {
    NGUYEN_CAN("Nguyên căn"),
    NOI_THAT("Nội thất"),
    TANG_TRET("Tầng trệt");

    private final String typeName;

    buildingType(String name) {
        this.typeName = name;
    }
    public static Map<String,String> type() {
        Map<String,String> map = new TreeMap<String,String>();
        for(buildingType t : buildingType.values()) {
            map.put(t.toString(),t.typeName);
        }
        return map;
    }

}
