package com.system.core.util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.system.core.util.ParamUtil.isEmpty;

public class RandomCodeUtil {

    public static String getOrderCode() {
        return new Date().getTime() + createRandom(true, 6);
    }

    public static String createRandom(boolean isAllNumber, int length) {
        String randomCharStr = isAllNumber ? Constant.NUMBER_SEQ : Constant.NUM_LETTER_SEQ;
        int len = randomCharStr.length();
        StringBuilder retStr = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) Math.floor(Math.random() * len);
            retStr.append(randomCharStr.charAt(index));
        }
        return retStr.toString();
    }

    public static boolean isMobile(String mobile) {
        if (isEmpty(mobile)) {
            return false;
        }
        Pattern pattern = Pattern.compile(Constant.PHONE_REGEXP);
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }

    public static boolean isEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }
        Pattern pattern = Pattern.compile(Constant.EMAIL_REGEXP);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
