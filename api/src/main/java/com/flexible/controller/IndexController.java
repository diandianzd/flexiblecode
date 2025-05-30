package com.flexible.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping("/index")
    @ResponseBody
    public Map<String, String> index() {
        Map map = new HashMap<String, String>();
        map.put("北京", "北方城市");
        map.put("深圳", "南方城市");

        return map;
    }
}
