package net.ttcxy.tang.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import net.ttcxy.tang.entity.UserDto;
import net.ttcxy.tang.model.User;
import net.ttcxy.tang.api.CommonResult;
import net.ttcxy.tang.service.AuthDetailsService;
import net.ttcxy.tang.service.UserService;
import net.ttcxy.tang.service.impl.FansServiceImpl;
import net.ttcxy.tang.util.StringProUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户相关操作
 * @author huanglei
 */
@RestController
public class UserController {

    @Autowired
    private FansServiceImpl fansService;

    @Autowired
    private AuthDetailsService authDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("userinfo")
    @ResponseBody
    public CommonResult updateUser(@RequestBody UserDto userDto){
        String id = authDetailsService.getUser().getId();
        String nickname = userDto.getNickname();

        ReUtil.contains("dfsdfsd","");

        if (StrUtil.isNotBlank(nickname)){
            int length = StringProUtil.byteSize(nickname);
            if (length > 16 || length < 4){
                return CommonResult.failed("昵称长度：汉字 2 ~ 8,字母 4 ~ 16");
            }
        }else{
            return CommonResult.failed("昵称长度：汉字 2 ~ 8,字母 4 ~ 16");
        }

        String signature = userDto.getSignature();
        if (StrUtil.isNotBlank(signature)){
            int length = StringProUtil.byteSize(signature);
            if (length > 50){
                return CommonResult.failed("签名长度为50个之母或25个汉字");
            }
        }
        userDto.setId(id);

        User user = new User();
        BeanUtil.copyProperties(userDto,user);
        int count = userService.updateUser(user);
        if (count > 0){
            UserDto uu = authDetailsService.getUser();
            uu.setNickname(nickname);
            uu.setSignature(signature);
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @GetMapping("user/gz")
    @ResponseBody
    public CommonResult gz(){

        String userId = authDetailsService.getUser().getId();
        List<UserDto> fansList = fansService.selectFansList(userId);
        return CommonResult.success(fansList);
    }


    @PostMapping("list")
    @ResponseBody
    public CommonResult<List<User>> listUser(@RequestParam(defaultValue = "1") Integer page){
        return CommonResult.success(userService.listUser(page));
    }




}