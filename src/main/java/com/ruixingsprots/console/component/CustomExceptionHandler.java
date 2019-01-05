/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ruixingsprots.console.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ruixingsprots.console.exception.ServiceException;
import com.ruixingsprots.console.pojo.vo.AjaxResult;
import com.ruixingsprots.console.util.JsonUtils;

/**
 * 异常处理
 * Created by Geng on 2017/5/24.
 */
@Component
public class CustomExceptionHandler implements HandlerExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception exception) {
        AjaxResult result;
        exception.printStackTrace();
        if (exception instanceof ServiceException) {
            result = AjaxResult.errorResult(exception.getMessage());
        } else {
            result = AjaxResult.errorResult("系统错误");
        }
        writeHttpResponse(response, result);
        return new ModelAndView();
    }

    // 返回封装结果
    private void writeHttpResponse(HttpServletResponse response, AjaxResult result) {
        try {
            response.setContentType("application/json; charset=UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.getWriter().write(JsonUtils.toJsonStr(result));
            response.getWriter().flush();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}