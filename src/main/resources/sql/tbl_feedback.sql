create table tbl_feedback
(
    id               bigint unsigned primary key comment '피드백 ID (tbl_promotion.id)',
    feedback_content text comment '피드백 내용',
    constraint fk_feedback foreign key (id) references tbl_challenger (id)
) comment '체험 승급 피드백 (1:1)';


