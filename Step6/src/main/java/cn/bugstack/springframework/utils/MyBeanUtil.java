package cn.bugstack.springframework.utils;

import java.lang.reflect.Field;

public interface MyBeanUtil {

    static void setFieldValue(Object bean, String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        Class<?> aClass = bean.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(name)) {
                field.setAccessible(true);
                field.set(bean, value);
                break;
            }
        }
    }
}
