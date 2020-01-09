package com.system.core.util;

import org.apache.commons.beanutils.BeanUtilsBean;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParamUtil {

    public static boolean isEmpty(String string) {
        return string == null || string.trim().length() == 0;
    }

    //获取参数int
    public static int getIntParameter(HttpServletRequest request, String paraName, int defaultValue) {
        int res = defaultValue;
        try {
            String parameter = request.getParameter(paraName);
            res = parameter == null ? defaultValue : Integer.parseInt(parameter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    //获取参数String
    public static String getStrParameter(HttpServletRequest request, String paraName, String defaultValue) {
        String res = defaultValue;
        if (request.getParameter(paraName) != null) {
            res = request.getParameter(paraName);
        }
        return res;
    }

    // 参数拷贝
    public static void bindBean(Object dest, Object source) {
        try {
            BeanUtilsBean.getInstance().copyProperties(dest, source);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Integer[] toIntegers(String[] strings) {
        List<Integer> list = new ArrayList<>();
        Arrays.stream(strings).map(Integer::parseInt).forEach(list::add);
        return list.toArray(new Integer[0]);
    }


    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
