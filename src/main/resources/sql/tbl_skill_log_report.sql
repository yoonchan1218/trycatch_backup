-- ============================================================
create table tbl_skill_log_report
(
    id                             bigint unsigned primary key comment '기술블로그 리포트 ID (tbl_report.id)',
    skill_log_id     bigint unsigned not null comment '신고 대상 ID',
    constraint fk_skill_log_report_report foreign key (id) references tbl_report (id),
    constraint fk_skill_log_report_skill_log foreign key (skill_log_id) references tbl_skill_log (id)
) comment '기술 블로그 리포트 서브타입';

drop table tbl_skill_log_report;

