package net.ttcxy.tangtang.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 收藏页面相关接口
 * @author huanglei
 */
@Controller
@RequestMapping("cyun")
public class CyunController {

    @GetMapping
    public String toCyun(){
        return "page/cyun";
    }

}
