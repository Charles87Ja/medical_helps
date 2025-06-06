package com.example.medical_helps.validator;


import com.example.medical_helps.config.RRException;
import org.junit.platform.commons.util.StringUtils;

/**
 * 数据校验
 * @author chengguangrong@live.cn
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }
}
