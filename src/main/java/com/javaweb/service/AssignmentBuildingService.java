package com.javaweb.service;

import java.util.List;


public interface AssignmentBuildingService {
    void assignStafftoBuilding(Long buildingId, List<Long> staffId);
}
