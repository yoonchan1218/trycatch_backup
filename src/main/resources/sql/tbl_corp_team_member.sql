-- ============================================================

create table tbl_corp_team_member
(
    id                          bigint unsigned primary key comment '팀원 ID',
    corp_id                     bigint unsigned not null comment '기업 ID',
    corp_team_member_status     enum('active', 'inactive', 'wait') default 'active' comment '상태',
    created_datetime            datetime default current_timestamp,
    updated_datetime            datetime default current_timestamp,
    constraint fk_corp_team_corp foreign key (corp_id) references tbl_corp (id),
    constraint fk_member_id foreign key (id) references tbl_member (id)

);

ALTER TABLE tbl_corp_team_member ADD COLUMN invite_code VARCHAR(50);
