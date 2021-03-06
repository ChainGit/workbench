package org.example.test.test001;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.example.core.util.ConsoleUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author qbq
 * @date 2022/1/16 12:47 PM
 */
public class MyTestA {

    private static final List<String> LST = Lists.newArrayList(
            "账号一", "账号二"
    );

    public static void main(String[] args) {
        execute(Lists.newArrayList(LST));
    }

    private static void execute(List<String> lst) {
        // 洗牌
        Collections.shuffle(lst);
        ConsoleUtils.sout(lst);
    }

}
