package com.sg.util;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: DataUtil
 * @Description: 数据校验工具
 * @author: xz
 * @date 2019/12/12 0012 16:15
 */
public class DataUtil {

    public static boolean isEmpty (Object pObj) {
        if (pObj == null) {
            return true;
        } else if ("".equals (pObj)) {
            return true;
        } else {
            if (pObj instanceof String) {
                if (((String) pObj).length () == 0) {
                    return true;
                }
            } else if (pObj instanceof Collection) {
                if (((Collection) pObj).size () == 0) {
                    return true;
                }
            } else if (pObj instanceof Map && ((Map) pObj).size () == 0) {
                return true;
            }

            return false;
        }
    }

    public static boolean isNotEmpty (Object pObj) {
        if (pObj == null) {
            return false;
        } else if ("".equals (pObj)) {
            return false;
        } else {
            if (pObj instanceof String) {
                if (((String) pObj).length () == 0) {
                    return false;
                }
            } else if (pObj instanceof Collection) {
                if (((Collection) pObj).size () == 0) {
                    return false;
                }
            } else if (pObj instanceof Map && ((Map) pObj).size () == 0) {
                return false;
            }

            return true;
        }
    }

    /**
     * 验证参数是否为正数,是true
     * 
     * @param object
     * @return
     */
    public final static boolean isInteger (Object object) {
        try {
            Pattern p = Pattern.compile ("-?[0-9]+");
            Matcher m = p.matcher (object.toString ());
            return m.matches ();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 截取字符串，获取除最后一个字符的字符串
     * 
     * @param String
     * @return
     */
    public final static String subEndString (String text) {
        return text.substring (0, text.length () - 1);
    }
}
