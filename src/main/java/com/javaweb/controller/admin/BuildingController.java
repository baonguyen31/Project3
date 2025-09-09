package com.javaweb.controller.admin;



import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.buildingType;
import com.javaweb.enums.district;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.custom.impl.BuildingRepositoryImpl;
import com.javaweb.service.impl.BuildingService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buidingSearch, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/admin/building/list");
        mav.addObject("modelSearch",buidingSearch);
        //Lấy từ service
        List<BuildingSearchResponse> responseList = buildingService.getBuildings();
        //Lấy trức tiếp từ reposirory
//        List<BuildingEntity> listBuilding = buildingRepository.findAll();
//        List<BuildingSearchResponse> responseList = buildingConverter.convertToDto(listBuilding);
        //Gán cứng dữ liệu
//        BuildingSearchResponse item = new BuildingSearchResponse();
//        item.setId(5L);
//        item.setName("Tòa nhà");
//        item.setAddress("123,PVH");
//        item.setNumberOfBasement(3L);
//        item.setManagerName("Hào");
//        item.setManagerPhone("09854321");
//        item.setFloorArea(123L);
//        item.setEmptyArea("");
//        item.setRentArea("123");
//        item.setBrokerageFee(12.1);
//        responseList.add(item);
//        System.out.println("List building: " + responseList.size());
//        responseList.forEach(b -> System.out.println(b.getRentArea()));
        mav.addObject("buildingList", responseList);
        mav.addObject("listStaffs", userService.getStaffs());
        mav.addObject("districtList", district.type());
        mav.addObject("typeCode", buildingType.type());
        return mav;
    }

    @RequestMapping(value = "/admin/building-edit", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@ModelAttribute("buildingEdit") BuildingDTO buildingDTO, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/admin/building/edit");
        mav.addObject("districtList", district.type());
        mav.addObject("typeCode", buildingType.type());
        return mav;
    }

    @RequestMapping(value = "/admin/building-edit-{id}", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@PathVariable ("id") Long Id){
        ModelAndView mav = new ModelAndView("/admin/building/edit");
        BuildingDTO buildingDTO = buildingService.findById(Id);
        buildingDTO.setId(Id);
        mav.addObject("buildingEdit",buildingDTO);
        mav.addObject("districtList", district.type());
        mav.addObject("typeCode", buildingType.type());
        return mav;
    }
}
