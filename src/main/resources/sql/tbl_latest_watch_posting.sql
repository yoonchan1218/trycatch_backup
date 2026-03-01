-- ============================================================
create table tbl_latest_watch_posting
(
    id                    bigint unsigned auto_increment primary key comment '조회 ID',
    member_id  bigint unsigned not null comment '개인회원 ID',
    experience_program_id bigint unsigned not null comment '프로그램 ID',
    created_datetime      datetime default current_timestamp,
    constraint fk_latest_watch_member foreign key (member_id) references tbl_individual_member (id),
    constraint fk_latest_watch_program foreign key (experience_program_id) references tbl_experience_program (id),
    unique key uk_member_program (member_id, experience_program_id)
);
