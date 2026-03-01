create table tbl_qna_job_category_big(
    id bigint unsigned primary key comment '직무 카테고리 대카',
    job_category_big_name varchar(255) not null comment '카테고리명',
    job_category_big_code varchar(255) comment '카테고리 고유 식별 코드 (예: DEV_BACKEND, DEV_FRONTEND, DESIGN_UI 등)'
)

insert tbl_qna_job_category_big(id, job_category_big_code, job_category_big_name)
values (2, 2, '산업' );