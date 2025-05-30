-- ----------------------------
-- Table structure for user_infos
-- ----------------------------

DROP TABLE IF EXISTS user_infos;
CREATE TABLE user_infos (
  id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY, -- SERIAL类型
  user_id INT NOT NULL, -- user ID
  name VARCHAR(255) NULL, -- 姓名
  departmentId INT NULL, -- 部门ID
  deleted_at TIMESTAMP NULL, -- 删除时间
  created_at TIMESTAMP NULL, -- 创建时间
  updated_at TIMESTAMP NULL -- 更新时间
);



-- ----------------------------
-- Table structure for users
-- ----------------------------

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY, -- SERIAL类型
  name VARCHAR(255) NULL, 
  email VARCHAR(255) NOT NULL, -- 邮箱
  email_verified_at TIMESTAMP NULL, -- 邮箱验证时间
  password VARCHAR(255) NULL, 
  remember_token VARCHAR(100) NULL, 
  deleted_at TIMESTAMP NULL, 
  created_at TIMESTAMP NULL, 
  updated_at TIMESTAMP NULL
);

INSERT INTO "api"."users" ( "name", "email", "password") 
VALUES ( 'admin', 'admin', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJXb3JrMjFAISJ9._6yDbttMcyuJEgsk2JznKO8kdkkHtr_Vg0z03OcO0LxvN6Tvh9TyKsbsdZ1i8KWOalhU9OtrEuo_duzw0p63TQ');


-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS "permissions";

CREATE TABLE "permissions" (
  id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY, -- SERIAL类型
  "name" VARCHAR(255) NOT NULL,
  "guard_name" VARCHAR(255) NOT NULL,
  "group_name" VARCHAR(255) DEFAULT NULL,
  "created_at" TIMESTAMPTZ DEFAULT NULL,
  "updated_at" TIMESTAMPTZ DEFAULT NULL
);


-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS "roles";

CREATE TABLE "roles" (
  id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY, -- SERIAL类型
  "name" VARCHAR(255) NOT NULL,
  "guard_name" VARCHAR(255) NOT NULL,
  "created_at" TIMESTAMPTZ DEFAULT NULL,
  "updated_at" TIMESTAMPTZ DEFAULT NULL
);



-- ----------------------------
-- Table structure for role_has_permissions
-- ----------------------------
DROP TABLE IF EXISTS "role_has_permissions";

CREATE TABLE "role_has_permissions" (
  "permission_id" INT NOT NULL,
  "role_id" INT NOT NULL,
  PRIMARY KEY ("permission_id", "role_id"),
  CONSTRAINT "role_has_permissions_permission_id_foreign" 
    FOREIGN KEY ("permission_id") REFERENCES "permissions" ("id") 
    ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT "role_has_permissions_role_id_foreign" 
    FOREIGN KEY ("role_id") REFERENCES "roles" ("id") 
    ON DELETE CASCADE ON UPDATE RESTRICT
);

-- Index for role_id (optional, but useful for performance)
CREATE INDEX "role_has_permissions_role_id_foreign" ON "role_has_permissions" ("role_id");



-- ----------------------------
-- Table structure for model_has_permissions
-- ----------------------------
DROP TABLE IF EXISTS "model_has_permissions";

CREATE TABLE "model_has_permissions" (
  "permission_id" INT NOT NULL,
  "model_type" VARCHAR(255) NOT NULL,
  "model_id" BIGINT NOT NULL,
  PRIMARY KEY ("permission_id", "model_id", "model_type"),
  CONSTRAINT "model_has_permissions_permission_id_foreign"
    FOREIGN KEY ("permission_id") REFERENCES "permissions" ("id")
    ON DELETE CASCADE ON UPDATE RESTRICT
);

-- Index for model_id and model_type (optional, but useful for performance)
CREATE INDEX "model_has_permissions_model_id_model_type_index" 
  ON "model_has_permissions" ("model_id", "model_type");



-- ----------------------------
-- Table structure for model_has_roles
-- ----------------------------
DROP TABLE IF EXISTS "model_has_roles";

CREATE TABLE "model_has_roles" (
  "role_id" INT NOT NULL,
  "model_type" VARCHAR(255) NOT NULL,
  "model_id" BIGINT NOT NULL,
  PRIMARY KEY ("role_id", "model_id", "model_type"),
  CONSTRAINT "model_has_roles_role_id_foreign" 
    FOREIGN KEY ("role_id") REFERENCES "roles" ("id") 
    ON DELETE CASCADE ON UPDATE RESTRICT
);

-- Index for model_id and model_type (optional, but useful for performance)
CREATE INDEX "model_has_roles_model_id_model_type_index" 
  ON "model_has_roles" ("model_id", "model_type");


-- ----------------------------
-- Table structure for departments
-- ----------------------------
DROP TABLE IF EXISTS "departments";

CREATE TABLE "departments" (
  id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY, -- SERIAL类型
  "pid" INT DEFAULT NULL,  -- 父分类
  "name" VARCHAR(255) DEFAULT NULL,  -- 部门名字
  "deleted_at" TIMESTAMPTZ DEFAULT NULL,
  "created_at" TIMESTAMPTZ DEFAULT NULL,
  "updated_at" TIMESTAMPTZ DEFAULT NULL
);


DROP TABLE IF EXISTS "rel_department_user";
CREATE TABLE "rel_department_user" (
  "id"  BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  "user_id" BIGINT,
  "department_id" BIGINT
);