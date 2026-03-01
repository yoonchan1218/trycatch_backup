create table tbl_skill_log_comment
(
    id                        bigint unsigned auto_increment primary key,
    skill_log_id              bigint unsigned not null comment '스킬로그 ID',
    member_id                 bigint unsigned not null comment '작성자 ID (FK: tbl_member)',
    skill_log_comment_parent_id  bigint unsigned comment '부모 댓글 ID',
    skill_log_comment_content text            not null comment '댓글 내용',
    created_datetime          datetime default current_timestamp,
    updated_datetime          datetime default current_timestamp,
    constraint fk_skill_log_comment_log foreign key (skill_log_id) references tbl_skill_log (id),
    constraint fk_skill_log_comment_member foreign key (member_id) references tbl_member (id) ,
    constraint fk_skill_log_comment_parent foreign key (skill_log_comment_parent_id) references tbl_skill_log_comment (id)
);

select * from tbl_skill_log_comment;
select * from tbl_skill_log_comment_file;
select * from tbl_file;


set foreign_key_checks = 1;
drop table tbl_skill_log_comment;

select * from tbl_skill_log_comment
where skill_log_comment_parent_id = 269 and skill_log_id = 293
;

