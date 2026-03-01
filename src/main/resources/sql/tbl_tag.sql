-- ============================================================
create table tbl_tag
(
    id               bigint unsigned auto_increment primary key comment '태그 ID',
    tag_name         varchar(100) not null comment '태그명',
    skill_log_id     bigint unsigned not null comment '블로그 ID',
    constraint fk_tag_skill_log foreign key(skill_log_id)
    references tbl_skill_log(id)
) comment '기술 블로그 태그';

select * from tbl_tag;

set foreign_key_checks = 1;
drop table tbl_tag;
delete from tbl_tag;
