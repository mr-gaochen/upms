package com.eric.grace.upms.modules.api.annotation;

import java.lang.annotation.*;

/**
 * AuthIgnore: api接口，忽略Token验证
 *
 * @author: MrServer
 * @since: 2018/4/24 下午12:38
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIgnore {
}