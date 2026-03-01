create table tbl_address_program
(
    id                    bigint unsigned auto_increment primary key,
    address_id            bigint unsigned not null comment '주소 ID FK',
    experience_program_id bigint unsigned not null comment '프로그램 id',
    created_datetime          datetime default current_timestamp,
    updated_datetime          datetime default current_timestamp,
    constraint fk_address_program_address foreign key (address_id) references tbl_address (id),
    constraint fk_address_program_program foreign key (experience_program_id) references tbl_experience_program (id)
) comment '프로그램(근무지) 주소(중간 테이블)';

select * from tbl_address_program;
drop table tbl_address_program;