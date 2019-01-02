package com.ruixingsprots.console.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ruixingsprots.console.dao.ProjectMapper;
import com.ruixingsprots.console.exception.ServiceException;
import com.ruixingsprots.console.pojo.po.Project;
import com.ruixingsprots.console.pojo.po.ProjectExample;

/**
 * Created by v_luoxin on 2018-12-28 15:35
 */
@Service
@Transactional
public class ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    public List<Project> list(String name) {
        ProjectExample example = new ProjectExample();
        if (!StringUtils.isEmpty(name)) {
            example.createCriteria().andNameLike("%" + name + "%");
        }
        return projectMapper.selectByExample(example);
    }

    public void save(Project recoder) {
        ProjectExample example = new ProjectExample();
        example.createCriteria().andNameEqualTo(recoder.getName());
        List<Project> lists = projectMapper.selectByExample(example);
        if (lists != null && lists.size() > 0) {
            Project project = lists.get(0);
            if (recoder.getId() == null || !project.getId().equals(recoder.getId())) {
                throw new ServiceException("项目已存在！");
            }
        }
        // 校验合格区间
        recoder.setQualifiedInterval(String.valueOf(recoder.getPassLoweLimit()).concat("-").concat(String.valueOf
                (recoder.getPassUpperLimit())));
        if (recoder.getId() != null) {
            projectMapper.updateByPrimaryKeySelective(recoder);
        } else {
            recoder.setCreateTime(new Date());
            projectMapper.insertSelective(recoder);
        }
    }

    public void del(Integer[] ids) {
        for (Integer id : ids) {
            projectMapper.deleteByPrimaryKey(id);
        }
    }

}
