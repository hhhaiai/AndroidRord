package me.hhhaiai.androidrord.utils;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Map;

public class Utils {

    /**
     * check all object legitimate in a call
     * @param objs
     * @return true:  legitimate,can use
     *          false: not legitimate. invalid value, can't work
     */
    public static boolean isLegitimate(Object... objs) {

        try {
            if (objs == null || objs.length < 1) {
                return false;
            }

            boolean isItemCanWork = true;
            // must all object item legitimate, can work
            for (Object obj : objs) {
                if (obj == null) {
                    return false;
                } else {
                    if (obj instanceof String) {
                        if (TextUtils.isEmpty((String) obj)) {
                            return false;
                        }
                    } else if (obj instanceof Collection) {
                        // has no item is not legitimate
                        Collection c = (Collection) obj;
                        if (c == null || c.size() < 1) {
                            return false;
                        }
                    } else if (obj instanceof Map) {
                        Map map = (Map) obj;
                        if (map == null || map.size() < 1) {
                            return false;
                        }
                    } else if (obj instanceof JSONArray) {
                        JSONArray arr = (JSONArray) obj;
                        if (arr == null || arr.length() < 1) {
                            return false;
                        }
                    } else if (obj instanceof JSONObject) {
                        JSONObject o = (JSONObject) obj;
                        if (o == null || o.length() < 1) {
                            return false;
                        }
                    }
                }
            }
        } catch (Throwable e) {
            Logs.e(e);
            return false;
        }

        return true;
    }
}
