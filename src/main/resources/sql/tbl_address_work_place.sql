create table tbl_address_work_place
(
    id               bigint unsigned primary key comment '주소 ID FK',
    experience_program_id        bigint unsigned comment '프로그램 id',
    constraint fk_address_work_place foreign key (id) references tbl_address (id),
    constraint fk_address_work_place_program foreign key (experience_program_id) references tbl_experience_program (id)
) comment '프로그램(근무지) 주소';

select * from tbl_address_work_place;