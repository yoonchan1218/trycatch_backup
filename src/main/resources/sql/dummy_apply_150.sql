-- ============================================================
-- 체험지원현황 더미데이터 150건 (모집인원 제한 준수)
-- 규칙: 지원자수(non-cancelled) <= 모집인원
--       지원자수 == 모집인원 → 공고 status = 'closed'
-- ============================================================

SET foreign_key_checks = 0;

-- 0) 기존 더미 지원 데이터 삭제
DELETE FROM tbl_apply WHERE member_id IN (SELECT id FROM tbl_member WHERE member_id LIKE 'dummyapply%');
DELETE FROM tbl_apply WHERE member_id IN (6,7,8,9);

-- 기존 더미 회원이 없으면 생성
SET @m_check = (SELECT id FROM tbl_member WHERE member_id = 'dummyapply01' LIMIT 1);

-- 더미 회원이 이미 있으면 재사용, 없으면 새로 생성
-- (이전 실행에서 이미 생성됨)

INSERT IGNORE INTO tbl_address (address_zipcode, address_address, address_detail)
VALUES ('06134','서울특별시 강남구 역삼동','테헤란로 201'),
       ('04524','서울특별시 중구 을지로','세종대로 101'),
       ('13487','경기도 성남시 분당구','판교로 200'),
       ('34012','대전광역시 유성구 궁동','대학로 100'),
       ('48058','부산광역시 해운대구 좌동','해운대로 50'),
       ('61452','광주광역시 서구 치평동','상무대로 77'),
       ('41068','경기도 수원시 영통구','광교로 33'),
       ('22004','인천광역시 남동구 간석동','인하로 55'),
       ('63197','제주특별자치도 제주시','연동로 22'),
       ('24354','강원도 춘천시 효자동','강원대학로 11'),
       ('31085','충청남도 천안시 동남구','대흥로 66'),
       ('54896','전라북도 전주시 완산구','전주로 44'),
       ('42700','대구광역시 수성구 범어동','달구벌대로 88'),
       ('44677','울산광역시 남구 삼산동','삼산로 99'),
       ('51741','경상남도 창원시 의창구','창원대로 12');

SET @addr_base = LAST_INSERT_ID();

INSERT IGNORE INTO tbl_member (member_id, member_password, member_name, member_email, member_phone, address_id, member_status)
VALUES ('dummyapply01','$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe','강민수','dummyapply01@test.com','010-3333-0001',@addr_base,     'active'),
       ('dummyapply02','$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe','오수정','dummyapply02@test.com','010-3333-0002',@addr_base + 1, 'active'),
       ('dummyapply03','$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe','임재혁','dummyapply03@test.com','010-3333-0003',@addr_base + 2, 'active'),
       ('dummyapply04','$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe','배유진','dummyapply04@test.com','010-3333-0004',@addr_base + 3, 'active'),
       ('dummyapply05','$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe','노건우','dummyapply05@test.com','010-3333-0005',@addr_base + 4, 'active'),
       ('dummyapply06','$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe','문하늘','dummyapply06@test.com','010-3333-0006',@addr_base + 5, 'active'),
       ('dummyapply07','$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe','신동현','dummyapply07@test.com','010-3333-0007',@addr_base + 6, 'active'),
       ('dummyapply08','$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe','양서윤','dummyapply08@test.com','010-3333-0008',@addr_base + 7, 'active'),
       ('dummyapply09','$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe','조승민','dummyapply09@test.com','010-3333-0009',@addr_base + 8, 'active'),
       ('dummyapply10','$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe','권나영','dummyapply10@test.com','010-3333-0010',@addr_base + 9, 'active'),
       ('dummyapply11','$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe','유정훈','dummyapply11@test.com','010-3333-0011',@addr_base + 10,'active'),
       ('dummyapply12','$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe','장미래','dummyapply12@test.com','010-3333-0012',@addr_base + 11,'active'),
       ('dummyapply13','$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe','홍석진','dummyapply13@test.com','010-3333-0013',@addr_base + 12,'active'),
       ('dummyapply14','$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe','전다은','dummyapply14@test.com','010-3333-0014',@addr_base + 13,'active'),
       ('dummyapply15','$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe','서준영','dummyapply15@test.com','010-3333-0015',@addr_base + 14,'active');

