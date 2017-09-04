package com.tw.demo.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by tw on 2017/5/26.
 */
public class JacksonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    private JacksonUtil() {
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static String writeValue(Object data) {
        try {
            if (data != null)
                return objectMapper.writeValueAsString(data);
        } catch (IOException e) {
            LoggerFactory.getLogger(JacksonUtil.class).error("jackson write value error ", e);
        }
        return null;
    }

    public static <T> T readValue(String json, Class<T> valueType) {
        try {
            if (StringUtils.isNotBlank(json))
                return objectMapper.readValue(json, valueType);
        } catch (IOException e) {
            LoggerFactory.getLogger(JacksonUtil.class).error("jackson read value error ", e);
        }
        return null;
    }

    public static <T> List<T> readValueList(String json, Class<T> valueType) {
        try {
            if (StringUtils.isNotBlank(json))
                return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));
        } catch (IOException e) {
            LoggerFactory.getLogger(JacksonUtil.class).error("jackson read value error ", e);
        }
        return null;
    }

    public static <K, V> Map<K, V> readValueMap(String json, Class<K> key, Class<V> value) {
        try {
            if (StringUtils.isNotBlank(json))
                return objectMapper.readValue(json, objectMapper.getTypeFactory().constructMapType(Map.class, key, value));
        } catch (IOException e) {
            LoggerFactory.getLogger(JacksonUtil.class).error("jackson read value error ", e);
        }
        return null;
    }

    public static <K, V> Map<K, List<V>> readValueListMap(String json, Class<K> key, Class<V> value) {
        try {
            if (StringUtils.isNotBlank(json))
                return objectMapper.readValue(json, objectMapper.getTypeFactory().constructMapType(Map.class,
                        objectMapper.getTypeFactory().constructType(key),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, value)));
        } catch (IOException e) {
            LoggerFactory.getLogger(JacksonUtil.class).error("jackson read value error ", e);
        }
        return null;
    }

}
