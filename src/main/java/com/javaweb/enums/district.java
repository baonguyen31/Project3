package com.javaweb.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public enum district {
    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_3("Quận 3"),
    QUAN_4("Quận 4"),
    QUAN_5("Quận 5"),
    QUAN_6("Quận 6"),
    QUAN_7("Quận 7"),
    QUAN_8("Quận 8"),
    QUAN_9("Quận 9"),
    QUAN_10("Quận 10"),
    QUAN_11("Quận 11"),
    QUAN_12("Quận 12"),
    QUAN_BINH_THANH("Quận Bình Thạnh"),
    QUAN_TAN_BINH("Quận Tân Bình");

    private final String districtName;

    district(String districtName) {
        this.districtName = districtName;
    }

    public static Map<String, String> type(){
        Map<String, String> districts = new TreeMap<>();
        for(district it : district.values()){
            districts.put(it.toString(), it.districtName);
        }
        return districts;
    }
}