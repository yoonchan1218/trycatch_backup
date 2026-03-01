create table tbl_qna_file
(
    id               bigint unsigned primary key comment '파일 ID (tbl_file.id)',
    qna_id           bigint unsigned not null comment 'QnA ID (FK)',
    constraint fk_qna_file foreign key (id) references tbl_file (id) ,
    constraint fk_qna_file_qna foreign key (qna_id) references tbl_qna (id)
) comment 'QnA 파일 서브타입';

select * from tbl_qna_file;