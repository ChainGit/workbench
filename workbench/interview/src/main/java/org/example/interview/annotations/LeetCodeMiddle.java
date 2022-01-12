package org.example.interview.annotations;

/**
 * @author qbq
 * @date 2022/1/12 4:54 PM
 */
@LeetCode(level = Level.middle)
public @interface LeetCodeMiddle {

    Tag[] value() default {};
}
