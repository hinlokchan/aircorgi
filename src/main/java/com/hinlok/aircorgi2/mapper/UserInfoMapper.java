package com.hinlok.aircorgi2.mapper;

import com.hinlok.aircorgi2.model.UserInfo;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(@Param("userInfo")UserInfo userInfo);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    UserInfo selectByUserId(String userId);

    int updatePassword(@Param("userInfo") UserInfo userInfo);
}