package org.example.interview.annotations;

/**
 * @author qbq
 * @date 2022/1/10 5:58 PM
 */
public @interface LeetCode {

    Tag[] tags() default {};

    Level level() default Level.unknown;

}
