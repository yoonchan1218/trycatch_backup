-- ============================================================
create table tbl_qna_notification
(
    id                    bigint unsigned primary key comment 'QnA 알림 ID (tbl_main_notification.id)',
    qna_id                bigint unsigned not null comment 'QnA ID',
    constraint fk_qna_notification foreign key (id) references tbl_main_notification (id),
    constraint fk_qna_notification_qna foreign key (qna_id) references tbl_qna (id)
) comment 'QnA 알림 서브타입';
