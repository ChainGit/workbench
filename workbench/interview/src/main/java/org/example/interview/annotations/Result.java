package org.example.interview.annotations;

import org.apache.commons.lang3.StringUtils;

/**
 * @author qbq
 * @date 2022/1/10 6:03 PM
 */
public @interface Result {

    String value() default StringUtils.EMPTY;
}
