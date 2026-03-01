create table tbl_skill_log_file
(
    id                  bigint unsigned primary key comment '파일 ID (tbl_file.id)',
    skill_log_id        bigint unsigned                           not null comment '스킬로그 ID (FK)',
    constraint fk_skill_log_file foreign key (id) references tbl_file (id),
    constraint fk_skill_log_file_log foreign key (skill_log_id) references tbl_skill_log (id)
) comment '기술 블로그 파일 서브타입';

select * from tbl_skill_log_file;
set foreign_key_checks = 1;
drop table tbl_skill_log_file;
