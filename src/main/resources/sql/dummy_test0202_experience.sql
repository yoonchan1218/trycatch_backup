-- ============================================================
-- test0202 체험지원현황 페이징 테스트용 더미데이터 (110건)
-- rowCount=10 기준 → 11페이지 이상 생성
-- 재실행 가능 (기존 테스트 데이터 먼저 삭제)
-- ============================================================

SET foreign_key_checks = 0;

-- test0202 회원 ID 조회
SET @test_member = (SELECT id FROM tbl_member WHERE member_id = 'test0202' LIMIT 1);

-- 기존 기업 ID 조회 (첫 번째 기업 사용)
SET @corp_id = (SELECT id FROM tbl_corp ORDER BY id LIMIT 1);

-- ============================================================
-- 0) 기존 테스트 데이터 삭제 (재실행 시 중복 방지)
-- ============================================================
DELETE FROM tbl_apply
WHERE member_id = @test_member
  AND experience_program_id IN (
      SELECT id FROM (
          SELECT id FROM tbl_experience_program
          WHERE experience_program_title LIKE '페이징테스트%'
      ) AS t
  );

DELETE FROM tbl_experience_program
WHERE experience_program_title LIKE '페이징테스트%';

-- ============================================================
-- 1) 체험 프로그램 110건 생성
-- ============================================================
INSERT INTO tbl_experience_program (
    corp_id,
    experience_program_title,
    experience_program_description,
    experience_program_level,
    experience_program_recruitment_count,
    experience_program_work_days,
    experience_program_work_hours,
    experience_program_deadline,
    experience_program_status,
    experience_program_view_count,
    experience_program_job
)
SELECT
    @corp_id,
    CONCAT('페이징테스트 프로그램 ', n),
    '페이징 테스트용 더미 프로그램입니다.',
    ELT(1 + MOD(n - 1, 5), 'a', 'b', 'c', 'd', 'e'),
    10,
    '월~금',
    '09:00 ~ 18:00',
    '2026-12-31',
    'recruiting',
    MOD(n * 7, 100),
    ELT(1 + MOD(n - 1, 5), '개발', '디자인', '마케팅', '영업', '기획')
FROM (
    SELECT a.N + b.N * 10 + 1 AS n
    FROM (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4
          UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a,
         (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4
          UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b
    HAVING n <= 110
    ORDER BY n
) nums;

-- 첫 번째 삽입된 프로그램 ID 저장
SET @first_ep_id = LAST_INSERT_ID();

-- ============================================================
-- 2) test0202 지원 데이터 110건 생성
--    최신순 정렬 확인을 위해 날짜를 1일 간격으로 설정
-- ============================================================
INSERT INTO tbl_apply (
    experience_program_id,
    member_id,
    apply_status,
    created_datetime
)
SELECT
    @first_ep_id + (n - 1),
    @test_member,
    ELT(1 + MOD(n - 1, 4), 'applied', 'document_pass', 'document_fail', 'cancelled'),
    DATE_SUB(NOW(), INTERVAL (110 - n) DAY)
FROM (
    SELECT a.N + b.N * 10 + 1 AS n
    FROM (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4
          UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a,
         (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4
          UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b
    HAVING n <= 110
    ORDER BY n
) nums;

SET foreign_key_checks = 1;

-- ============================================================
-- 검증 쿼리
-- ============================================================
SELECT '총 지원 건수' AS label, COUNT(*) AS cnt
FROM tbl_apply
WHERE member_id = @test_member;

SELECT '지원 상태별 건수' AS label, apply_status, COUNT(*) AS cnt
FROM tbl_apply
WHERE member_id = @test_member
GROUP BY apply_status;
