package io.exhub.exhub_manager.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Json转换
 * @date 2018/7/26
 * @author
 */
public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String object2json(Object object){

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw  new RuntimeException("conversion json failure");
        }

    }

    public static <T>T json2Object(String json, Class<T> clazz) {

        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            LOGGER.error(e.getStackTrace().toString());
            throw new RuntimeException("json conversion failure");
        }
    }
}