SET @m = (SELECT id FROM tbl_member WHERE member_id = 'dummyapply01');

INSERT IGNORE INTO tbl_individual_member (id, individual_member_birth, individual_member_gender, individual_member_education)
VALUES (@m,      '1997-03-15','man',  '대학교(4년) 졸업'),
       (@m + 1,  '1999-07-22','women','대학(2,3년) 졸업'),
       (@m + 2,  '1996-11-05','man',  '대학교(4년) 졸업'),
       (@m + 3,  '2000-01-30','women','대학원 졸업'),
       (@m + 4,  '1995-09-12','man',  '고등학교 졸업'),
       (@m + 5,  '2001-04-18','women','대학(2,3년) 졸업'),
       (@m + 6,  '1998-12-25','man',  '대학원 졸업'),
       (@m + 7,  '2002-06-08','women','대학교(4년) 졸업'),
       (@m + 8,  '1997-08-20','man',  '대학교(4년) 졸업'),
       (@m + 9,  '2000-02-14','women','대학(2,3년) 졸업'),
       (@m + 10, '1996-05-11','man',  '대학원 졸업'),
       (@m + 11, '2001-10-03','women','고등학교 졸업'),
       (@m + 12, '1999-07-07','man',  '대학교(4년) 졸업'),
       (@m + 13, '2000-12-19','women','대학(2,3년) 졸업'),
       (@m + 14, '1998-01-25','man',  '대학교(4년) 졸업');

-- ============================================================
-- 지원 데이터 150건
-- 프로그램 1~30 사용, 모집인원 제한 준수
-- [CLOSED] 표시 = active수 == 모집인원 → 마감 처리 대상
-- ============================================================

INSERT INTO tbl_apply (experience_program_id, member_id, apply_status, created_datetime) VALUES

-- P1 (모집5) : active 5 + cancelled 1 = 6건 [CLOSED]
(1, 6,     'applied',       '2025-12-01 09:10:00'),
(1, 7,     'document_pass', '2025-12-01 09:25:00'),
(1, 8,     'applied',       '2025-12-01 10:00:00'),
(1, 9,     'document_fail', '2025-12-01 10:30:00'),
(1, @m,    'document_pass', '2025-12-01 11:00:00'),
(1, @m+1,  'cancelled',     '2025-12-01 11:30:00'),

-- P2 (모집3) : active 3 = 3건 [CLOSED]
(2, 6,     'applied',       '2025-12-03 09:00:00'),
(2, 7,     'document_pass', '2025-12-03 09:40:00'),
(2, 8,     'document_fail', '2025-12-03 10:20:00'),

-- P3 (모집2) : active 2 = 2건 [CLOSED]
(3, 6,     'document_pass', '2025-12-05 09:00:00'),
(3, 9,     'applied',       '2025-12-05 09:45:00'),

-- P4 (모집4) : active 4 = 4건 [CLOSED]
(4, 7,     'applied',       '2025-12-07 09:00:00'),
(4, 8,     'document_pass', '2025-12-07 09:30:00'),
(4, 9,     'applied',       '2025-12-07 10:00:00'),
(4, @m,    'document_fail', '2025-12-07 10:45:00'),

-- P5 (모집2) : active 2 = 2건 [CLOSED]
(5, @m+1,  'applied',       '2025-12-09 09:00:00'),
(5, @m+2,  'document_pass', '2025-12-09 09:30:00'),

-- P6 (모집6) : active 5 + cancelled 1 = 6건
(6, 6,     'applied',       '2025-12-11 09:00:00'),
(6, 7,     'document_pass', '2025-12-11 09:30:00'),
(6, @m+3,  'applied',       '2025-12-11 10:00:00'),
(6, @m+4,  'document_fail', '2025-12-11 10:30:00'),
(6, @m+5,  'document_pass', '2025-12-11 11:00:00'),
(6, @m+6,  'cancelled',     '2025-12-11 11:30:00'),

