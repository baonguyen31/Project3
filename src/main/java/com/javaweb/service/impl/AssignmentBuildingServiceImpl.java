//package com.javaweb.service.impl;
//
//import com.javaweb.converter.BuildingConverter;
//import com.javaweb.converter.UserConverter;
//import com.javaweb.entity.AssignmentBuildingEntity;
//import com.javaweb.entity.BuildingEntity;
//import com.javaweb.entity.UserEntity;
//import com.javaweb.repository.AssingmentBuildingRepository;
//import com.javaweb.repository.BuildingRepository;
//import com.javaweb.repository.UserRepository;
//import com.javaweb.service.AssignmentBuildingService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//public class AssignmentBuildingServiceImpl implements AssignmentBuildingService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private AssingmentBuildingRepository assingmentBuildingRepository;
//
//    @Autowired
//    private BuildingRepository buildingRepository;
//
//    @Autowired
//    private BuildingConverter buildingConverter;
//
//    @Autowired
//    private UserConverter userConverter;
//
//
//    @Override
//    @Transactional
//    public void assignStafftoBuilding(Long buildingId, List<Long> staffId) {
//        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
////        BuildingDTO building = buildingConverter.ToDto(buildingEntity);
//        assingmentBuildingRepository.deleteAssignmentBuildingEntityByBuilding_Id(buildingId);
//        for(Long id : staffId) {
//            UserEntity userEntity = userRepository.findOneById(id);
//            AssignmentBuildingEntity assign = new AssignmentBuildingEntity();
//
////            assign.setBuildingId(building.getId());
//            assign.setBuilding(buildingEntity);
//            assign.setStaff(userEntity);
//            assingmentBuildingRepository.save(assign);
//        }
//    }
//
//    @Override
//    public void deleteByBuildingIds(List<Long> Id) {
//        assingmentBuildingRepository.deleteByBuildingIdIn(Id);
//    }
//}
