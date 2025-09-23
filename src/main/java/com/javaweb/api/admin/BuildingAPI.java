package com.javaweb.api.admin;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.BuildingRepository;
//import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.impl.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value="/api/building")
public class BuildingAPI {

    @Autowired
    private BuildingService buildingService;

//    @Autowired
//    private AssignmentBuildingService assignmentBuildingService;
    @Autowired
    private BuildingRepository buildingRepository;

    @PostMapping
    public ResponseEntity<BuildingDTO> addAndUpdateBuilding(@RequestBody BuildingDTO dto){
        return ResponseEntity.ok(buildingService.addAndUpdate(dto));
//        return dto;
    }

    @DeleteMapping(value="/{ids}")
    public void deleteBuilding(@PathVariable List<Long> ids){
        buildingService.deleteByBuidling(ids);
    }

//    @GetMapping("/search")
//    public List<BuildingSearchResponse> searchBuilding(@RequestParam Map<String, Object> params,
//                                                       @RequestParam List<String> typeCode) {
////        BuildingSearchBuilder searchBuilder = buildingService.findAllBuildings(params, typeCode);
////        return buildingService.findAllBuildings(params, typeCode);
//    }
    @GetMapping("/{buildingId}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long buildingId){
        ResponseDTO responseDTO = buildingService.listStaffs(buildingId);
        return responseDTO;
    }
    @PostMapping("/{buildingId}/staffs")
    public void addStaff(@PathVariable Long buildingId,
                         @RequestBody List<Long> staffIds){
//        assignmentBuildingService.assignStafftoBuilding(buildingId, staffIds);
        buildingService.assignmentStafftoBuiling(buildingId, staffIds);
    }

}
