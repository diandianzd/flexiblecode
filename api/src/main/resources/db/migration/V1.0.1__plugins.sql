-- ----------------------------
-- Table structure for departments
-- ----------------------------
DROP TABLE IF EXISTS "plugins";

CREATE TABLE "plugins" (
  id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY, -- SERIAL类型
  "name" VARCHAR(255) DEFAULT NULL,  -- 插件名字
  "key" VARCHAR(255) DEFAULT NULL,  -- 插件key
  "description" VARCHAR(255) DEFAULT NULL,  -- 插件描述
  "version" VARCHAR(255) DEFAULT NULL,  -- 插件版本
  "codestr" VARCHAR(255) DEFAULT NULL,  -- 插件渲染方式
  "configuration" VARCHAR(255) DEFAULT NULL,  -- 插件配置
  "created_at" TIMESTAMP DEFAULT NULL  -- 创建时间
);


