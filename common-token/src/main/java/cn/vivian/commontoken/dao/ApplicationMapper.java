package cn.vivian.commontoken.dao;

import cn.vivian.commontoken.model.Application;

public interface ApplicationMapper {
    int deleteByPrimaryKey(String id);

    int insert(Application record);

    int insertSelective(Application record);

    Application selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Application record);

    int updateByPrimaryKey(Application record);

    Application selectByAppKey(String appKey);
}