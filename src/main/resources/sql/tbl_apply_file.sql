
create table tbl_apply_file
(
    id               bigint unsigned primary key comment '파일 ID (tbl_file.id)',
    apply_id         bigint unsigned not null comment '지원 ID (FK)',
    constraint fk_apply_file foreign key (id) references tbl_file (id) ,
    constraint fk_apply_file_apply foreign key (apply_id) references tbl_apply (id)
) comment '지원 포트폴리오 파일 서브타입';