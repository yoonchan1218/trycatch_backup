CREATE TABLE tbl_corp_welfare_rel
(
    id            BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    corp_id       BIGINT UNSIGNED NOT NULL COMMENT '기업 ID',
    welfare_code  VARCHAR(10) NOT NULL COMMENT '복리후생 코드',
    welfare_name  VARCHAR(100) NOT NULL COMMENT '복리후생명',
    CONSTRAINT fk_corp_welfare_rel_corp FOREIGN KEY (corp_id) REFERENCES tbl_corp (id)
) COMMENT '기업별 복리후생 선택';