-- P7 (모집7) : active 5 = 5건
(7, 8,     'applied',       '2025-12-13 09:00:00'),
(7, 9,     'document_pass', '2025-12-13 09:30:00'),
(7, @m+2,  'applied',       '2025-12-13 10:00:00'),
(7, @m+7,  'document_fail', '2025-12-13 10:30:00'),
(7, @m+8,  'applied',       '2025-12-13 11:00:00'),

-- P8 (모집8) : active 5 + cancelled 1 = 6건
(8, 6,     'document_pass', '2025-12-15 09:00:00'),
(8, @m+3,  'applied',       '2025-12-15 09:30:00'),
(8, @m+5,  'document_pass', '2025-12-15 10:00:00'),
(8, @m+9,  'applied',       '2025-12-15 10:30:00'),
(8, @m+10, 'document_fail', '2025-12-15 11:00:00'),
(8, @m+11, 'cancelled',     '2025-12-15 11:30:00'),

-- P9 (모집9) : active 6 = 6건
(9, 7,     'applied',       '2025-12-17 09:00:00'),
(9, @m,    'document_pass', '2025-12-17 09:30:00'),
(9, @m+4,  'applied',       '2025-12-17 10:00:00'),
(9, @m+6,  'document_pass', '2025-12-17 10:30:00'),
(9, @m+12, 'applied',       '2025-12-17 11:00:00'),
(9, @m+13, 'document_fail', '2025-12-17 11:30:00'),

-- P10 (모집10) : active 7 + cancelled 1 = 8건
(10, 8,     'applied',       '2025-12-19 09:00:00'),
(10, 9,     'document_pass', '2025-12-19 09:20:00'),
(10, @m+1,  'applied',       '2025-12-19 09:40:00'),
(10, @m+7,  'document_pass', '2025-12-19 10:00:00'),
(10, @m+8,  'document_fail', '2025-12-19 10:20:00'),
(10, @m+9,  'applied',       '2025-12-19 10:40:00'),
(10, @m+14, 'document_pass', '2025-12-19 11:00:00'),
(10, @m+10, 'cancelled',     '2025-12-19 11:20:00'),

-- P11 (모집11) : active 5 = 5건
(11, 6,     'applied',       '2025-12-21 09:00:00'),
(11, @m+2,  'document_pass', '2025-12-21 09:30:00'),
(11, @m+5,  'applied',       '2025-12-21 10:00:00'),
(11, @m+11, 'document_pass', '2025-12-21 10:30:00'),
(11, @m+14, 'applied',       '2025-12-21 11:00:00'),

-- P12 (모집12) : active 5 = 5건
(12, 7,     'document_fail', '2025-12-23 09:00:00'),
(12, 8,     'applied',       '2025-12-23 09:30:00'),
(12, @m+3,  'document_pass', '2025-12-23 10:00:00'),
(12, @m+6,  'applied',       '2025-12-23 10:30:00'),
(12, @m+12, 'document_pass', '2025-12-23 11:00:00'),

-- P13 (모집13) : active 5 + cancelled 1 = 6건
(13, 9,     'applied',       '2025-12-25 09:00:00'),
(13, @m,    'document_pass', '2025-12-25 09:30:00'),
(13, @m+1,  'applied',       '2025-12-25 10:00:00'),
(13, @m+7,  'document_fail', '2025-12-25 10:30:00'),
(13, @m+9,  'document_pass', '2025-12-25 11:00:00'),
(13, @m+13, 'cancelled',     '2025-12-25 11:30:00'),

-- P14 (모집14) : active 5 = 5건
(14, @m+4,  'applied',       '2025-12-27 09:00:00'),
(14, @m+8,  'document_pass', '2025-12-27 09:30:00'),
(14, @m+10, 'applied',       '2025-12-27 10:00:00'),
(14, @m+11, 'document_fail', '2025-12-27 10:30:00'),
(14, @m+14, 'document_pass', '2025-12-27 11:00:00'),

-- P15 (모집5) : active 5 = 5건 [CLOSED]
(15, 6,     'document_pass', '2026-01-02 09:00:00'),
(15, 7,     'applied',       '2026-01-02 09:30:00'),
(15, @m+2,  'document_fail', '2026-01-02 10:00:00'),
(15, @m+3,  'document_pass', '2026-01-02 10:30:00'),
(15, @m+12, 'applied',       '2026-01-02 11:00:00'),

