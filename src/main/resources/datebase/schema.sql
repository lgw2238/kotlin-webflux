CREATE TABLE IF NOT EXISTS TB_ARTICLE
(
    id
    BIGINT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    title
    VARCHAR
(
    200
),
    body VARCHAR
(
    2000
),
    author_id BIGINT,
    create_at TIMESTAMP,
    update_at TIMESTAMP
);