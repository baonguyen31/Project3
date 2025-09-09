package com.javaweb.api.admin;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.impl.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/building")
public class BuildingAPI {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private AssignmentBuildingService assignmentBuildingService;
    @Autowired
    private BuildingRepository buildingRepository;

    @PostMapping
    public BuildingDTO addAndUpdateBuilding(@RequestBody BuildingDTO dto){
        if (dto.getId() != null){
            return buildingService.update(dto, dto.getId());
        }else {
            return buildingService.insert(dto);
        }
//        return dto;
    }

    @DeleteMapping(value="/{ids}")
    public void deleteBuilding(@PathVariable List<Long> ids){
        buildingService.deleteByBuidling(ids);
    }

    @GetMapping("/{buildingId}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long buildingId){
        ResponseDTO responseDTO = buildingService.listStaffs(buildingId);
        return responseDTO;
    }
    @PostMapping("/{buildingId}/staffs")
    public void addStaff(@PathVariable Long buildingId,
                                @RequestBody List<Long> staffIds){
        assignmentBuildingService.assignStafftoBuilding(buildingId, staffIds);
    }

}
