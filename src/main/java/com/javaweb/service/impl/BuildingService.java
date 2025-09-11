package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssingmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.custom.impl.BuildingRepositoryImpl;
import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.IBuildingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingRepository buildingRepo;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AssignmentBuildingService assignmentBuildingService;

    @Autowired
    private AssingmentBuildingRepository assingmentBuildingRepo;

    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;

    @Override
    public List<BuildingSearchResponse> getBuildings() {
        List<BuildingEntity> getList = buildingRepo.findAll();
        List<BuildingSearchResponse> buildings = buildingConverter.convertToDto(getList);
        return buildings;
    }



    @Override
    @Transactional
    public BuildingDTO insert(BuildingDTO dto) {
        BuildingEntity savedEntity2 = buildingConverter.convertToEntity(dto);
        BuildingEntity savedEntity = buildingRepo.save(savedEntity2);
//        BuildingEntity entity = modelMapper.map(dto, BuildingEntity.class);
//        BuildingDTO result = buildingConverter.ToDto(savedEntity);
        return buildingConverter.ToDto(savedEntity);
    }

    @Override
    public BuildingDTO findById(Long Id) {
        BuildingEntity entity = buildingRepo.findById(Id).get();
        BuildingDTO dto = buildingConverter.ToDto(entity);
        return dto;
    }

    @Override
    public BuildingDTO update(BuildingDTO dto, Long Id) {
//        BuildingEntity findbyid = buildingRepo.findById(Id).get();
        BuildingEntity savedEntity = buildingRepo.save(buildingConverter.convertToEntity(dto));
        return buildingConverter.ToDto(savedEntity);
    }

    @Override
    @Transactional
    public void deleteByBuidling(List<Long> Id) {
        for(Long buildingId: Id){
            if(assingmentBuildingRepo.existsById(buildingId)){
                assingmentBuildingRepo.deleteAssignmentBuildingEntityByBuildingId(buildingId);
            }
        }
        buildingRepo.deleteByIdIn(Id);
    }

    @Override
    public List<BuildingSearchResponse> findAllBuildings(BuildingSearchRequest request, Pageable pageable) {
        List<String> typeCode = request.getTypeCode();
        BuildingSearchBuilder builder = buildingSearchBuilderConverter.toBuildingSearchConverter(request,typeCode);
        List<BuildingEntity> entity = buildingRepo.findAll(builder, pageable);
        List<BuildingSearchResponse> dtoList = buildingConverter.convertToDto(entity);
        return dtoList;
    }

    @Override
    public int countTotalItems(BuildingSearchBuilder builder) {
        return buildingRepo.countTotalItem(builder);
    }

    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        BuildingEntity building = buildingRepo.findById(buildingId).get();
        List<UserEntity> getStaff = userRepo.findByStatusAndRoles_Code(1, "STAFF");
        List<AssignmentBuildingEntity> findStaff = building.getAssignBuidling();
        List<UserEntity> staff = findStaff.stream().map(AssignmentBuildingEntity::getStaff).collect(Collectors.toList());
        List<StaffResponseDTO> responseDTOs = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for (UserEntity it : getStaff) {
            StaffResponseDTO responseStaff = new StaffResponseDTO();
            responseStaff.setFullName(it.getFullName());
            responseStaff.setStaffId(it.getId());
            if (staff.contains(it)) {
                responseStaff.setChecked("checked");
            } else {
                responseStaff.setChecked("");
            }
            responseDTOs.add(responseStaff);
        }
        responseDTO.setData(responseDTOs);
        return responseDTO;
    }


}
