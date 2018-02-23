package com.zyy.springcloud.cache.core.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegxUtils {
    public static List<String> split(String regx, String str) {
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        List<String> groups = new ArrayList<String>();
        while (matcher.find()) {
            for (int i = 0; i < matcher.groupCount(); i++) {
                if (matcher.group(i + 1) != null
                        && !"".equals(matcher.group(i + 1))) {
                    groups.add(matcher.group(i + 1));
                }
            }
        }
        return groups;
    }
}
