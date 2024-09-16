package cn.fo9c.educationaltourism.utils;

public final class UUIDGenerator {
    /**
     * 生成一个新的UUID
     * @return UUID（无连接符）
     */
    public static String newUUID() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }
}
