package cn.bugstack.springframework.beans.factory.support;

import java.lang.reflect.Field;

public interface MyBeanUtil {

    static void setFieldValue(Object bean, String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        Class<?> aClass = bean.getClass();
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            if (field.getName().equals(name)) {

            }
        }

    }
}
