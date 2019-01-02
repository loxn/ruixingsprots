/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.ruixingsprots.console.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ruixingsprots.console.dao.GradeMapper;
import com.ruixingsprots.console.dao.KindergartenMapper;
import com.ruixingsprots.console.dao.StudentMapper;
import com.ruixingsprots.console.exception.ServiceException;
import com.ruixingsprots.console.pojo.po.Grade;
import com.ruixingsprots.console.pojo.po.GradeExample;
import com.ruixingsprots.console.pojo.po.Kindergarten;
import com.ruixingsprots.console.pojo.po.KindergartenExample;
import com.ruixingsprots.console.pojo.po.Student;
import com.ruixingsprots.console.pojo.po.StudentExample;

/**
 * Created by v_luoxin on 2018-12-28 15:35
 */
@Service
@Transactional
public class KindergartenService {
    @Autowired
    private KindergartenMapper kindergartenMapper;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private StudentMapper studentMapper;

    public List<Kindergarten> list(String name) {
        KindergartenExample example = new KindergartenExample();
        if (!StringUtils.isEmpty(name)) {
            example.createCriteria().andNameLike("%" + name + "%");
        }
        return kindergartenMapper.selectByExample(example);
    }

    public void save(Kindergarten recoder) {
        KindergartenExample example = new KindergartenExample();
        example.createCriteria().andNameEqualTo(recoder.getName());
        List<Kindergarten> lists = kindergartenMapper.selectByExample(example);
        if (lists != null && lists.size() > 0) {
            Kindergarten kindergarten = lists.get(0);
            if (recoder.getId() == null || !kindergarten.getId().equals(recoder.getId())) {
                throw new ServiceException("园所已存在！");
            }
        }

        if (recoder.getId() != null) {
            kindergartenMapper.updateByPrimaryKeySelective(recoder);
            // 如果园所名称改变，更新班级和学生的数据
            GradeExample gradeExample = new GradeExample();
            gradeExample.createCriteria().andKIdEqualTo(recoder.getId());
            Grade grade = new Grade();
            grade.setkName(recoder.getName());
            gradeMapper.updateByExampleSelective(grade, gradeExample);
            // 更新学生数据
            StudentExample studentExample = new StudentExample();
            studentExample.createCriteria().andKIdEqualTo(recoder.getId());
            Student student = new Student();
            student.setkName(recoder.getName());
            studentMapper.updateByExampleSelective(student, studentExample);
        } else {
            recoder.setCreateTime(new Date());
            kindergartenMapper.insertSelective(recoder);
        }
    }

    public void del(Integer[] ids) {
        for (Integer id : ids) {
            kindergartenMapper.deleteByPrimaryKey(id);
            // 级联删除班级和
            GradeExample gradeExample = new GradeExample();
            gradeExample.createCriteria().andKIdEqualTo(id);
            gradeMapper.deleteByExample(gradeExample);
            //学生
            StudentExample example = new StudentExample();
            example.createCriteria().andKIdEqualTo(id);
            studentMapper.deleteByExample(example);
        }
    }

    public List<Kindergarten> combobox() {
        return kindergartenMapper.selectByExample(null);
    }
}
