-- ============================================================

create table tbl_file
(
    id                 bigint unsigned auto_increment primary key comment '파일 ID',
    file_path          varchar(255)            not null comment '파일 경로',
    file_name          varchar(255)            not null comment 'UUID + 파일명',
    file_original_name varchar(255)            not null comment '파일명',
    file_size          varchar(255)            not null comment '파일 크기 (byte)',
    file_content_type  enum ('image', 'other') not null comment '파일 유형',
    created_datetime   datetime default current_timestamp,
    updated_datetime   datetime default current_timestamp
) comment '파일 정보';

select * from tbl_file;
set foreign_key_checks = 1;
drop table tbl_file;