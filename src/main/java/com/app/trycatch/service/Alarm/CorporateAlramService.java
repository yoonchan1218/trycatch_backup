package com.app.trycatch.service.Alarm;

import com.app.trycatch.dto.alarm.CorpAlramDTO;
import com.app.trycatch.repository.alarm.CorporateAlramDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CorporateAlramService {
    private final CorporateAlramDAO corporateAlramDAO;

    public List<CorpAlramDTO> list(Long corpId) {
        return corporateAlramDAO.findAllByCorpId(corpId);
    }

    public boolean hasUnread(Long corpId) {
        return corporateAlramDAO.findUnreadCountByCorpId(corpId) > 0;
    }

    public void readAll(Long corpId) {
        corporateAlramDAO.setReadByCorpId(corpId);
    }

    public void notify(Long corpId, String type, String title, String content) {
        notify(corpId, type, title, content, null);
    }

    public void notify(Long corpId, String type, String title, String content, Long relatedId) {
        CorpAlramDTO dto = new CorpAlramDTO();
        dto.setCorpId(corpId);
        dto.setNotificationType(type);
        dto.setNotificationTitle(title);
        dto.setNotificationContent(content);
        dto.setNotificationRelatedId(relatedId);
        corporateAlramDAO.save(dto);
    }
}
