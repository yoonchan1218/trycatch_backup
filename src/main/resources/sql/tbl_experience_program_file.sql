create table tbl_experience_program_file
(
    id                    bigint unsigned primary key comment '파일 ID (tbl_file.id)',
    experience_program_id bigint unsigned not null comment 'program ID (FK)',
    constraint fk_experience_program_file_file foreign key (id) references tbl_file (id),
    constraint fk_experience_program_file_experience_program foreign key (experience_program_id)
        references tbl_experience_program (id)
) comment 'program 파일 서브타입';

select *
from tbl_experience_program_file;