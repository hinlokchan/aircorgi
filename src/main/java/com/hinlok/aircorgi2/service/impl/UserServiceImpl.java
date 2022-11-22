package com.hinlok.aircorgi2.service.impl;

import com.hinlok.aircorgi2.mapper.PassengerInfoMapper;
import com.hinlok.aircorgi2.mapper.UserInfoMapper;
import com.hinlok.aircorgi2.model.PassengerInfo;
import com.hinlok.aircorgi2.model.UserInfo;
import com.hinlok.aircorgi2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private PassengerInfoMapper passengerInfoMapper;

    @Override
    public UserInfo findByUserId(String userId) {
        return userInfoMapper.selectByUserId(userId);
    }

    @Override
    public int insert(UserInfo userInfo) {
        System.out.println("Conducting insert...");
        userInfoMapper.insert(userInfo);
        return 0;
    }

    @Override
    public int addPassenger(PassengerInfo passengerInfo) {
        passengerInfoMapper.insertPassenger(passengerInfo);
        return 0;
    }

    @Override
    public int alterPassword(UserInfo userInfo) {
        userInfoMapper.updatePassword(userInfo);
        return 0;
    }
}
