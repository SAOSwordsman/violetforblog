package com.mine.violet.controller;



import com.mine.violet.commonutils.R;
import com.mine.violet.entity.CrmBanner;
import com.mine.violet.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/violetcms/bannerFront")
public class BannerFrontController {


    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取首页banner")
    @GetMapping("/getAllBanner")
    public R index() {
        List<CrmBanner> list = bannerService.selectAllList();
        return R.ok().data("list", list);
    }

}
