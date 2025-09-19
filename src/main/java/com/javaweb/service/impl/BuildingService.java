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
import com.javaweb.repository.rentAreaRepository;
import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.File;
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
    private AssignmentBuildingService assignmentBuildingService;
    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;

    @Autowired
    private rentAreaRepository rentAreaRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RentAreaService rentAreaService;

    @Override
    public List<BuildingSearchResponse> getBuildings() {
        List<BuildingEntity> getList = buildingRepo.findAll();
        List<BuildingSearchResponse> buildings = buildingConverter.convertToDto(getList);
        return buildings;
    }



    @Override
    @Transactional
    public BuildingDTO addAndUpdate(BuildingDTO dto) {
        Long id = dto.getId();
        BuildingEntity buildingEntity = modelMapper.map(dto, BuildingEntity.class);
        buildingEntity.setTypeCode(removeAccent(dto.getTypeCode()));
        buildingRepo.save(buildingEntity);
        dto.setId(buildingEntity.getId());
        if(StringUtils.check(dto.getRentArea())) rentAreaService.addRentArea(dto);
        return dto;
    }

//    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
//        String path = "/building/" + buildingDTO.getImageName();
//        if (null != buildingDTO.getImageBase64()) {
//            if (null != buildingEntity.getImage()) {
//                if (!path.equals(buildingEntity.getImage())) {
//                    File file = new File("C:/home/office" + buildingEntity.getImage());
//                    file.delete();
//                }
//            }
//            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
//            uploadFileUtils.writeOrUpdate(path, bytes);
//            buildingEntity.setImage(path);
//        }
//    }


    @Override
    public BuildingDTO findById(Long Id) {
        BuildingEntity entity = buildingRepo.findById(Id).get();
        BuildingDTO dto = buildingConverter.ToDto(entity);
        return dto;
    }

//    @Override
//    @Transactional
//    public BuildingDTO update(BuildingDTO dto, Long Id) {
//        BuildingEntity building = buildingConverter.convertToEntity(dto);
//        rentAreaRepo.deleteRentAreaEntityByBuilding_Id(Id);
//        BuildingEntity savedEntity = buildingRepo.save(building);
//        return buildingConverter.ToDto(savedEntity);
//    }

    @Override
    @Transactional
    public void deleteByBuidling(List<Long> Id) {
        rentAreaService.deleteByBuildingIdIn(Id);
        assignmentBuildingService.deleteByBuildingIds(Id);
        buildingRepo.deleteByIdIn(Id);
    }

    @Override
    public List<BuildingSearchResponse> findAllBuildings(BuildingSearchRequest request, Pageable pageable) {
        List<String> typeCode = request.getTypeCode();
        BuildingSearchBuilder builder = buildingSearchBuilderConverter.toBuildingSearchConverter(request,typeCode);
        List<BuildingEntity> entity = buildingRepo.findAll(builder, pageable);
//        List<BuildingSearchResponse> dtoList = buildingConverter.convertToDto(entity);
        return  buildingConverter.convertToDto(entity);
    }

    @Override
    public int countTotalItems(BuildingSearchRequest request) {
        List<String> typeCode = request.getTypeCode();
        BuildingSearchBuilder builder = buildingSearchBuilderConverter.toBuildingSearchConverter(request, typeCode);
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

    public String removeAccent(List<String> typeCodes) {
        String result = String.join(",", typeCodes);
        return result;
    }


}
