create table tbl_qna_likes
(
    id                   bigint unsigned auto_increment primary key comment '좋아요 ID',
    member_id bigint unsigned not null comment '좋아요 누른 회원 ID',
    qna_id               bigint unsigned not null comment 'QnA ID',
    created_datetime     datetime default current_timestamp,
    constraint fk_qna_likes_member foreign key (member_id) references tbl_individual_member (id),
    constraint fk_qna_likes_qna foreign key (qna_id) references tbl_qna (id),
    unique key uk_member_qna (member_id, qna_id)
) comment 'QnA 좋아요';

