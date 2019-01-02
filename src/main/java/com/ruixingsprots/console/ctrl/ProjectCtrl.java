/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ruixingsprots.console.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruixingsprots.console.pojo.po.Project;
import com.ruixingsprots.console.pojo.vo.AjaxResult;
import com.ruixingsprots.console.pojo.vo.DatagridResult;
import com.ruixingsprots.console.service.ProjectService;

/**
 * Created by v_luoxin on 2018-12-28 15:33
 */
@Controller
@RequestMapping(value = "/project")
public class ProjectCtrl {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/list")
    @ResponseBody
    public DatagridResult list(Integer page, Integer rows, String name) {
        PageHelper.startPage(page, rows);
        PageInfo pageInfo = new PageInfo(projectService.list(name));
        return new DatagridResult(pageInfo.getTotal(), pageInfo.getList());
    }

    @PostMapping("/save")
    @ResponseBody
    public AjaxResult save(Project recoder) {
        projectService.save(recoder);
        return AjaxResult.successResult("保存成功");
    }

    @PostMapping("/del")
    @ResponseBody
    public AjaxResult del(Integer[] ids) {
        projectService.del(ids);
        return AjaxResult.successResult("删除成功");
    }

    @GetMapping("")
    public String manager() {
        return "project";
    }

}
