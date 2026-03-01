create table tbl_skill_log_comment_file
(
    id                   bigint unsigned primary key comment '파일 ID (tbl_file.id)',
    skill_log_comment_id bigint unsigned not null comment '댓글 ID (FK)',
    constraint fk_skill_log_comment_file foreign key (id) references tbl_file (id),
    constraint fk_skill_log_comment_file_comment foreign key (skill_log_comment_id) references tbl_skill_log_comment (id)
) comment '기술 블로그 댓글 파일 서브타입';

