
create table tbl_apply
(
    id                        bigint unsigned auto_increment primary key comment '지원 ID',
    experience_program_id     bigint unsigned not null comment '프로그램 ID',
    member_id      bigint unsigned not null comment '개인회원 ID',
    apply_status              enum('applied', 'document_pass', 'document_fail', 'cancelled') comment '지원 상태',
    created_datetime          datetime default current_timestamp,
    updated_datetime          datetime default current_timestamp,
    constraint fk_apply_program foreign key (experience_program_id) references tbl_experience_program (id),
    constraint fk_apply_member foreign key (member_id) references tbl_individual_member (id),
    unique key uk_program_member (experience_program_id, member_id)

);

select * from tbl_apply;
set foreign_key_checks = 1;
drop table tbl_apply;