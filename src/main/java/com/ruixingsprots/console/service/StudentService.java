/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ruixingsprots.console.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ruixingsprots.console.dao.StudentMapper;
import com.ruixingsprots.console.pojo.po.Student;
import com.ruixingsprots.console.pojo.po.StudentExample;
import com.ruixingsprots.console.util.PinYinUtils;

/**
 * Created by v_luoxin on 2018-12-28 15:35
 */
@Service
@Transactional
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public List<Student> list(String name, Integer kId, Integer cId) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (kId != null) {
            criteria.andKIdEqualTo(kId);
        }
        if (cId != null) {
            criteria.andCIdEqualTo(cId);
        }
        return studentMapper.selectByExample(example);
    }

    public void save(Student recoder) {
        if (recoder.getId() != null) {
            studentMapper.updateByPrimaryKeySelective(recoder);
        } else {
            recoder.setCreateTime(new Date());
            studentMapper.insertSelective(recoder);
        }
    }

    public void del(Integer[] ids) {
        for (Integer id : ids) {
            studentMapper.deleteByPrimaryKey(id);
        }
    }

    public String fetchUsername(String name, Integer id) {
        if (id != null) {
            Student student = studentMapper.selectByPrimaryKey(id);
            if (name.equals(student.getName())) {
                return student.getUsername();
            }
        }
        String namePinyin = PinYinUtils.toPinyin(name);

        long count;
        StudentExample example = new StudentExample();
        example.createCriteria().andUsernameLike(namePinyin + "%");
        synchronized(this) {
            count = studentMapper.countByExample(example);
        }
        return namePinyin.concat(bulidSuffix(count));
    }

    private String bulidSuffix(long count) {
        if (count <= 0) {
            return "";
        }
        int length = String.valueOf(count).length();
        if (length == 1) {
            return "0" + (count + 1);
        }
        return String.valueOf(count + 1);
    }

}
