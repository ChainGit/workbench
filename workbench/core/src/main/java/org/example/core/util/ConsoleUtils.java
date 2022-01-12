package org.example.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

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

    public static void jout(Object obj) {
        sout(JSON.toJSONString(obj));
    }

    public static void prettyJout(Object obj) {
        sout(JSON.toJSONString(obj, SerializerFeature.PrettyFormat));
    }

}
