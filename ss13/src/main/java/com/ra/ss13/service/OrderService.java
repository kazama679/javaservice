package com.ra.ss13.service;


import com.ra.ss13.model.entity.UserBt;

public interface OrderService {
    void checkout(UserBt user, String receiver, String phoneNumber, String address);
}
