package com.coding.commons.base.data.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public class ProtoStuffSerializerUtils {

    private ProtoStuffSerializerUtils() {
    }

    /**
     * serialize obj.
     */
    public static <T> byte[] serialize(T t) {
        @SuppressWarnings("unchecked")
        Class<T> typeClass = (Class<T>) t.getClass();
        return serialize(t, typeClass);
    }

    public static <T> byte[] serialize(T t, Class<T> typeClass) {
        Schema<T> schema = RuntimeSchema.getSchema(typeClass);
        LinkedBuffer buffer = LinkedBuffer.allocate(512);
        final byte[] protostuff;
        try {
            protostuff = ProtostuffIOUtil.toByteArray(t, schema, buffer);
        } finally {
            buffer.clear();
        }
        return protostuff;
    }

    /**
     * deserialize obj.
     */
    public static <T> T deserialize(byte[] bytes, Class<T> clazz) {
        Schema<T> schema = RuntimeSchema.getSchema(clazz);
        T t = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, t, schema);
        return t;
    }

    /**
     * deserialize list.
     */
    public static <T> List<T> deserializeList(byte[] paramArrayOfByte, Class<T> targetClass) {
        Schema<T> schema = RuntimeSchema.getSchema(targetClass);
        try {
            return ProtostuffIOUtil
                .parseListFrom(new ByteArrayInputStream(paramArrayOfByte), schema);
        } catch (IOException e) {
            throw new DeserializeException(e);
        }
    }

    /**
     * serialize list.
     */
    public static <T> byte[] serializeList(List<T> objList, Class<T> typeClass) {
        Schema<T> schema = RuntimeSchema.getSchema(typeClass);
        LinkedBuffer buffer = LinkedBuffer.allocate(1024 * 1024);
        byte[] protostuff;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ProtostuffIOUtil.writeListTo(bos, objList, schema, buffer);
            protostuff = bos.toByteArray();
        } catch (IOException e) {
            throw new SerializeException("序列化对象列表(" + objList + ")发生异常!", e);
        } finally {
            buffer.clear();
        }

        return protostuff;
    }

}
