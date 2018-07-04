package com.lfd.frontend.common.utils;

import java.util.*;

/**
 * Created by ryan on 1/12/16.
 */
public class RandomUtils {

    /**
     * generate [a-z] or [A-Z] random string
     * @param length
     * @param upperCase
     * @return
     */
    public static String randomString(int length, boolean upperCase) {
        if (length <= 0)
            return "";

        int start = upperCase ? 65 : 97;
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(26);
            str.append((char) (num + start));
        }

        return str.toString();
    }

    public static Set<Integer> intRandomSet(int size) {
        Random r = new Random();

        Set<Integer> randoms = new HashSet<>();

        while(randoms.size() < size) {
            randoms.add(r.nextInt(size));
        }

        return randoms;
    }

}
