package com.system.core.util;

import org.apache.commons.beanutils.BeanUtilsBean;

import javax.servlet.http.HttpServletRequest;

public class ParamUtil {

    //获取参数int
    public static int getIntParameter(HttpServletRequest request, String paraName, int defaul) {
        int res = defaul;
        try {
            String para = request.getParameter(paraName);
            if (para == null) {
                res = defaul;
            } else {
                res = Integer.parseInt(para);
            }
        } catch (Exception e) {
            res = defaul;
        }
        return res;
    }

    //获取参数String
    public static String getStrParameter(HttpServletRequest request, String paraName, String defaul) {
        String res = defaul;
        try {
            res = request.getParameter(paraName);
            if (res == null) {
                res = defaul;
            }
        } catch (Exception e) {
            res = defaul;
        }
        return res;
    }

    // 参数拷贝
    public static void bindBean(Object entity, Object model) {
        try {
            BeanUtilsBean.getInstance().copyProperties(entity, model);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * string数组转Integer数组
     *
     * @param strs
     * @return
     */
    public static Integer[] toIntegers(String[] strs) {
        Integer[] ides = new Integer[strs.length];
        for (int i = 0; i < strs.length; i++) {
            ides[i] = Integer.parseInt(strs[i]);
        }
        return ides;
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
