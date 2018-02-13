package com.zyy.springcloud.api.api;


import com.zyy.springcloud.api.model.dto.request.user.UserAddDTO;
import com.zyy.springcloud.api.model.dto.response.BaseResponse;
import com.zyy.springcloud.api.model.dto.response.user.UserListResult;
import com.zyy.springcloud.api.model.dto.response.user.UserResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户API
 *
 * @author zhangyanyan
 * @date 2018/1/3
 */
@Api(value = "User", description = "用户中心API", protocols = "http")
public interface UserAPI {
    @ApiOperation(value = "查询用户信息", notes = "查询用户信息", protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "uid", required = true, paramType = "path", dataType = "Long")
    })
    @RequestMapping(value = "/user/{uid}", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<UserResult> queryById(@PathVariable("uid") Long uid);

    @ApiOperation(value = "查询用户信息", notes = "查询用户信息", protocols = "http")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "acount", value = "acount", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "mobile", value = "mobile", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = {"application/json"})
    BaseResponse<UserListResult> search(@RequestParam(value = "acount", required = false) String account,
                                        @RequestParam(value = "mobile", required = true) String mobile);

    @ApiOperation(value = "新增用户", notes = "新增用户", protocols = "http")
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    BaseResponse add(@Valid @RequestBody UserAddDTO addDTO);

}
