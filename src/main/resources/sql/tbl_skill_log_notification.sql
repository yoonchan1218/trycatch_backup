-- ============================================================
create table tbl_skill_log_notification
(
    id                    bigint unsigned primary key comment '기술블로그 알림 ID (tbl_main_notification.id)',
    skill_log_id          bigint unsigned not null comment '스킬로그 ID',
    constraint fk_skill_log_notification foreign key (id) references tbl_main_notification (id),
    constraint fk_skill_log_notification_log foreign key (skill_log_id) references tbl_skill_log (id)
) comment '기술 블로그 알림 서브타입';

