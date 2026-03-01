-- ============================================================
-- tbl_main_notification 테스트 데이터 재삽입 (test0202, member_id=7)
-- 기존 데이터 삭제 후 재삽입 (notification_is_read = false 확인용)
-- ============================================================

-- 기존 데이터 삭제
delete from tbl_main_notification where member_id = 7;

-- 유효한 FK ID 확인 (아래 결과 확인 후 위의 insert에 반영)
select id, qna_title from tbl_qna order by id limit 5;
select id, experience_program_title from tbl_experience_program order by id limit 5;
select id, skill_log_title from tbl_skill_log order by id limit 5;

-- ============================================================
-- INSERT (FK 값은 위 조회 결과에 맞게 수정)
-- ============================================================

-- QnA 알림 2건
insert into tbl_main_notification
    (member_id, notification_type, notification_title, notification_content, notification_is_read, qna_id)
values
    (7, 'qna', '취업톡톡 답변이 달렸어요', null, false, 1),
    (7, 'qna', '취업톡톡 답변이 달렸어요 (2)', null, false, 2);

-- 체험지원현황 알림 2건
insert into tbl_main_notification
    (member_id, notification_type, notification_title, notification_content, notification_is_read, experience_program_id)
values
    (7, 'experience_apply', '[백엔드 개발 체험] 지원 현황이 업데이트됐어요', 'applied', false, 1),
    (7, 'experience_apply', '[프론트엔드 UI 체험] 지원 현황이 업데이트됐어요', 'document_pass', false, 2);

-- 기술블로그 알림 2건
insert into tbl_main_notification
    (member_id, notification_type, notification_title, notification_content, notification_is_read, skill_log_id)
values
    (7, 'skill_log', '기술블로그에 댓글이 달렸어요', null, false, 1),
    (7, 'skill_log', '기술블로그에 댓글이 달렸어요 (2)', null, false, 2);

-- 결과 확인
select id, notification_type, notification_title, notification_is_read
from tbl_main_notification
where member_id = 7
order by created_datetime desc;
