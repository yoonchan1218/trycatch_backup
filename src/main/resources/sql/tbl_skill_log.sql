-- ============================================================
create table tbl_skill_log
(
    id                            bigint unsigned auto_increment primary key comment '스킬로그 ID',
    member_id                     bigint unsigned not null comment '작성자 ID (FK: tbl_member)',
    experience_program_id         bigint unsigned comment '체험 프로그램 ID',
    skill_log_title               varchar(255)    not null comment '제목',
    skill_log_content             text            not null comment '내용',
    skill_log_view_count          int                           default 0 comment '조회수',
    skill_log_status              enum ('published', 'deleted') default 'published' comment '상태',
    created_datetime              datetime                      default current_timestamp,
    updated_datetime              datetime                      default current_timestamp,
    constraint fk_skill_log_member foreign key (member_id) references tbl_individual_member (id),
    constraint fk_skill_log_program foreign key (experience_program_id) references tbl_experience_program (id)
);


select * from tbl_skill_log;
set foreign_key_checks = 1;
drop table tbl_skill_log;
delete from tbl_skill_log;

select * from tbl_skill_log;
select * from tbl_member;


# select
#     count(*) individualMemberPostCount,
#     sum(skill_log_view_count) skillLogViewCountTotal,
#     (select count(*) from tbl_skill_log_likes group by skill_log_id) skillLogLikeCountTotal,
#     (select count(*) from tbl_skill_log_comment group by skill_log_id) skillLogCommentCount
# from tbl_skill_log s
# left join
#
# where skill_log_status = 'published' and member_id = 12
# group by id;







