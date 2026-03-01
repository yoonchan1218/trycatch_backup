package com.app.trycatch.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class NotificationInsertTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void insertTestNotifications() {
        long memberId = 7L;

        // 기존 데이터 삭제
        int deleted = jdbcTemplate.update(
                "DELETE FROM tbl_main_notification WHERE member_id = ?", memberId);
        log.info("기존 알림 {}건 삭제", deleted);

        // 유효한 FK ID 조회
        List<Map<String, Object>> qnaList = jdbcTemplate.queryForList(
                "SELECT id FROM tbl_qna ORDER BY id LIMIT 2");
        List<Map<String, Object>> programList = jdbcTemplate.queryForList(
                "SELECT id FROM tbl_experience_program ORDER BY id LIMIT 2");
        List<Map<String, Object>> skillLogList = jdbcTemplate.queryForList(
                "SELECT id FROM tbl_skill_log ORDER BY id LIMIT 2");

        Long qnaId1 = qnaList.size() > 0 ? (Long) qnaList.get(0).get("id") : null;
        Long qnaId2 = qnaList.size() > 1 ? (Long) qnaList.get(1).get("id") : null;
        Long programId1 = programList.size() > 0 ? (Long) programList.get(0).get("id") : null;
        Long programId2 = programList.size() > 1 ? (Long) programList.get(1).get("id") : null;
        Long skillLogId1 = skillLogList.size() > 0 ? (Long) skillLogList.get(0).get("id") : null;
        Long skillLogId2 = skillLogList.size() > 1 ? (Long) skillLogList.get(1).get("id") : null;

        log.info("FK IDs - qna: {}/{}, program: {}/{}, skillLog: {}/{}",
                qnaId1, qnaId2, programId1, programId2, skillLogId1, skillLogId2);

        // QnA 알림 2건
        jdbcTemplate.update(
                "INSERT INTO tbl_main_notification (member_id, notification_type, notification_title, notification_content, notification_is_read, qna_id) VALUES (?, 'qna', ?, NULL, false, ?)",
                memberId, "취업톡톡 답변이 달렸어요", qnaId1);
        jdbcTemplate.update(
                "INSERT INTO tbl_main_notification (member_id, notification_type, notification_title, notification_content, notification_is_read, qna_id) VALUES (?, 'qna', ?, NULL, false, ?)",
                memberId, "취업톡톡 답변이 달렸어요 (2)", qnaId2);

        // 체험지원현황 알림 2건
        jdbcTemplate.update(
                "INSERT INTO tbl_main_notification (member_id, notification_type, notification_title, notification_content, notification_is_read, experience_program_id) VALUES (?, 'experience_apply', ?, 'applied', false, ?)",
                memberId, "[백엔드 개발 체험] 지원 현황 업데이트", programId1);
        jdbcTemplate.update(
                "INSERT INTO tbl_main_notification (member_id, notification_type, notification_title, notification_content, notification_is_read, experience_program_id) VALUES (?, 'experience_apply', ?, 'document_pass', false, ?)",
                memberId, "[프론트엔드 UI 체험] 지원 현황 업데이트", programId2);

        // 기술블로그 알림 2건
        jdbcTemplate.update(
                "INSERT INTO tbl_main_notification (member_id, notification_type, notification_title, notification_content, notification_is_read, skill_log_id) VALUES (?, 'skill_log', ?, NULL, false, ?)",
                memberId, "기술블로그에 댓글이 달렸어요", skillLogId1);
        jdbcTemplate.update(
                "INSERT INTO tbl_main_notification (member_id, notification_type, notification_title, notification_content, notification_is_read, skill_log_id) VALUES (?, 'skill_log', ?, NULL, false, ?)",
                memberId, "기술블로그에 댓글이 달렸어요 (2)", skillLogId2);

        // 결과 확인
        List<Map<String, Object>> result = jdbcTemplate.queryForList(
                "SELECT id, notification_type, notification_title, notification_is_read FROM tbl_main_notification WHERE member_id = ? ORDER BY created_datetime DESC",
                memberId);
        result.forEach(row -> log.info("삽입됨: {}", row));
    }
}