-- P16 (모집6) : active 6 = 6건 [CLOSED]
(16, 8,     'applied',       '2026-01-05 09:00:00'),
(16, 9,     'document_pass', '2026-01-05 09:20:00'),
(16, @m,    'applied',       '2026-01-05 09:40:00'),
(16, @m+5,  'document_pass', '2026-01-05 10:00:00'),
(16, @m+6,  'document_fail', '2026-01-05 10:20:00'),
(16, @m+9,  'applied',       '2026-01-05 10:40:00'),

-- P17 (모집7) : active 5 = 5건
(17, @m+1,  'document_pass', '2026-01-08 09:00:00'),
(17, @m+4,  'applied',       '2026-01-08 09:30:00'),
(17, @m+7,  'document_pass', '2026-01-08 10:00:00'),
(17, @m+10, 'applied',       '2026-01-08 10:30:00'),
(17, @m+13, 'document_fail', '2026-01-08 11:00:00'),

-- P18 (모집8) : active 5 + cancelled 1 = 6건
(18, 6,     'applied',       '2026-01-11 09:00:00'),
(18, @m+2,  'document_pass', '2026-01-11 09:30:00'),
(18, @m+8,  'applied',       '2026-01-11 10:00:00'),
(18, @m+11, 'document_pass', '2026-01-11 10:30:00'),
(18, @m+12, 'document_fail', '2026-01-11 11:00:00'),
(18, @m+14, 'cancelled',     '2026-01-11 11:30:00'),

-- P19 (모집9) : active 6 = 6건
(19, 7,     'document_pass', '2026-01-14 09:00:00'),
(19, 8,     'applied',       '2026-01-14 09:30:00'),
(19, @m+3,  'document_pass', '2026-01-14 10:00:00'),
(19, @m+5,  'document_fail', '2026-01-14 10:30:00'),
(19, @m+6,  'applied',       '2026-01-14 11:00:00'),
(19, @m+13, 'document_pass', '2026-01-14 11:30:00'),

-- P20 (모집10) : active 7 + cancelled 1 = 8건
(20, 9,     'applied',       '2026-01-17 09:00:00'),
(20, @m,    'document_pass', '2026-01-17 09:20:00'),
(20, @m+1,  'applied',       '2026-01-17 09:40:00'),
(20, @m+4,  'document_pass', '2026-01-17 10:00:00'),
(20, @m+7,  'document_fail', '2026-01-17 10:20:00'),
(20, @m+9,  'applied',       '2026-01-17 10:40:00'),
(20, @m+10, 'document_pass', '2026-01-17 11:00:00'),
(20, @m+14, 'cancelled',     '2026-01-17 11:20:00'),

-- P21 (모집11) : active 5 = 5건
(21, 6,     'document_pass', '2026-01-20 09:00:00'),
(21, @m+2,  'applied',       '2026-01-20 09:30:00'),
(21, @m+8,  'document_pass', '2026-01-20 10:00:00'),
(21, @m+11, 'applied',       '2026-01-20 10:30:00'),
(21, @m+12, 'document_fail', '2026-01-20 11:00:00'),

-- P22 (모집12) : active 5 = 5건
(22, 7,     'applied',       '2026-01-23 09:00:00'),
(22, 8,     'document_pass', '2026-01-23 09:30:00'),
(22, @m+3,  'applied',       '2026-01-23 10:00:00'),
(22, @m+6,  'document_pass', '2026-01-23 10:30:00'),
(22, @m+13, 'applied',       '2026-01-23 11:00:00'),

-- P23 (모집13) : active 5 + cancelled 1 = 6건
(23, 9,     'document_fail', '2026-01-26 09:00:00'),
(23, @m,    'applied',       '2026-01-26 09:30:00'),
(23, @m+1,  'document_pass', '2026-01-26 10:00:00'),
(23, @m+7,  'applied',       '2026-01-26 10:30:00'),
(23, @m+9,  'document_pass', '2026-01-26 11:00:00'),
(23, @m+14, 'cancelled',     '2026-01-26 11:30:00'),

