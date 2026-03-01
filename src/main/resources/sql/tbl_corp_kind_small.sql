-- ============================================================
create table tbl_corp_kind_small
(
    id                      bigint unsigned auto_increment primary key comment 'ID',
    corp_kind_parent_id     bigint unsigned not null comment '부모 카테고리 ID',
    corp_kind_small_name    varchar(255) not null comment '업종 소카테고리명',
    corp_kind_small_checked boolean  default false comment '체크 여부',
    constraint fk_corp_kind_small foreign key (corp_kind_parent_id) references tbl_corp_kind (id)
);


