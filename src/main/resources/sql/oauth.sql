create table tbl_oauth(
    id bigint unsigned primary key,
    provider enum('trycatch', 'kakao') not null,
    constraint fk_oauth_member foreign key (id)
    references tbl_member(id)
);

select * from tbl_oauth;

set FOREIGN_KEY_CHECKS = 1;
drop table tbl_oauth;
