-- ============================================================
create table tbl_skill_log_comment_report
(
    id                             bigint unsigned primary key comment '기술블로그 댓글 리포트 ID (tbl_report.id)',
    skill_log_comment_id     bigint unsigned not null comment '신고 대상 ID',
    constraint fk_skill_log_comment_report_report foreign key (id) references tbl_report (id),
    constraint fk_skill_log_comment_report_skill_log_comment foreign key (skill_log_comment_id) references tbl_skill_log_comment (id)
) comment '기술 블로그 댓글 리포트 서브타입';

select * from tbl_skill_log_comment_report;
select * from tbl_report;