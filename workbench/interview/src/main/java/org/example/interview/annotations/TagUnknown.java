package org.example.interview.annotations;

import org.apache.commons.lang3.StringUtils;

/**
 * 标签：未知
 *
 * @author qbq
 * @date 2022/1/6 2:32 PM
 */
public @interface TagUnknown {

    String value() default StringUtils.EMPTY;

}
