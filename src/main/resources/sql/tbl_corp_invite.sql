-- ============================================================

create table tbl_corp_invite
(
    id                  bigint unsigned auto_increment primary key comment '초대 ID',
    corp_id             bigint unsigned not null comment '기업 ID',
    invite_email        varchar(255) not null comment '초대 이메일',
    invite_code         varchar(50) not null unique comment '초대 코드',
    invite_status       enum('pending', 'accepted') default 'pending' comment '초대 상태',
    created_datetime    datetime default current_timestamp,
    constraint fk_corp_invite_corp foreign key (corp_id) references tbl_corp (id)
);
