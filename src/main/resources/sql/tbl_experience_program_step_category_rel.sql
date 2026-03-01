create table tbl_experience_program_step_category_rel
(
    id                                   bigint unsigned auto_increment primary key,
    experience_program_id                bigint unsigned not null comment '프로그램 ID',
    experience_program_step_category_id  bigint unsigned not null comment '단계 카테고리 ID',
    constraint fk_exp_step_cat_program foreign key (experience_program_id) references tbl_experience_program (id) ,
    constraint fk_exp_step_cat_category foreign key (experience_program_step_category_id) references tbl_experience_program_step_category (id) ,
    unique key uk_program_step_cat (experience_program_id, experience_program_step_category_id)
) comment '체험-단계카테고리 N:N';
