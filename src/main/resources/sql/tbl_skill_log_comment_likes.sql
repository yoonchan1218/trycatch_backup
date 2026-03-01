create table tbl_skill_log_comment_likes
(
    id                       bigint unsigned auto_increment primary key comment '좋아요 ID',
    member_id     bigint unsigned not null comment '좋아요 누른 회원 ID',
    skill_log_comment_id     bigint unsigned not null comment '스킬로그 댓글 ID',
    created_datetime         datetime default current_timestamp,
    updated_datetime          datetime default current_timestamp,
    constraint fk_skill_log_comment_likes_member foreign key (member_id) references tbl_individual_member (id) on delete cascade,
    constraint fk_skill_log_comment_likes_comment foreign key (skill_log_comment_id) references tbl_skill_log_comment (id) on delete cascade,
    unique key uk_member_skill_log_comment (member_id, skill_log_comment_id)
) comment '기술 블로그 댓글 좋아요';

select * from tbl_skill_log_comment_likes;