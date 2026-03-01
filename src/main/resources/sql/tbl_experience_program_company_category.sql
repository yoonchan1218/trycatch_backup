create table tbl_experience_program_company_category
(
    id                                  bigint unsigned auto_increment primary key comment 'ID',
    experience_program_category_name    varchar(255) not null comment '카테고리명',
    experience_program_category_checked boolean  default false comment '체크 여부',
    created_datetime                    datetime default current_timestamp,
    updated_datetime                    datetime default current_timestamp
);
