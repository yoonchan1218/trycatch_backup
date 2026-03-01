-- PointType enum 4종(earn/use/expire/purchase_cancel) 대량 테스트 데이터 시드
-- 실행 대상: try_catch_copy (또는 현재 datasource DB)
-- 생성량: 개인회원 1명당 각 point_type 20건씩

-- 기존 테스트 데이터 제거
delete from tbl_point_details
where payment_order_id like 'TEST-POINT-BULK-%';

drop temporary table if exists tmp_point_seed_seq;
create temporary table tmp_point_seed_seq (
    n int not null primary key
);

insert into tmp_point_seed_seq (n)
values
    (1), (2), (3), (4), (5), (6), (7), (8), (9), (10),
    (11), (12), (13), (14), (15), (16), (17), (18), (19), (20);

-- earn 20건 x 모든 개인회원
insert into tbl_point_details (
    individual_member_id,
    point_type,
    point_amount,
    remaining_point_amount,
    payment_amount,
    expire_datetime,
    payment_order_id,
    payment_receipt_id,
    created_datetime,
    updated_datetime
)
select
    m.id as individual_member_id,
    'earn' as point_type,
    100 + (s.n * 5) as point_amount,
    100 + (s.n * 5) as remaining_point_amount,
    (100 + (s.n * 5)) * 110 as payment_amount,
    date_add(date_sub(now(), interval (120 - s.n) day), interval 1 year) as expire_datetime,
    concat('TEST-POINT-BULK-EARN-M', m.id, '-', lpad(s.n, 3, '0')) as payment_order_id,
    concat('TEST-POINT-BULK-EARN-RECEIPT-M', m.id, '-', lpad(s.n, 3, '0')) as payment_receipt_id,
    date_sub(now(), interval (120 - s.n) day) as created_datetime,
    date_sub(now(), interval (120 - s.n) day) as updated_datetime
from tbl_individual_member m
cross join tmp_point_seed_seq s;

-- use 20건 x 모든 개인회원
insert into tbl_point_details (
    individual_member_id,
    point_type,
    point_amount,
    remaining_point_amount,
    payment_amount,
    expire_datetime,
    payment_order_id,
    payment_receipt_id,
    created_datetime,
    updated_datetime
)
select
    m.id as individual_member_id,
    'use' as point_type,
    -(5 + s.n) as point_amount,
    greatest(0, 300 - (s.n * 5)) as remaining_point_amount,
    0 as payment_amount,
    null as expire_datetime,
    concat('TEST-POINT-BULK-USE-M', m.id, '-', lpad(s.n, 3, '0')) as payment_order_id,
    null as payment_receipt_id,
    date_sub(now(), interval (90 - s.n) day) as created_datetime,
    date_sub(now(), interval (90 - s.n) day) as updated_datetime
from tbl_individual_member m
cross join tmp_point_seed_seq s;

-- expire 20건 x 모든 개인회원
insert into tbl_point_details (
    individual_member_id,
    point_type,
    point_amount,
    remaining_point_amount,
    payment_amount,
    expire_datetime,
    payment_order_id,
    payment_receipt_id,
    created_datetime,
    updated_datetime
)
select
    m.id as individual_member_id,
    'expire' as point_type,
    -(3 + s.n) as point_amount,
    greatest(0, 220 - (s.n * 4)) as remaining_point_amount,
    0 as payment_amount,
    null as expire_datetime,
    concat('TEST-POINT-BULK-EXPIRE-M', m.id, '-', lpad(s.n, 3, '0')) as payment_order_id,
    null as payment_receipt_id,
    date_sub(now(), interval (60 - s.n) day) as created_datetime,
    date_sub(now(), interval (60 - s.n) day) as updated_datetime
from tbl_individual_member m
cross join tmp_point_seed_seq s;

-- purchase_cancel 20건 x 모든 개인회원
insert into tbl_point_details (
    individual_member_id,
    point_type,
    point_amount,
    remaining_point_amount,
    payment_amount,
    expire_datetime,
    payment_order_id,
    payment_receipt_id,
    created_datetime,
    updated_datetime
)
select
    m.id as individual_member_id,
    'purchase_cancel' as point_type,
    -(20 + s.n) as point_amount,
    0 as remaining_point_amount,
    -(20 + s.n) * 110 as payment_amount,
    now() as expire_datetime,
    concat('TEST-POINT-BULK-PURCHASE-CANCEL-M', m.id, '-', lpad(s.n, 3, '0')) as payment_order_id,
    concat('TEST-POINT-BULK-PURCHASE-CANCEL-RECEIPT-M', m.id, '-', lpad(s.n, 3, '0')) as payment_receipt_id,
    date_sub(now(), interval (30 - s.n) day) as created_datetime,
    date_sub(now(), interval (30 - s.n) day) as updated_datetime
from tbl_individual_member m
cross join tmp_point_seed_seq s;

drop temporary table if exists tmp_point_seed_seq;

-- 보유 포인트 동기화: 테스트로 넣은 내역 합계로 현재 포인트 반영
update tbl_individual_member im
join (
    select
        individual_member_id,
        greatest(0, sum(point_amount)) as synced_point
    from tbl_point_details
    where payment_order_id like 'TEST-POINT-BULK-%'
    group by individual_member_id
) seeded on seeded.individual_member_id = im.id
set im.individual_member_point = seeded.synced_point;

-- 회원별/타입별 건수 확인
select
    individual_member_id,
    point_type,
    count(*) as count_per_member_type
from tbl_point_details
where payment_order_id like 'TEST-POINT-BULK-%'
group by individual_member_id, point_type
order by individual_member_id, field(point_type, 'earn', 'use', 'expire', 'purchase_cancel');

-- 샘플 확인 (최신 20건)
select
    id,
    individual_member_id,
    point_type,
    point_amount,
    remaining_point_amount,
    payment_amount,
    created_datetime
from tbl_point_details
where payment_order_id like 'TEST-POINT-BULK-%'
order by created_datetime desc, id desc
limit 20;

-- 보유 포인트 확인
select
    id as individual_member_id,
    individual_member_point
from tbl_individual_member
order by id;
