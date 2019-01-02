/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.ruixingsprots.console.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by v_luoxin on 2018-12-31 23:10
 */
public class JsonUtils {

    public static String toJsonStr(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
