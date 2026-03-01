create table tbl_corp
(
    id                               bigint unsigned primary key comment '기업 ID (tbl_member.id)',
    corp_company_name                varchar(255) not null comment '기업명',
    corp_business_number             varchar(255)  not null unique comment '사업자등록번호',
    corp_ceo_name                    varchar(255) comment '대표자명',
    corp_kind_id                     bigint unsigned comment '업종 대카테고리',
    corp_kind_small_id               bigint unsigned comment '업종 소카테고리',
    corp_company_type                varchar(255) comment '기업형태',
    corp_company_size                varchar(255) comment '기업규모',
    corp_establishment_date          date comment '설립일',
    corp_website_url                 varchar(255) comment '홈페이지 URL',
    corp_fax                         varchar(255) comment 'fax 번호',
    corp_capital                     bigint comment '자본금 (원 단위)',
    corp_total_sales                 varchar(50) comment '매출액',
    corp_main_business               varchar(100) comment '주요사업내용',
    corp_performance                 text comment '연혁 및 실적',
    corp_vision                      text comment '개요 및 비전',
    constraint fk_corp_member foreign key (id) references tbl_member (id)
) comment '매니저 && 기업';


set FOREIGN_KEY_CHECKS = 1;
drop table tbl_corp;

select * from tbl_corp;
select * from tbl_address;

SELECT * FROM tbl_member;

-- 주요사업내용 컬럼 추가
ALTER TABLE tbl_corp ADD COLUMN corp_main_business varchar(100) comment '주요사업내용' AFTER corp_total_sales;

-- 매출액 컬럼 타입 변경 (bigint → varchar, 텍스트 입력 지원)
ALTER TABLE tbl_corp MODIFY COLUMN corp_total_sales varchar(50) comment '매출액';








