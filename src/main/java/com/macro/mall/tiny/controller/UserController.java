package com.macro.mall.tiny.controller;

import cn.hutool.json.JSONObject;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.entity.User;
import com.macro.mall.tiny.service.UserService;

import com.macro.mall.tiny.vo.param.NormalUserParam;
import com.macro.mall.tiny.vo.param.UserParam;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Api(value = "费用api", description = "普通用户api description", tags = "普通用户")
//@RestController      //   @RestController = @Controller +  @ResponseBody
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired(required = false)
    protected HttpServletRequest request;
    @Autowired(required = false)
    protected HttpServletResponse response;


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

    @ApiOperation(value = "添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "name", value = "姓名", required = true, dataType = "String", paramType = "query"
            ),
            @ApiImplicitParam(name = "age", value = "年龄", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "email", value = "邮箱", required = false, dataType = "String", paramType = "query")
    })
    @GetMapping("/add")
    @ResponseBody
    public Object addUser(@Validated NormalUserParam param) {
        //            User user = new User("张三'特殊`符号", AgeEnum.TWO, 1);
//            user.setPhone(PhoneEnum.CUCC);
//            JSONObject result = new JSONObject();
//            result.put("result", userService.insert(user));
//            return result;

        System.out.println(request.getParameter("name"));
        Boolean result = userService.save(new User().setName(param.getName()).setAge(param.getAge()).setEmail(param.getEmail()));
        if (result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", "add user ok");
            return jsonObject;
        }
        return null;
    }

    @ApiOperation(value = "添加用户1")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "name", value = "姓名", required = true, dataType = "String", paramType = "query"
            ),
            @ApiImplicitParam(name = "age", value = "年龄", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "email", value = "邮箱", required = false, dataType = "String", paramType = "query")
    })
    @GetMapping("/add1")
    @ResponseBody
    public Object addUser1(@Validated NormalUserParam param) {

        System.out.println(request.getParameter("name"));
        Boolean result = userService.save(new User().setName(param.getName()).setAge(param.getAge()).setEmail(param.getEmail()));
        if (result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", "add user ok");
            return CommonResult.success(jsonObject);
        }
        return null;
    }

    @ApiOperation(value = "查询一条user记录")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "name", value = "用户姓名", required = true, dataType = "String", paramType = "query")
            }
    )
    @GetMapping("/query")
    public CommonResult queryOneUser(@RequestParam("name") String name) {
        User user = userService.getUserById();
        JSONObject result = new JSONObject();
        result.put("result", user);
        return CommonResult.success(result);
    }
}
