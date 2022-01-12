package org.example.interview.leetcode;

import org.apache.commons.lang3.reflect.MethodUtils;

/**
 * @author qbq
 * @date 2022/1/12 10:42 AM
 */
public class Main {

    private static final Class<?> CLZ = Q0001.class;

    public static void main(String[] args) throws Throwable {
        MethodUtils.invokeStaticMethod(CLZ, "main", new String[][]{new String[]{}}, new Class[]{String[].class});
    }

}