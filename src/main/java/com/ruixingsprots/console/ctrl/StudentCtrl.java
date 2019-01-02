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
import com.ruixingsprots.console.pojo.po.Student;
import com.ruixingsprots.console.pojo.vo.AjaxResult;
import com.ruixingsprots.console.pojo.vo.DatagridResult;
import com.ruixingsprots.console.service.StudentService;

/**
 * Created by v_luoxin on 2018-12-28 15:33
 */
@Controller
@RequestMapping(value = "/student")
public class StudentCtrl {

    @Autowired
    private StudentService studentService;

    @GetMapping("/username")
    @ResponseBody
    public AjaxResult username(String name, Integer id) {
        return AjaxResult.successResult(studentService.fetchUsername(name, id));
    }

    @PostMapping("/list")
    @ResponseBody
    public DatagridResult list(Integer page, Integer rows, String name, Integer kId, Integer cId) {
        PageHelper.startPage(page, rows);
        PageInfo pageInfo = new PageInfo(studentService.list(name, kId, cId));
        return new DatagridResult(pageInfo.getTotal(), pageInfo.getList());
    }

    @PostMapping("/save")
    @ResponseBody
    public AjaxResult save(Student recoder) {
        studentService.save(recoder);
        return AjaxResult.successResult("保存成功");
    }

    @PostMapping("/del")
    @ResponseBody
    public AjaxResult del(Integer[] ids) {
        studentService.del(ids);
        return AjaxResult.successResult("删除成功");
    }

    @GetMapping("")
    public String manager() {
        return "student";
    }

}
