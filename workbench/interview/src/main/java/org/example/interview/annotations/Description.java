package org.example.interview.annotations;

import org.apache.commons.lang3.StringUtils;

/**
 * 题目描述
 *
 * @author qbq
 * @date 2022/1/6 2:30 PM
 */
public @interface Description {

    String value() default StringUtils.EMPTY;

}
