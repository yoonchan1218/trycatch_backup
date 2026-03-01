-- ============================================================
create table tbl_corp_welfare_small
(
    id                     bigint unsigned auto_increment primary key comment 'ID',
    corp_welfare_parent_id bigint unsigned not null comment '부모 카테고리 ID',
    corp_welfare_name      varchar(255) not null comment '복지 카테고리명',
    corp_welfare_checked   boolean  default false comment '체크 여부',
    constraint fk_corp_welfare_small foreign key (corp_welfare_parent_id) references tbl_corp_welfare (id)
);
