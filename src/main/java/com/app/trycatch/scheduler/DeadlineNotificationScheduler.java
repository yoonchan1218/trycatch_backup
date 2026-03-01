package com.app.trycatch.scheduler;

import com.app.trycatch.dto.experience.ExperienceProgramDTO;
import com.app.trycatch.mapper.experience.ExperienceProgramMapper;
import com.app.trycatch.service.Alarm.CorporateAlramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeadlineNotificationScheduler {
    private final ExperienceProgramMapper experienceProgramMapper;
    private final CorporateAlramService corporateAlramService;

    /** 서버 시작 시 밀린 상태 전환 즉시 실행 */
    @PostConstruct
    public void onStartup() {
        log.info("서버 시작: 공고 상태 일괄 전환 실행");
        updateProgramStatuses();
    }

    /** 매일 자정: 공고 상태 자동 전환 (모집마감 → 진행중 → 진행종료) */
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void updateProgramStatuses() {
        String today = LocalDate.now().toString();

        // 1. 마감일 지난 모집중 → 모집마감
        int closed = experienceProgramMapper.closeExpiredPrograms(today);
        if (closed > 0) {
            log.info("마감일 지난 공고 {}건 모집마감 처리 완료", closed);
        }

        // 2. 체험 시작일 도달 모집마감 → 진행중
        int started = experienceProgramMapper.startPrograms(today);
        if (started > 0) {
            log.info("체험 시작 공고 {}건 진행중 처리 완료", started);
        }

        // 3. 체험 종료일 지난 진행중 → 진행종료
        int finished = experienceProgramMapper.finishPrograms(today);
        if (finished > 0) {
            log.info("체험 종료 공고 {}건 진행종료 처리 완료", finished);
        }
    }

    /** 매일 오전 9시: 오늘 마감인 공고 알림 발송 */
    @Scheduled(cron = "0 0 9 * * *")
    public void notifyDeadlineToday() {
        String today = LocalDate.now().toString();
        List<ExperienceProgramDTO> programs = experienceProgramMapper.selectByDeadline(today);
        for (ExperienceProgramDTO p : programs) {
            corporateAlramService.notify(
                    p.getCorpId(),
                    "experience_apply_received",
                    "공고 마감일 안내",
                    "[" + p.getExperienceProgramTitle() + "] 프로그램이 오늘 마감됩니다."
            );
        }
    }
}
