package com.ruixingsprots.console.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruixingsprots.console.pojo.po.Kindergarten;
import com.ruixingsprots.console.pojo.po.KindergartenExample;

public interface KindergartenMapper {
    long countByExample(KindergartenExample example);

    int deleteByExample(KindergartenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Kindergarten record);

    int insertSelective(Kindergarten record);

    List<Kindergarten> selectByExample(KindergartenExample example);

    Kindergarten selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Kindergarten record, @Param("example") KindergartenExample example);

    int updateByExample(@Param("record") Kindergarten record, @Param("example") KindergartenExample example);

    int updateByPrimaryKeySelective(Kindergarten record);

    int updateByPrimaryKey(Kindergarten record);
}