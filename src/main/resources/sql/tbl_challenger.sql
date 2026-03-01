
create table tbl_challenger
(
    id                   bigint unsigned auto_increment primary key comment '승급 ID',
    apply_id             bigint unsigned not null comment '지원 ID',
    challenger_status     enum ('in_progress', 'completed', 'out_of_process', 'step_failed') default 'in_progress' comment '승급 상태',
    created_datetime     datetime default current_timestamp,
    updated_datetime     datetime default current_timestamp,
    constraint fk_challenger_apply foreign key (apply_id) references tbl_apply (id)
) comment '체험 단계 승급';


