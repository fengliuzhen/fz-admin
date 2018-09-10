package com.fz.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {
    @Autowired
    public HttpServletRequest request;
    @Resource
    public HttpServletResponse response;
}
