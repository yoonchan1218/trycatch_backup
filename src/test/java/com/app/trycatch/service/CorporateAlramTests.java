package com.app.trycatch.service;

import com.app.trycatch.dto.alarm.CorpAlramDTO;
import com.app.trycatch.service.Alarm.CorporateAlramService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
public class CorporateAlramTests {

    @Autowired
    private CorporateAlramService corporateAlramService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Long TEST_CORP_ID;
    private List<String> enumValues;
    private int insertedCount;

    // 더미데이터 매핑 (enum값 -> title, content, relatedId)
    private static final Map<String, String[]> DUMMY_MAP = Map.of(
            "apply_received", new String[]{
                    "새로운 지원자가 있습니다",
                    "홍길동님이 [백엔드 개발 체험] 프로그램에 지원하였습니다.", "10"
            },
            "experience_apply_received", new String[]{
                    "체험 공고가 마감되었습니다",
                    "[프론트엔드 체험] 공고의 모집이 마감되었습니다.", "20"
            },
            "qna", new String[]{
                    "커뮤니티 질문에 답변이 달렸습니다",
                    "작성하신 답변에 좋아요가 눌렸습니다.", "30"
            }
    );

    @BeforeEach
    public void insertDummyData() {
        // DB에 실제 존재하는 기업 ID 조회
        TEST_CORP_ID = jdbcTemplate.queryForObject(
                "select id from tbl_corp order by id limit 1", Long.class
        );

        // DB에서 실제 enum 값 조회
        String columnType = jdbcTemplate.queryForObject(
                "select column_type from information_schema.columns "
                + "where table_schema = database() and table_name = 'tbl_corp_notification' "
                + "and column_name = 'notification_type'", String.class
        );
        enumValues = parseEnum(columnType);

        log.info("===== 테스트 기업 ID: {} =====", TEST_CORP_ID);
        log.info("===== DB enum 값: {} =====", enumValues);

        // 기존 테스트 데이터 정리
        jdbcTemplate.update(
                "delete from tbl_corp_notification where corp_id = ?", TEST_CORP_ID
        );

        // enum별 더미데이터 INSERT
        insertedCount = 0;
        for (String enumVal : enumValues) {
            String[] data = DUMMY_MAP.get(enumVal);
            String title = data != null ? data[0] : enumVal + " 테스트 알림";
            String content = data != null ? data[1] : enumVal + " 테스트 내용입니다.";
            long relatedId = data != null ? Long.parseLong(data[2]) : 99;

            jdbcTemplate.update(
                    "insert into tbl_corp_notification "
                    + "(corp_id, notification_type, notification_title, notification_content, notification_related_id) "
                    + "values (?, ?, ?, ?, ?)",
                    TEST_CORP_ID, enumVal, title, content, relatedId
            );
            insertedCount++;
            log.info("  INSERT: type={}, title={}", enumVal, title);
        }
        log.info("===== 더미 데이터 {}건 INSERT 완료 =====", insertedCount);
    }

    private List<String> parseEnum(String columnType) {
        // "enum('apply_received','experience_apply_received')" -> List
        List<String> values = new ArrayList<>();
        Matcher m = Pattern.compile("'([^']+)'").matcher(columnType);
        while (m.find()) {
            values.add(m.group(1));
        }
        return values;
    }

    // ── enum별 알림 조회 테스트 ─────────────────────────

    @Test
    public void test_enum별_알림_각각_조회() {
        List<CorpAlramDTO> alrams = corporateAlramService.list(TEST_CORP_ID);

        for (String enumVal : enumValues) {
            CorpAlramDTO found = alrams.stream()
                    .filter(a -> enumVal.equals(a.getNotificationType()))
                    .findFirst()
                    .orElse(null);

            log.info("==== {} ====", enumVal);
            log.info("  found: {}", found);

            assertNotNull(found, enumVal + " 알림이 존재해야 합니다");
            assertEquals(TEST_CORP_ID, found.getCorpId());
            assertFalse(found.getNotificationIsRead());
            log.info("  [PASS] {} 정상 조회", enumVal);
        }
    }

    // ── 전체 enum 조회 테스트 ───────────────────────────

    @Test
    public void test_전체_enum_조회() {
        List<CorpAlramDTO> alrams = corporateAlramService.list(TEST_CORP_ID);

        log.info("==== 전체 알림 조회 (corpId={}) ====", TEST_CORP_ID);
        log.info("  총 개수: {}", alrams.size());
        alrams.forEach(a -> log.info("  type={}, title={}, relatedId={}, isRead={}",
                a.getNotificationType(), a.getNotificationTitle(),
                a.getNotificationRelatedId(), a.getNotificationIsRead()));

        assertEquals(insertedCount, alrams.size(),
                "더미 데이터 " + insertedCount + "건이 조회되어야 합니다");

        for (String enumVal : enumValues) {
            long count = alrams.stream()
                    .filter(a -> enumVal.equals(a.getNotificationType()))
                    .count();
            assertEquals(1, count, enumVal + " 1건");
        }
        log.info("  [PASS] enum {}종 모두 정상 조회", enumValues.size());
    }

    // ── readAll 후 전체 읽음처리 테스트 ──────────────────

    @Test
    public void test_readAll_후_알림_0건() {
        List<CorpAlramDTO> before = corporateAlramService.list(TEST_CORP_ID);
        log.info("==== readAll 테스트 ====");
        log.info("  읽음 처리 전: {}건", before.size());
        assertEquals(insertedCount, before.size());

        corporateAlramService.readAll(TEST_CORP_ID);

        List<CorpAlramDTO> after = corporateAlramService.list(TEST_CORP_ID);
        log.info("  읽음 처리 후: {}건", after.size());

        assertEquals(0, after.size(), "readAll 후 미읽은 알림 0건이어야 합니다");
        log.info("  [PASS] readAll 정상 작동");
    }

    // ── DTO 필드 매핑 검증 ──────────────────────────────

    @Test
    public void test_DTO_필드_매핑_검증() {
        List<CorpAlramDTO> alrams = corporateAlramService.list(TEST_CORP_ID);
        assertFalse(alrams.isEmpty(), "알림이 1건 이상 있어야 합니다");

        CorpAlramDTO first = alrams.get(0);

        log.info("==== DTO 필드 매핑 검증 ====");
        log.info("  id: {}", first.getId());
        log.info("  corpId: {}", first.getCorpId());
        log.info("  notificationType: {}", first.getNotificationType());
        log.info("  notificationTitle: {}", first.getNotificationTitle());
        log.info("  notificationContent: {}", first.getNotificationContent());
        log.info("  notificationRelatedId: {}", first.getNotificationRelatedId());
        log.info("  notificationIsRead: {}", first.getNotificationIsRead());
        log.info("  createdDatetime: {}", first.getCreatedDatetime());

        assertNotNull(first.getId());
        assertNotNull(first.getCorpId());
        assertNotNull(first.getNotificationType());
        assertNotNull(first.getNotificationTitle());
        assertNotNull(first.getNotificationContent());
        assertNotNull(first.getNotificationRelatedId());
        assertNotNull(first.getNotificationIsRead());
        assertNotNull(first.getCreatedDatetime());
        log.info("  [PASS] 모든 필드 정상 매핑");
    }
}
