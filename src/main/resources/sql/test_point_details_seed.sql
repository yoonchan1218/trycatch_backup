-- point/point 페이지 확인용 테스트 데이터 (individual_member_id = 7)
-- 실행 전 기존 테스트 데이터 정리
delete from tbl_point_details where individual_member_id = 7;

-- 1) 충전 - 취소 가능 (7일 이내, 미사용)
insert into tbl_point_details (individual_member_id, point_type, point_amount, remaining_point_amount, payment_amount, expire_datetime, payment_order_id, payment_receipt_id, created_datetime, updated_datetime)
values (7, 'earn', 100, 100, 11000, date_add(now(), interval 1 year), 'TEST-EARN-001', 'TEST-RECEIPT-001', now(), now());

-- 2) 충전 - 취소 가능 (3일 전, 미사용)
insert into tbl_point_details (individual_member_id, point_type, point_amount, remaining_point_amount, payment_amount, expire_datetime, payment_order_id, payment_receipt_id, created_datetime, updated_datetime)
values (7, 'earn', 50, 50, 5500, date_add(date_sub(now(), interval 3 day), interval 1 year), 'TEST-EARN-002', 'TEST-RECEIPT-002', date_sub(now(), interval 3 day), date_sub(now(), interval 3 day));

-- 3) 충전 - 취소 불가 (7일 초과)
insert into tbl_point_details (individual_member_id, point_type, point_amount, remaining_point_amount, payment_amount, expire_datetime, payment_order_id, payment_receipt_id, created_datetime, updated_datetime)
values (7, 'earn', 200, 200, 22000, date_add(date_sub(now(), interval 30 day), interval 1 year), 'TEST-EARN-003', 'TEST-RECEIPT-003', date_sub(now(), interval 30 day), date_sub(now(), interval 30 day));

-- 4) 충전 - 취소 불가 (일부 사용됨)
insert into tbl_point_details (individual_member_id, point_type, point_amount, remaining_point_amount, payment_amount, expire_datetime, payment_order_id, payment_receipt_id, created_datetime, updated_datetime)
values (7, 'earn', 300, 150, 33000, date_add(date_sub(now(), interval 2 day), interval 1 year), 'TEST-EARN-004', 'TEST-RECEIPT-004', date_sub(now(), interval 2 day), date_sub(now(), interval 2 day));

-- 5) 충전 - 이미 취소됨
insert into tbl_point_details (individual_member_id, point_type, point_amount, remaining_point_amount, payment_amount, expire_datetime, payment_order_id, payment_receipt_id, cancelled_datetime, created_datetime, updated_datetime)
values (7, 'earn', 500, 0, 55000, date_add(date_sub(now(), interval 5 day), interval 1 year), 'TEST-EARN-005', 'TEST-RECEIPT-005', date_sub(now(), interval 4 day), date_sub(now(), interval 5 day), date_sub(now(), interval 4 day));

-- 6) 사용
insert into tbl_point_details (individual_member_id, point_type, point_amount, remaining_point_amount, payment_amount, expire_datetime, payment_order_id, created_datetime, updated_datetime)
values (7, 'use', -30, 0, 0, null, null, date_sub(now(), interval 1 day), date_sub(now(), interval 1 day));

-- 7) 사용
insert into tbl_point_details (individual_member_id, point_type, point_amount, remaining_point_amount, payment_amount, expire_datetime, payment_order_id, created_datetime, updated_datetime)
values (7, 'use', -20, 0, 0, null, null, date_sub(now(), interval 10 day), date_sub(now(), interval 10 day));

-- 8) 소멸
insert into tbl_point_details (individual_member_id, point_type, point_amount, remaining_point_amount, payment_amount, expire_datetime, payment_order_id, created_datetime, updated_datetime)
values (7, 'expire', -50, 0, 0, null, null, date_sub(now(), interval 15 day), date_sub(now(), interval 15 day));

-- 9) 구매취소 내역
insert into tbl_point_details (individual_member_id, point_type, point_amount, remaining_point_amount, payment_amount, expire_datetime, payment_order_id, payment_receipt_id, created_datetime, updated_datetime)
values (7, 'purchase_cancel', -500, 0, -55000, date_sub(now(), interval 4 day), 'TEST-EARN-005', 'TEST-RECEIPT-005', date_sub(now(), interval 4 day), date_sub(now(), interval 4 day));

-- 10) 충전 - 취소 가능 (오늘, 미사용)
insert into tbl_point_details (individual_member_id, point_type, point_amount, remaining_point_amount, payment_amount, expire_datetime, payment_order_id, payment_receipt_id, created_datetime, updated_datetime)
values (7, 'earn', 1000, 1000, 110000, date_add(now(), interval 1 year), 'TEST-EARN-006', 'TEST-RECEIPT-006', now(), now());

-- 보유 포인트 동기화 (충전합계 - 사용/소멸/취소)
update tbl_individual_member
set individual_member_point = (
    select greatest(0, sum(point_amount))
    from tbl_point_details
    where individual_member_id = 7
)
where id = 7;

-- 확인
select id, point_type, point_amount, remaining_point_amount, payment_amount, cancelled_datetime, created_datetime
from tbl_point_details
where individual_member_id = 7
order by created_datetime desc;

select id, individual_member_point from tbl_individual_member where id = 7;
