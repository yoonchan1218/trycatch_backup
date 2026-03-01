create table tbl_qna_comment_file
(
    id               bigint unsigned primary key comment '파일 ID (tbl_file.id)',
    qna_comment_id   bigint unsigned not null comment '댓글 ID (FK)',
    constraint fk_qna_comment_file foreign key (id) references tbl_file (id) ,
    constraint fk_qna_comment_file_comment foreign key (qna_comment_id) references tbl_qna_comment (id)
) comment 'QnA 댓글 파일 서브타입';