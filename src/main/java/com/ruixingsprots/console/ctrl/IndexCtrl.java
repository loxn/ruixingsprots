/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.ruixingsprots.console.ctrl;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruixingsprots.console.dao.UserMapper;
import com.ruixingsprots.console.pojo.po.UserExample;

/**
 * Created by v_luoxin on 2018-12-15 12:53
 */
@Controller
public class IndexCtrl {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "doLogin")
    public String login(@RequestParam String username, @RequestParam String password,
                        Map<String, Object> map, HttpSession session) {
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andNameEqualTo(username).andPwdEqualTo(password);
            long count = userMapper.countByExample(userExample);
            if (count > 0) {
                session.setAttribute("LOGIN_USER", username);
                return "redirect:/index";
            }
        }
        map.put("msg", "用户名或密码错误");
        return "/login";
    }

    @GetMapping("index")
    public String index(Map<String, Object> map, HttpSession session) {
        map.put("currentUser", session.getAttribute("LOGIN_USER"));
        return "index";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("LOGIN_USER");
        return "login";
    }

}
