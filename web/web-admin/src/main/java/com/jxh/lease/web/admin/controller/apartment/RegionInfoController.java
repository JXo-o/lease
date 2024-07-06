package com.jxh.lease.web.admin.controller.apartment;

import com.jxh.lease.common.result.Result;
import com.jxh.lease.model.entity.CityInfo;
import com.jxh.lease.model.entity.DistrictInfo;
import com.jxh.lease.model.entity.ProvinceInfo;
import com.jxh.lease.web.admin.service.CityInfoService;
import com.jxh.lease.web.admin.service.DistrictInfoService;
import com.jxh.lease.web.admin.service.ProvinceInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "地区信息管理")
@RestController
@RequestMapping("/admin/region")
public class RegionInfoController {

    private final ProvinceInfoService provinceInfoService;
    private final CityInfoService cityInfoService;
    private final DistrictInfoService districtInfoService;

    public RegionInfoController(
            ProvinceInfoService provinceInfoService,
            CityInfoService cityInfoService,
            DistrictInfoService districtInfoService
    ) {
        this.provinceInfoService = provinceInfoService;
        this.cityInfoService = cityInfoService;
        this.districtInfoService = districtInfoService;
    }

    @Operation(summary = "查询省份信息列表")
    @GetMapping("province/list")
    public Result<List<ProvinceInfo>> listProvince() {
        return Result.ok(provinceInfoService.list());
    }

    @Operation(summary = "根据省份id查询城市信息列表")
    @GetMapping("city/listByProvinceId")
    public Result<List<CityInfo>> listCityInfoByProvinceId(@RequestParam Long id) {
        return Result.ok(cityInfoService.lambdaQuery().eq(CityInfo::getProvinceId, id).list());
    }

    @GetMapping("district/listByCityId")
    @Operation(summary = "根据城市id查询区县信息")
    public Result<List<DistrictInfo>> listDistrictInfoByCityId(@RequestParam Long id) {
        return Result.ok(districtInfoService.lambdaQuery().eq(DistrictInfo::getCityId, id).list());
    }

}
