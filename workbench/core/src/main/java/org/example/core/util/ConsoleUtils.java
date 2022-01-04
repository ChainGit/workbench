package org.example.core.util;

/**
 * @author qbq
 * @date 2022/1/4 10:36 AM
 */
public class ConsoleUtils {

    public static void souts(Object... objs) {
        sout(objs);
    }

    public static void sout(Object obj) {
        System.out.println(obj);
    }

    public static void sout() {
        System.out.println();
    }

}
