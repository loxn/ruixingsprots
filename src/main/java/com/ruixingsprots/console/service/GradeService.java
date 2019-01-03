/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ruixingsprots.console.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruixingsprots.console.dao.GradeMapper;
import com.ruixingsprots.console.dao.KindergartenMapper;
import com.ruixingsprots.console.dao.StudentMapper;
import com.ruixingsprots.console.exception.ServiceException;
import com.ruixingsprots.console.pojo.po.Grade;
import com.ruixingsprots.console.pojo.po.GradeExample;
import com.ruixingsprots.console.pojo.po.Kindergarten;
import com.ruixingsprots.console.pojo.po.Student;
import com.ruixingsprots.console.pojo.po.StudentExample;

/**
 * Created by v_luoxin on 2018-12-28 15:35
 */
@Service
@Transactional
public class GradeService {
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private KindergartenMapper kindergartenMapper;

    public List<Grade> list(Integer kId) {
        GradeExample example = new GradeExample();
        if (kId != null) {
            example.createCriteria().andKIdEqualTo(kId);
        }
        return gradeMapper.selectByExample(example);
    }

    public void save(Grade recoder) {
        GradeExample example = new GradeExample();
        example.createCriteria().andNameEqualTo(recoder.getName()).andKIdEqualTo(recoder.getkId());
        List<Grade> lists = gradeMapper.selectByExample(example);
        if (lists != null && lists.size() > 0) {
            Grade grade = lists.get(0);
            if (recoder.getId() == null || !grade.getId().equals(recoder.getId())) {
                throw new ServiceException("班级已存在！");
            }
        }

        // 查询kName
        Kindergarten kindergarten = kindergartenMapper.selectByPrimaryKey(recoder.getkId());
        recoder.setkName(kindergarten.getName());
        if (recoder.getId() != null) {
            gradeMapper.updateByPrimaryKeySelective(recoder);
            // 如果班级名称改变了，更新学生数据
            StudentExample studentExample = new StudentExample();
            studentExample.createCriteria().andCIdEqualTo(recoder.getId());
            Student student = new Student();
            student.setcName(recoder.getName());
            studentMapper.updateByExampleSelective(student, studentExample);
        } else {
            recoder.setCreateTime(new Date());
            gradeMapper.insertSelective(recoder);
        }
    }

    public void del(Integer[] ids) {
        for (Integer id : ids) {
            gradeMapper.deleteByPrimaryKey(id);
            // 级联删除学生
            StudentExample example = new StudentExample();
            example.createCriteria().andCIdEqualTo(id);
            studentMapper.deleteByExample(example);
        }
    }

    public List<Grade> combobox(Integer kId) {
        GradeExample example = new GradeExample();
        example.createCriteria().andKIdEqualTo(kId);
        return gradeMapper.selectByExample(example);
    }
}
