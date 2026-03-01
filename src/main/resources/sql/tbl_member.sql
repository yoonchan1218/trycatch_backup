create table tbl_member
(
    id                     bigint unsigned auto_increment primary key comment '회원 ID',
    member_id              varchar(255)         not null unique comment '로그인 ID',
    member_password        varchar(255)         not null comment '비밀번호',
    member_name            varchar(255)         not null comment '이름',
    member_email           varchar(255)         not null unique comment '이메일',
    member_phone           varchar(255) comment '전화번호',
    address_id             bigint unsigned comment '주소 ID (FK)',
    member_status          enum ('active', 'inactive') default 'active' comment '회원 상태',
    member_agree_privacy   boolean                     default false comment '개인정보처리방침 동의',
    member_agree_marketing   boolean                     default false comment '마케팅 수신 동의',
    member_profile_file_id   bigint unsigned null comment '프로필 사진 파일 ID (tbl_file.id)',
    created_datetime         datetime                    default current_timestamp,
    updated_datetime         datetime                    default current_timestamp,
    constraint fk_member_address foreign key (address_id) references tbl_address (id),
    constraint fk_member_profile_file foreign key (member_profile_file_id) references tbl_file (id)
) comment '회원 슈퍼타입';

-- 기존 테이블에 컬럼 추가 시
alter table tbl_member add column member_profile_file_id bigint unsigned null comment '프로필 사진 파일 ID (tbl_file.id)';
alter table tbl_member add constraint fk_member_profile_file foreign key (member_profile_file_id) references tbl_file (id);

select * from tbl_member;
delete from tbl_member;
select * from tbl_individual_member;
set FOREIGN_KEY_CHECKS = 1;
drop table tbl_member;



