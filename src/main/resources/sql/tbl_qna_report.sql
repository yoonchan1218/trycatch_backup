-- ============================================================
create table tbl_qna_report
(
    id                       bigint unsigned primary key comment 'QnA 리포트 ID (tbl_report.id)',
    qna_report_target_type   enum ('qna', 'comment') not null comment '신고 대상 유형',
    qna_report_target_id     bigint unsigned not null comment '신고 대상 ID',
    constraint fk_qna_report foreign key (id) references tbl_report (id)
) comment 'QnA 리포트 서브타입';

