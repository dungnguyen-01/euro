package net.brian.dbms;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Abstract base class for handling dynamic fields
public abstract class GenericTable {

    @XmlTransient
    private Map<String, String> dynamicFields = new HashMap<>();

    public Map<String, String> getDynamicFields() {
        return dynamicFields;
    }

    public void setDynamicField(String key, String value) {
        this.dynamicFields.put(key, value);
    }

    public String getDynamicField(String key) {
        return dynamicFields.get(key);
    }

    public static List<String> getXmlTags(Class<?> clazz) {
        List<String> tags = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(XmlElement.class)) {
                XmlElement element = field.getAnnotation(XmlElement.class);
                tags.add(element.name());
            }
        }
        return tags;
    }
}