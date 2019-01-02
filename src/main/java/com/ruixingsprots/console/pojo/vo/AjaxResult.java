/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.ruixingsprots.console.pojo.vo;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AjaxResult
 * Created by Geng on 2016/5/22.
 */
public class AjaxResult implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(AjaxResult.class);
    private static final long serialVersionUID = 3311880859621174728L;
    private static final Integer FAILURE = 1;
    private static final Integer SUCCESS = 0;
    private static final Integer ERROR = -1;
    private static final Integer FORBIDDEN = -100;

    private static final String SUCCESS_MSG = "success";

    private int errNo;
    private Object data;
    private String message;

    public static AjaxResult emptySuccessResult() {
        AjaxResult result = new AjaxResult();
        result.setErrNo(SUCCESS);
        result.setMessage(SUCCESS_MSG);
        return result;
    }

    public static AjaxResult successResult(Object data) {
        AjaxResult result = new AjaxResult();
        result.setErrNo(SUCCESS);
        result.setData(data);
        return result;
    }

    public static AjaxResult noAuthResult() {
        AjaxResult result = new AjaxResult();
        result.setErrNo(ERROR);
        result.setMessage("noData");
        return result;
    }

    public static AjaxResult errorResult(String msg) {
        AjaxResult result = new AjaxResult();
        result.setErrNo(FAILURE);
        result.setMessage(msg);
        return result;
    }

    public static AjaxResult forbiddenResult(String msg) {
        AjaxResult result = new AjaxResult();
        result.setErrNo(FORBIDDEN);
        result.setMessage(msg);
        return result;
    }

    public static void sendAjax(String content, HttpServletResponse response) {
        try {
            response.setContentType("application/json; charset=UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public int getErrNo() {
        return errNo;
    }

    public void setErrNo(int errNo) {
        this.errNo = errNo;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
