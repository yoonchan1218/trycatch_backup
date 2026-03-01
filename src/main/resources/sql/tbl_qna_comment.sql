set FOREIGN_KEY_CHECKS = 0;
drop table if exists tbl_qna_comment_likes;
drop table if exists tbl_qna_comment;
set FOREIGN_KEY_CHECKS = 1;

-- auto-generated definition
create table tbl_qna_comment
(
    id                  bigint unsigned auto_increment
        primary key,
    qna_id              bigint unsigned                    not null comment 'QnA ID',
    member_id           bigint unsigned                    null,
    qna_comment_parent  bigint unsigned                    null comment '부모 댓글 ID (대댓글용)',
    qna_comment_content text                               not null comment '댓글 내용',
    created_datetime    datetime default CURRENT_TIMESTAMP null,
    updated_datetime    datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint fk_qna_comment_member
        foreign key (member_id) references tbl_member (id),
    constraint fk_qna_comment_parent
        foreign key (qna_comment_parent) references tbl_qna_comment (id)
            on delete cascade,
    constraint fk_qna_comment_qna
        foreign key (qna_id) references tbl_qna (id)
            on delete cascade
)
    comment 'QnA 댓글';

select * from tbl_qna_comment;

set FOREIGN_KEY_CHECKS = 1;

drop table tbl_qna_comment;