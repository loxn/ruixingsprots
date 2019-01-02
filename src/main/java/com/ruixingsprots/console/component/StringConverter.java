/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ruixingsprots.console.component;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by v_luoxin on 2019-01-02 12:17
 */
@Component
public class StringConverter implements Converter<String, String> {

    @Override
    public String convert(String s) {
        if (s == null) {
            return null;
        }
        s = s.trim();
        if (s.equals("")) {
            return null;
        }
        return s;
    }
}
