-- ============================================================
create table tbl_qna
(
    id                    bigint unsigned auto_increment primary key,
    individual_member_id  bigint unsigned not null comment '작성자 ID',
    qna_title             varchar(255)    not null comment '제목',
    qna_content           text            not null comment '내용',
    qna_file_id           bigint unsigned comment '파일ID',
    job_category_small_id bigint unsigned comment '직무 소분류 ID',
    job_category_name     varchar(255) comment '직무 카테고리명 (반정규화)',
    company_name          varchar(255) comment '기업선배 기업명',
    college_friend        varchar(255) comment '학교선배 학교명',
    qna_view_count        int                           default 0 comment '조회수',
    qna_status            enum ('published', 'deleted') default 'published' comment '상태',
    created_datetime      datetime                      default current_timestamp comment '작성일시',
    updated_datetime      datetime                      default current_timestamp on update current_timestamp comment '수정일시',
    constraint fk_qna_member foreign key (individual_member_id) references tbl_individual_member (id),
    constraint fk_qna_file_id foreign key (qna_file_id) references tbl_qna_file (id),
    constraint fk_job_category_small foreign key (job_category_small_id) references tbl_qna_job_category_small(id)
);

select *
from tbl_qna;

set FOREIGN_KEY_CHECKS = 1;

drop table tbl_qna;

INSERT INTO tbl_qna (individual_member_id, qna_title,company_name, college_friend,qna_content, qna_file_id, job_category_small_id, job_category_name)
(select individual_member_id, qna_title, company_name, college_friend, qna_content, qna_file_id, job_category_small_id, job_category_name from tbl_qna)


