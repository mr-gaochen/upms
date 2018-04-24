package com.eric.grace.upms.common.utils;


import com.eric.grace.upms.common.exception.RRException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;


import java.util.Iterator;
import java.util.Set;

/**
 * ValidatorUtils: hibernate-validator校验工具类
 *
 * @author: MrServer
 * @since: 2018/4/24 下午1:16
 */
public class ValidatorUtils {

    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws RRException 校验不通过，则报RRException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws RRException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.append(constraint.getMessage()).append("<br>");
            }
            throw new RRException(msg.toString());
        }
    }


}