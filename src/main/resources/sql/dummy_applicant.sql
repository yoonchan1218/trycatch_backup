-- ============================================================
-- 지원자 관리 테스트용 더미데이터
-- 사전조건: tbl_experience_program id 1~5 존재
-- ============================================================

-- 1) 주소 더미
INSERT INTO tbl_address (address_zipcode, address_address, address_detail)
VALUES ('06134', '서울특별시 강남구 역삼동', '테헤란로 123'),
       ('04524', '서울특별시 중구 명동', '세종대로 45'),
       ('13487', '경기도 성남시 분당구', '판교로 100'),
       ('34012', '대전광역시 유성구 봉명동', '대학로 55'),
       ('48058', '부산광역시 해운대구 우동', '센텀중앙로 88'),
       ('61452', '광주광역시 동구 충장로', '금남로 33'),
       ('41068', '경기도 용인시 수지구', '풍덕천로 22'),
       ('22004', '인천광역시 연수구 송도동', '인천타워대로 77');

-- 2) 회원 (tbl_member) - 개인회원 8명
--    password는 BCrypt('1234') 예시
INSERT INTO tbl_member (member_id, member_password, member_name, member_email, member_phone, address_id, member_status, member_gender)
VALUES ('applicant01', '$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe', '김지원', 'jiwon@test.com', '010-1111-1001', LAST_INSERT_ID(), 'active', 'man'),
       ('applicant02', '$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe', '이서연', 'seoyeon@test.com', '010-1111-1002', LAST_INSERT_ID()+1, 'active', 'women'),
       ('applicant03', '$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe', '박준호', 'junho@test.com', '010-1111-1003', LAST_INSERT_ID()+2, 'active', 'man'),
       ('applicant04', '$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe', '최민지', 'minji@test.com', '010-1111-1004', LAST_INSERT_ID()+3, 'active', 'women'),
       ('applicant05', '$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe', '정현우', 'hyunwoo@test.com', '010-1111-1005', LAST_INSERT_ID()+4, 'active', 'women'),
       ('applicant06', '$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe', '한수빈', 'subin@test.com', '010-1111-1006', LAST_INSERT_ID()+5, 'active', 'women'),
       ('applicant07', '$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe', '윤태영', 'taeyoung@test.com', '010-1111-1007', LAST_INSERT_ID()+6, 'active', 'women'),
       ('applicant08', '$2a$10$X7UrH5AxmLHSMOPwYN.Sku3LJG1U7CUWY1MJaXdJPGW9j3E3fhwWe', '송예린', 'yerin@test.com', '010-1111-1008', LAST_INSERT_ID()+7, 'active', 'women');

-- 3) 방금 INSERT한 회원들의 ID 범위 확인용 (수동 실행)
-- SELECT id, member_name FROM tbl_member WHERE member_id LIKE 'applicant%';

-- 4) 개인회원 서브타입 (tbl_individual_member)
--    ※ 아래 @id 값을 위 SELECT 결과에 맞게 수정하세요
SET @base_id = (SELECT id FROM tbl_member WHERE member_id = 'applicant01');

INSERT INTO tbl_individual_member (id, individual_member_birth, individual_member_gender, individual_member_education)
VALUES (@base_id,     '1998-03-15', 'women', '대학교(4년) 졸업'),
       (@base_id + 1, '2000-07-22', 'women', '대학(2,3년) 졸업'),
       (@base_id + 2, '1997-11-05', 'man',   '대학교(4년) 졸업'),
       (@base_id + 3, '1999-01-30', 'women', '대학원 졸업'),
       (@base_id + 4, '1996-09-12', 'man',   '고등학교 졸업'),
       (@base_id + 5, '2001-04-18', 'women', '대학(2,3년) 졸업'),
       (@base_id + 6, '1995-12-25', 'man',   '대학원 졸업'),
       (@base_id + 7, '2002-06-08', 'women', '대학교(4년) 졸업');

-- 5) 지원 데이터 (tbl_apply)
--    프로그램 1~5에 골고루 분배, 초기 상태는 모두 applied(지원완료)
INSERT INTO tbl_apply (experience_program_id, member_id, apply_status, created_datetime)
VALUES
    -- 프로그램 1: 지원자 4명
    (1, @base_id,     'applied', '2026-02-20 09:00:00'),
    (1, @base_id + 1, 'applied', '2026-02-20 10:30:00'),
    (1, @base_id + 2, 'applied', '2026-02-19 14:00:00'),
    (1, @base_id + 3, 'applied', '2026-02-18 11:00:00'),

    -- 프로그램 2: 지원자 3명
    (2, @base_id + 4, 'applied', '2026-02-21 08:00:00'),
    (2, @base_id + 5, 'applied', '2026-02-21 09:30:00'),
    (2, @base_id + 6, 'applied', '2026-02-20 16:00:00'),

    -- 프로그램 3: 지원자 3명
    (3, @base_id + 1, 'applied', '2026-02-22 10:00:00'),
    (3, @base_id + 3, 'applied', '2026-02-21 15:00:00'),
    (3, @base_id + 7, 'applied', '2026-02-22 11:30:00'),

    -- 프로그램 4: 지원자 2명
    (4, @base_id + 2, 'applied', '2026-02-23 09:00:00'),
    (4, @base_id + 5, 'applied', '2026-02-22 13:00:00'),

    -- 프로그램 5: 지원자 3명
    (5, @base_id,     'applied', '2026-02-24 10:00:00'),
    (5, @base_id + 6, 'applied', '2026-02-24 11:00:00'),
    (5, @base_id + 7, 'applied', '2026-02-23 17:00:00');

-- 6) 뷰 생성 (이미 있으면 재생성)
CREATE OR REPLACE VIEW view_applicant AS
(
SELECT a.id, a.experience_program_id, a.member_id, a.apply_status,
       a.created_datetime, a.updated_datetime,
       m.member_name, m.member_email, m.member_phone,
       im.individual_member_birth, im.individual_member_gender, im.individual_member_education
FROM tbl_apply a
         JOIN tbl_member m ON a.member_id = m.id
         JOIN tbl_individual_member im ON m.id = im.id
);

-- 7) 확인
SELECT * FROM view_applicant WHERE experience_program_id = 1;
SELECT * FROM view_applicant WHERE experience_program_id = 2;
