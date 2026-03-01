-- ============================================================
create table tbl_qna_job_category
(
    id                     bigint unsigned auto_increment primary key comment '직무 카테고리 ID',
    job_category_name      varchar(255) not null comment '카테고리명',
    job_category_code      varchar(255) comment '카테고리 고유 식별 코드 (예: DEV_BACKEND, DEV_FRONTEND, DESIGN_UI 등)',
    job_category_big_id bigint unsigned not null comment '대카 FK',
    constraint fk_qna_job_category_big foreign key  (job_category_big_id) references tbl_qna_job_category_big(id)
);

drop table tbl_qna_job_category;

set FOREIGN_KEY_CHECKS =0;

INSERT INTO tbl_qna_job_category(job_category_name, job_category_code, job_category_big_id)
VALUES
    ('IT·정보통신업', 10007, 2),
    ('판매·유통업', 10010, 2),
    ('제조·생산·화학업', 10009, 2),
    ('교육업', 10011, 2),
    ('건설업', 10003, 2),
    ('의료·제약업', 10004, 2),
    ('미디어·광고업', 10005, 2),
    ('문화·예술·디자인업', 10006, 2),
    ('기관·협회', 10008, 2);

select * from tbl_qna_job_category;