-- ============================================================

create table tbl_scrap_posting
(
    id                    bigint unsigned auto_increment primary key comment '스크랩 ID',
    member_id  bigint unsigned not null comment '개인회원 ID',
    experience_program_id bigint unsigned not null comment '프로그램 ID',
    scrap_status          enum ('active', 'inactive') default 'active' comment '활성화 여부',
    created_datetime      datetime                    default current_timestamp,
    updated_datetime      datetime                    default current_timestamp,
    constraint fk_scrap_member foreign key (member_id) references tbl_individual_member (id),
    constraint fk_scrap_program foreign key (experience_program_id) references tbl_experience_program (id),
    unique key uk_member_program (member_id, experience_program_id)
);

