package cn.mrmj.api.admin.pd;

import cn.mrmj.controller.BaseController;
import cn.mrmj.response.Result;
import cn.mrmj.service.pd.CountryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "国家字典admin类")
@RequestMapping("admin/country/api")
@RestController
public class CountryAdminController extends BaseController {
    @Autowired
    private CountryService countryService;

    @PostMapping("v1/selectAllContinent")
    @ApiOperation("admin端查询所有的洲")
    public Result selectAllContinent() {
        return countryService.selectAllContinent();
    }

}
