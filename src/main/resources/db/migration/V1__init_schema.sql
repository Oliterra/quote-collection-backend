CREATE TABLE "author"
(
    "id"      BIGINT       NOT NULL PRIMARY KEY,
    "name"    VARCHAR(255) NOT NULL,
    "surname" VARCHAR(255) NOT NULL
);

CREATE TABLE "book"
(
    "id"        BIGINT       NOT NULL PRIMARY KEY,
    "name"      VARCHAR(255) NOT NULL,
    "author_id" BIGINT       NOT NULL
        CONSTRAINT "FK_AUTHOR_TO_BOOK" REFERENCES "author",
    "user_id"   BIGINT       NOT NULL
        CONSTRAINT "FK_USER_TO_BOOK" REFERENCES "user",
);

CREATE TABLE "category"
(
    "id"   BIGINT       NOT NULL PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    UNIQUE ("name")
);

CREATE TABLE "book_to_category"
(
    "book_id"     BIGINT NOT NULL
        CONSTRAINT "FK_BOOK_TO_BOOK_TO_CATEGORY" REFERENCES "book",
    "category_id" BIGINT NOT NULL
        CONSTRAINT "FK_CATEGORY_TO_BOOK_TO_CATEGORY" REFERENCES "category",
    PRIMARY KEY ("book_id", "category_id")
);

CREATE TABLE "user"
(
    "id"       BIGINT       NOT NULL PRIMARY KEY,
    "name"     VARCHAR(255) NOT NULL,
    "surname"  VARCHAR(255) NOT NULL,
    "email"    VARCHAR(255) NOT NULL,
    "username" VARCHAR(255) NOT NULL,
    "password" VARCHAR(255) NOT NULL,
    UNIQUE ("username"),
    UNIQUE ("email")
);

CREATE TABLE "role"
(
    "id"   BIGINT       NOT NULL PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    UNIQUE ("name")
);

CREATE TABLE "permission"
(
    "id"   BIGINT       NOT NULL PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    UNIQUE ("name")
);

CREATE TABLE "user_to_role"
(
    "user_id" BIGINT NOT NULL
        CONSTRAINT "FK_USER_TO_USER_TO_ROLE" REFERENCES "user",
    "role_id" BIGINT NOT NULL
        CONSTRAINT "FK_ROLE_TO_USER_TO_ROLE" REFERENCES "role",
    PRIMARY KEY ("user_id", "role_id")
);

CREATE TABLE "role_to_permission"
(
    "role_id"       BIGINT NOT NULL
        CONSTRAINT "FK_ROLE_TO_ROLE_TO_PERMISSION" REFERENCES "role",
    "permission_id" BIGINT NOT NULL
        CONSTRAINT "FK_PERMISSION_TO_ROLE_TO_PERMISSION" REFERENCES "permission",
    PRIMARY KEY ("role_id", "permission_id")
);

CREATE TABLE "quote"
(
    "id"              BIGINT        NOT NULL PRIMARY KEY,
    "text"            VARCHAR(2000) NOT NULL,
    "is_public"       BOOLEAN       NOT NULL,
    "number_of_votes" INTEGER       NOT NULL,
    "rating"          FLOAT         NOT NULL,
    creation_time     TIMESTAMPTZ   NOT NULL,
    "user_id"         BIGINT        NOT NULL
        CONSTRAINT "FK_USER_TO_QUOTE" REFERENCES "user",
    "book_id"         BIGINT        NOT NULL
        CONSTRAINT "FK_BOOK_TO_QUOTE" REFERENCES "book",
    UNIQUE ("text")
);

CREATE TABLE "user_quote_rating"
(
    "user_id"  BIGINT  NOT NULL,
    "quote_id" BIGINT  NOT NULL,
    "rating"   INTEGER NOT NULL,
    PRIMARY KEY ("user_id", "quote_id")
);

CREATE TABLE "tag"
(
    "id"   BIGINT       NOT NULL PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    UNIQUE ("name")
);

CREATE TABLE "quote_to_tag"
(
    "quote_id" BIGINT NOT NULL
        CONSTRAINT "FK_QUOTE_TO_QUOTE_TO_TAG" REFERENCES "quote",
    "tag_id"   BIGINT NOT NULL
        CONSTRAINT "FK_TAG_TO_QUOTE_TO_TAG" REFERENCES "tag",
    PRIMARY KEY ("quote_id", "tag_id")
);

CREATE TABLE "group"
(
    "id"        BIGINT       NOT NULL PRIMARY KEY,
    "name"      VARCHAR(255) NOT NULL,
    "is_public" BOOLEAN      NOT NULL,
    "user_id"   BIGINT       NOT NULL
        CONSTRAINT "FK_USER_TO_GROUP" REFERENCES "user"
);

CREATE TABLE "group_to_quote"
(
    "group_id" BIGINT NOT NULL
        CONSTRAINT "FK_GROUP_TO_GROUP_TO_QUOTE" REFERENCES "group",
    "quote_id" BIGINT NOT NULL
        CONSTRAINT "FK_QUOTE_TO_GROUP_TO_QUOTE" REFERENCES "quote",
    PRIMARY KEY ("group_id", "quote_id")
);

CREATE SEQUENCE id_sequence START 1;
