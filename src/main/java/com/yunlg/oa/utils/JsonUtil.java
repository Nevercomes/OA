package com.yunlg.oa.utils;

//import com.alibaba.dubbo.common.utils.StringUtils;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.TypeReference;
//import com.alibaba.fastjson.parser.Feature;
//import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil {

    public static String toJson(Object object) throws Exception {
//        try {
//            return JSON.toJSONString(object, new SerializerFeature[] {
//                    SerializerFeature.WriteMapNullValue,
//                    SerializerFeature.DisableCircularReferenceDetect,
//                    SerializerFeature.WriteNonStringKeyAsString });
//        } catch (Exception e) {
//            throw new Exception();
//        }
        return "";
    }

    /**
     *
     * 描述：将对象格式化成json字符串（PrettyFormat格式）
     * @author mao2080@sina.com
     * @created 2017年4月1日 下午4:38:18
     * @since
     * @param object 对象
     * @return json字符串
     * @throws Exception
     */
    public static String toJsonFormat(Object object) throws Exception {
//        try {
//            return JSON.toJSONString(object, new SerializerFeature[] {
//                    SerializerFeature.WriteMapNullValue,
//                    SerializerFeature.PrettyFormat,
//                    SerializerFeature.DisableCircularReferenceDetect,
//                    SerializerFeature.WriteNonStringKeyAsString });
//        } catch (Exception e) {
//            throw new Exception();
//        }
        return "";
    }

    /**
     *
     * 描述：转Map
     * @author mao2080@sina.com
     * @created 2017年4月1日 下午5:00:20
     * @since
     * @param obj 对象
     * @return object
     * @throws Exception
     */
    public static Object toJsonObject(Object obj) throws Exception {
//        try {
//            return JSON.toJSON(obj);
//        } catch (Exception e) {
//            throw new Exception();
//        }
        return new Object();
    }

    /**
     *
     * 描述：将json串转为对象
     * @author mao2080@sina.com
     * @created 2017年4月1日 下午5:01:23
     * @since
     * @param jsonString json串
     * @param clazz 对象
     * @return
     * @throws Exception
     */
    public static <T> T fromJson(String jsonString, Class<T> clazz) throws Exception {
//        try {
//            if (StringUtils.isBlank(jsonString)) {
//                return null;
//            }
//            return (T) JSON.parseObject(jsonString, clazz);
//        } catch (Exception e) {
//            throw new Exception();
//        }
        return null;
    }
}