-- P24 (모집14) : active 5 = 5건
(24, @m+4,  'document_pass', '2026-01-29 09:00:00'),
(24, @m+5,  'applied',       '2026-01-29 09:30:00'),
(24, @m+10, 'document_pass', '2026-01-29 10:00:00'),
(24, @m+11, 'document_fail', '2026-01-29 10:30:00'),
(24, @m+12, 'applied',       '2026-01-29 11:00:00'),

-- P25 (모집5) : active 5 = 5건 [CLOSED]
(25, @m+2,  'applied',       '2026-02-01 09:00:00'),
(25, @m+6,  'document_pass', '2026-02-01 09:30:00'),
(25, @m+8,  'applied',       '2026-02-01 10:00:00'),
(25, @m+13, 'document_fail', '2026-02-01 10:30:00'),
(25, @m+14, 'document_pass', '2026-02-01 11:00:00'),

-- P26 (모집6) : active 3 + cancelled 1 = 4건
(26, 6,     'applied',       '2026-02-04 09:00:00'),
(26, @m+3,  'document_pass', '2026-02-04 09:30:00'),
(26, @m+7,  'cancelled',     '2026-02-04 10:00:00'),
(26, @m+9,  'applied',       '2026-02-04 10:30:00'),

-- P27 (모집7) : active 4 = 4건
(27, 7,     'document_pass', '2026-02-07 09:00:00'),
(27, @m+4,  'applied',       '2026-02-07 09:30:00'),
(27, @m+10, 'document_pass', '2026-02-07 10:00:00'),
(27, @m+11, 'applied',       '2026-02-07 10:30:00'),

-- P28 (모집8) : active 4 = 4건
(28, 8,     'applied',       '2026-02-10 09:00:00'),
(28, @m+5,  'document_pass', '2026-02-10 09:30:00'),
(28, @m+12, 'document_fail', '2026-02-10 10:00:00'),
(28, @m+13, 'applied',       '2026-02-10 10:30:00'),

-- P29 (모집9) : active 3 = 3건
(29, 9,     'document_pass', '2026-02-13 09:00:00'),
(29, @m,    'applied',       '2026-02-13 09:30:00'),
(29, @m+1,  'document_fail', '2026-02-13 10:00:00'),

-- P30 (모집10) : active 4 = 4건
(30, @m+2,  'applied',       '2026-02-16 09:00:00'),
(30, @m+6,  'document_pass', '2026-02-16 09:30:00'),
(30, @m+8,  'applied',       '2026-02-16 10:00:00'),
(30, @m+14, 'document_pass', '2026-02-16 10:30:00');

-- ============================================================
-- 공고 상태 업데이트: 지원자수 == 모집인원 → closed
-- 해당 프로그램: 1,2,3,4,5,15,16,25
-- ============================================================
UPDATE tbl_experience_program
SET experience_program_status = 'closed', updated_datetime = NOW()
WHERE id IN (1, 2, 3, 4, 5, 15, 16, 25);

-- 나머지 프로그램은 모집중 유지 (아직 자리 남음)
UPDATE tbl_experience_program
SET experience_program_status = 'recruiting', updated_datetime = NOW()
WHERE id IN (6,7,8,9,10,11,12,13,14,17,18,19,20,21,22,23,24,26,27,28,29,30);

SET foreign_key_checks = 1;

-- ============================================================
-- 검증 쿼리
-- ============================================================
SELECT '총 지원 건수' AS label, COUNT(*) AS cnt FROM tbl_apply;

SELECT
    ep.id AS program_id,
    ep.experience_program_recruitment_count AS recruit,
    SUM(CASE WHEN a.apply_status != 'cancelled' THEN 1 ELSE 0 END) AS active_cnt,
    COUNT(*) AS total_cnt,
    ep.experience_program_status AS status,
    CASE
        WHEN SUM(CASE WHEN a.apply_status != 'cancelled' THEN 1 ELSE 0 END) > ep.experience_program_recruitment_count THEN '!! OVER !!'
        WHEN SUM(CASE WHEN a.apply_status != 'cancelled' THEN 1 ELSE 0 END) = ep.experience_program_recruitment_count THEN 'FULL'
        ELSE 'OK'
    END AS check_result
FROM tbl_experience_program ep
JOIN tbl_apply a ON a.experience_program_id = ep.id
GROUP BY ep.id
ORDER BY ep.id;
