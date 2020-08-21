package com.macro.mall.tiny.controller;

import cn.hutool.json.JSONObject;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.entity.User;
import com.macro.mall.tiny.service.UserService;
import com.macro.mall.tiny.vo.param.UserParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "费用api", description = "费用api description", tags = "财务")
//@RestController      //   @RestController = @Controller +  @ResponseBody
@Controller
@RequestMapping("/fee")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "登录以后返回token")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "name", value = "姓名", required = true, dataType = "String", paramType = "query"
            )
    })
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @GetMapping("/login")
    @ResponseBody
    public CommonResult login(@Validated UserParam param) {
        String token = userService.checkUser(param.getName(), param.getAge());
//        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", "tokenHead");
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "添加记录")
    @GetMapping("/add")
    public Object addUser() {
//            User user = new User("张三'特殊`符号", AgeEnum.TWO, 1);
//            user.setPhone(PhoneEnum.CUCC);
//            JSONObject result = new JSONObject();
//            result.put("result", userService.insert(user));
//            return result;
        return null;
    }

    @ApiOperation(value = "查询一条user记录")
    @GetMapping("/query")
    public CommonResult queryOneUser() {
        User user = userService.getUserById();
        JSONObject result = new JSONObject();
        result.put("result", user);
        return CommonResult.success(result);
    }
}
