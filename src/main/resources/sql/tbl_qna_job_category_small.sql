-- ============================================================
create table tbl_qna_job_category_small
(
    id                     bigint unsigned auto_increment primary key comment '직무 카테고리 ID',
    job_category_parent_id bigint unsigned not null comment '중 카테고리 ID',
    job_category_small_name      varchar(255) not null comment '카테고리명',
    job_category_small_code      bigint comment '카테고리 고유 식별 코드 (예: DEV_BACKEND_JAVA, DESIGN_UI_FIGMA 등)',
    constraint fk_qna_job_category_small foreign key (job_category_parent_id) references tbl_qna_job_category (id)
);

set FOREIGN_KEY_CHECKS = 1;

drop table tbl_qna_job_category_small;

select * from tbl_qna_job_category_small;

INSERT INTO tbl_qna_job_category_small(job_category_parent_id, job_category_small_name, job_category_small_code)
VALUES
    (34, '공기업·공공기관', 1000048),
    (34, '협회·단체', 1000049),
    (34, '컨설팅·연구·조사', 1000050),
    (34, '회계·세무·법무', 1000051);
