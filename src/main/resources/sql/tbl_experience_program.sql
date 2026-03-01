-- ============================================================
create table tbl_experience_program
(
    id                                   bigint unsigned auto_increment primary key comment '프로그램 ID',
    corp_id                              bigint unsigned not null comment '기업 ID',
    experience_program_title             varchar(255)    not null comment '프로그램 제목',
    experience_program_description       text comment '프로그램 설명',
    experience_program_level             varchar(1) check (experience_program_level in ('a', 'b', 'c', 'd', 'e')) comment '체험 단계 (a~e)',
    experience_program_recruitment_count int                                                 default 1 comment '모집 인원',
    experience_program_work_days         varchar(100) comment '근무 요일',
    experience_program_work_hours        varchar(100) comment '근무 시간',
    experience_program_deadline          date comment '지원 마감일',
    experience_program_status            enum ('draft', 'recruiting', 'closed', 'cancelled') default 'draft' comment '프로그램 상태',
    experience_program_view_count        int                                                 default 0 comment '조회수',
    experience_program_job               varchar(255)    not null comment '프로그램 직무',
    created_datetime                     datetime                                            default current_timestamp,
    updated_datetime                     datetime                                            default current_timestamp,
    constraint fk_experience_program_corp foreign key (corp_id) references tbl_corp (id)
);


select * from tbl_experience_program;
set foreign_key_checks = 1;
drop table tbl_experience_program;

ALTER TABLE tbl_experience_program DROP COLUMN experience_program_start_date;
ALTER TABLE tbl_experience_program DROP COLUMN experience_program_end_date;

insert into tbl_experience_program(corp_id, experience_program_title, experience_program_description, experience_program_level, experience_program_recruitment_count, experience_program_work_days, experience_program_work_hours, experience_program_deadline, experience_program_status, experience_program_view_count, experience_program_job)
(select corp_id, experience_program_title, experience_program_description, experience_program_level, experience_program_recruitment_count, experience_program_work_days, experience_program_work_hours, experience_program_deadline, experience_program_status, experience_program_view_count, experience_program_job from tbl_experience_program);

