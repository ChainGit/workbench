package org.example.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * @author qbq
 * @date 2022/1/4 10:36 AM
 */
public class ConsoleUtils {

    public static void souts(Object... objs) {
        sout(Lists.newArrayList(objs));
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
