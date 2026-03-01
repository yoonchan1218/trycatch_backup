-- ============================================================
create table tbl_experience_apply_notification
(
    id                    bigint unsigned primary key comment '체험 알림 ID (tbl_main_notification.id)',
    experience_program_id bigint unsigned not null comment '체험 프로그램 ID',
    apply_id              bigint unsigned comment '지원 ID',
    constraint fk_experience_apply_notification foreign key (id) references tbl_main_notification (id) ,
    constraint fk_experience_apply_notification_program foreign key (experience_program_id) references tbl_experience_program (id),
    constraint fk_experience_apply_notification_apply foreign key (apply_id) references tbl_apply (id)
) comment '체험 지원 현황 알림 서브타입(기업)';
