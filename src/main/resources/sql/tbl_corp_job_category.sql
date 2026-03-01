create table tbl_corp_job_category
(
    id                                  bigint unsigned auto_increment primary key,
    corp_id                             bigint unsigned not null comment '기업 ID',
    experience_program_job_category_id  bigint unsigned not null comment '직무 카테고리 ID',
    constraint fk_corp_job_cat_corp foreign key (corp_id) references tbl_corp (id) ,
    constraint fk_corp_job_cat_category foreign key (experience_program_job_category_id) references tbl_experience_program_job_category (id) ,
    unique key uk_corp_job_cat (corp_id, experience_program_job_category_id)
) comment '기업-직무카테고리 N:N';

