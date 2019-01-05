/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.ruixingsprots.console.ctrl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruixingsprots.console.common.Const;
import com.ruixingsprots.console.dao.UserMapper;
import com.ruixingsprots.console.pojo.po.User;
import com.ruixingsprots.console.pojo.po.UserExample;
import com.ruixingsprots.console.pojo.vo.AjaxResult;

/**
 * Created by v_luoxin on 2018-12-15 12:53
 */
@Controller
public class IndexCtrl extends BaseCtrl {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping(value = "doLogin")
    @ResponseBody
    public AjaxResult doLogin(@RequestParam String loginname, @RequestParam String password, HttpSession session) {
        if (StringUtils.isEmpty(loginname) || StringUtils.isEmpty(password)) {
            return AjaxResult.errorResult("usererror");
        }

        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(loginname).andPwdEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);
        if (users == null || users.size() == 0) {
            return AjaxResult.errorResult("usererror");
        }
        session.setAttribute(Const.LOGIN_USER, users.get(0));
        return AjaxResult.emptySuccessResult();
    }

    @GetMapping("index")
    public ModelAndView index(Map<String, Object> map, HttpSession session) {
        map.put("currentUser", ((User) session.getAttribute(Const.LOGIN_USER)).getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpSession session) {
        session.removeAttribute(Const.LOGIN_USER);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("tab")
    public ModelAndView tab() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("common/tab");
        return modelAndView;
    }

}
