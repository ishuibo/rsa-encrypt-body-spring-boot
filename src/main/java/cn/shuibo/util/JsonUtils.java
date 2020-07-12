package cn.shuibo.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author imyzt
 * @date 2020/06/08
 * @description JSON 工具类
 */
public class JsonUtils {

    private JsonUtils() {
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static JsonNode getNode(String content, String key) throws IOException {
        JsonNode jsonNode = OBJECT_MAPPER.readTree(content);
        return jsonNode.get(key);
    }

    public static boolean hasNode(String content, String key) throws IOException {
        JsonNode jsonNode = OBJECT_MAPPER.readTree(content);
        return jsonNode.has(key);
    }

    public static String writeValueAsString(Object body) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(body);
    }
}
