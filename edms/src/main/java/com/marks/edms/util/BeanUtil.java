package com.marks.edms.util;

import org.springframework.beans.BeanUtils;

import java.util.*;

public abstract class BeanUtil {
    public static Object copyProperties(Object source, Object targer, String... ignoreProperties) {
        if (source == null) {
            return targer;
        }
        BeanUtils.copyProperties(source, targer, ignoreProperties);
        return targer;
    }

    public static <T> List<T> copyList(List sources, Class<T> clazz) {
        return copyList(sources, clazz, null);
    }

    public static <T> List<T> copyList(List sources, Class<T> clazz, Callback<T> callback) {
        List<T> targetList = new ArrayList<>();
        if (sources != null) {
            try {
                for (Object source : sources) {
                    T target = clazz.newInstance();
                    copyProperties(source, target);
                    if (callback != null) {
                        callback.set(source, target);
                    }
                    targetList.add(target);
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return targetList;
    }

    public static interface Callback<T> {
        void set(Object source, T target);
    }
}
