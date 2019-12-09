package cn.mrmj.api.errer;

import cn.mrmj.constant.enums.ErrorCodeEnum;
import cn.mrmj.controller.BaseController;
import cn.mrmj.response.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by: mrmj
 * description:  token验证失败后的处理器
 * create time: 2019/12/9 11:58
 */
@RestController
@RequestMapping("/")
public class DispatcherController extends BaseController {

    @PostMapping("/token/error")
    public Result<Object> error() {
        return Result.fail(ErrorCodeEnum.ACCESS_TOKEN_ERROR);
    }

    @PostMapping("/auth/error")
    public Result<Object> authError() {
        return Result.fail(ErrorCodeEnum.NOT_AUTH_MENU);
    }

    @PostMapping("/login/error")
    public Result<Object> loginError() {
        return Result.fail(ErrorCodeEnum.ONLY_LOGIN_ERROR);
    }


}