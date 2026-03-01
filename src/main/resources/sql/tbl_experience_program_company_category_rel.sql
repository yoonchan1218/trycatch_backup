create table tbl_experience_program_company_category_rel
(
    id                                     bigint unsigned auto_increment primary key,
    experience_program_id                  bigint unsigned not null comment '프로그램 ID',
    experience_program_company_category_id bigint unsigned not null comment '기업 카테고리 ID',
    constraint fk_exp_company_cat_program foreign key (experience_program_id) references tbl_experience_program (id),
    constraint fk_exp_company_cat_category foreign key (experience_program_company_category_id) references tbl_experience_program_company_category (id) ,
    unique key uk_program_company_cat (experience_program_id, experience_program_company_category_id)
) comment '체험-기업카테고리 N:N';
