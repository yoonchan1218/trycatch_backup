-- ============================================================
create table tbl_corp_welfare
(
    id                   bigint unsigned auto_increment primary key comment 'ID',
    corp_welfare_name    varchar(255) not null comment '복지 카테고리명',
    corp_welfare_checked boolean  default false comment '체크 여부',
    created_datetime     datetime default current_timestamp,
    updated_datetime     datetime default current_timestamp
);
