create table tbl_qna_comment_likes
(
    id               bigint unsigned primary key comment '좋아요 ID',
    fk_member_id        bigint unsigned not null comment '좋아요 누른 회원 ID',
    created_datetime datetime default current_timestamp,
    constraint fk_qna_comment_likes_member foreign key (fk_member_id) references tbl_member (id) on delete cascade,
    constraint fk_qna_comment_likes_comment foreign key (id) references tbl_qna_comment (id) on delete cascade,
    unique key uk_member_qna_comment (fk_member_id)
) comment 'QnA 댓글 좋아요';

set FOREIGN_KEY_CHECKS = 1;

drop table tbl_qna_comment_likes;
