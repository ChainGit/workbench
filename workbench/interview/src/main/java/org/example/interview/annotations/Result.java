package org.example.interview.annotations;

import org.apache.commons.lang3.StringUtils;

/**
 * 运行结果
 *
 * @author qbq
 * @date 2022/1/6 2:30 PM
 */
public @interface Result {

    String value() default StringUtils.EMPTY;

}
