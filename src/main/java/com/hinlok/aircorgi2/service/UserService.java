package com.hinlok.aircorgi2.service;

import com.hinlok.aircorgi2.model.PassengerInfo;
import com.hinlok.aircorgi2.model.UserInfo;

public interface UserService {

    UserInfo findByUserId(String userId);

    int insert(UserInfo userInfo);

    int addPassenger(PassengerInfo passengerInfo);

    int alterPassword(UserInfo userInfo);

}
