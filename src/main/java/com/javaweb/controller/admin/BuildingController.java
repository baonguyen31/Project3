package com.javaweb.controller.admin;



import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.constant.SystemConstant;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
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
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private BuildingRepository buildingRepo;

    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;

    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute(SystemConstant.MODEL)  BuildingSearchRequest buidingSearch,
                                     HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/admin/building/list");
        DisplayTagUtils.of(request, buidingSearch);
        mav.addObject("modelSearch",buidingSearch);
        //Lấy từ service
        List<String> typeCode = buidingSearch.getTypeCode();
        List<BuildingSearchResponse> responseList = buildingService.findAllBuildings(buidingSearch, PageRequest.of(buidingSearch.getPage() - 1, buidingSearch.getMaxPageItems()));
        BuildingSearchBuilder builder = buildingSearchBuilderConverter.toBuildingSearchConverter(buidingSearch, typeCode);
        int total = buildingService.countTotalItems(builder);
        buidingSearch.setTotalItems(total);
        buidingSearch.setListResult(responseList);
        mav.addObject(SystemConstant.MODEL, buidingSearch);
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
