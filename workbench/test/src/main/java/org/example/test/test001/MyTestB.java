package org.example.test.test001;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.example.core.util.ConsoleUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author qbq
 * @date 2022/1/16 12:47 PM
 */
public class MyTestB {

    private static final List<String> LST = Lists.newArrayList(
            "星期一", "星期二", "星期三", "星期四", "星期五", "休息"
    );

    public static void main(String[] args) {
        List<String> lst = Lists.newArrayList(LST);
        Random rand = new Random();
        // 挑一个
        int idx = rand.nextInt(lst.size());
        ConsoleUtils.sout(lst.get(idx));
    }

}
