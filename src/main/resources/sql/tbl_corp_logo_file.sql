create table tbl_corp_logo_file
(
    id               bigint unsigned primary key comment '파일 ID (tbl_file.id)',
    corp_id          bigint unsigned not null comment '기업 ID (FK)',
    constraint fk_corp_file foreign key (id) references tbl_file (id) ,
    constraint fk_corp_file_corp foreign key (corp_id) references tbl_corp (id)
) comment '기업 파일 (로고)';
