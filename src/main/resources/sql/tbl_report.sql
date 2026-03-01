-- ============================================================
create table tbl_report
(
    id                    bigint unsigned auto_increment primary key comment '리포트 ID',
    member_id  bigint unsigned not null comment '신고자 ID',
    report_reason_code    int not null comment '신고 사유 코드',
    report_reason_detail  text comment '신고 사유 상세',
    report_status         enum ('pending', 'reviewing', 'processed', 'rejected') default 'pending' comment '처리 상태',
    report_processed_at   datetime null comment '처리 완료일',
    created_datetime      datetime default current_timestamp,
    updated_datetime      datetime default current_timestamp,
    constraint fk_report_member foreign key (member_id) references tbl_individual_member (id)
) comment '리포트 슈퍼타입';


select * from tbl_report;
select * from tbl_skill_log_report;