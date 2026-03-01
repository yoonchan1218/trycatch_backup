-- ============================================================
create table tbl_corp_kind
(
    id               bigint unsigned auto_increment primary key comment 'ID',
    corp_kind_name   varchar(255) not null comment '업종 대카테고리명',
    corp_kind_checked boolean default false comment '체크 여부',
    created_datetime datetime default current_timestamp,
    updated_datetime datetime default current_timestamp on update current_timestamp
);

