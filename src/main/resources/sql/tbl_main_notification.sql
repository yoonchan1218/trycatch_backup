create table tbl_main_notification
(
    id                        bigint unsigned auto_increment primary key comment '알림 ID',
    member_id      bigint unsigned not null comment '수신자 (개인회원) ID',
    notification_type         enum ('experience_apply', 'qna', 'skill_log') not null comment '알림 유형',
    notification_title        varchar(255) not null comment '제목',
    notification_content      text comment '내용',
    notification_is_read      boolean  default false comment '읽음 여부',
    qna_id                    bigint unsigned comment 'QnA 게시글 ID',
    experience_program_id     bigint unsigned comment '체험 프로그램 ID',
    skill_log_id              bigint unsigned comment '기술블로그 ID',
    created_datetime          datetime default current_timestamp,
    updated_datetime          datetime default current_timestamp ,
    constraint fk_main_notification_member foreign key (member_id) references tbl_individual_member (id),
    constraint fk_main_notification_qna foreign key (qna_id) references tbl_qna (id),
    constraint fk_main_notification_experience foreign key (experience_program_id) references tbl_experience_program (id),
    constraint fk_main_notification_skill_log foreign key (skill_log_id) references tbl_skill_log (id)
) comment '메인 헤더 알림 슈퍼타입';

select * from tbl_main_notification;
select * from tbl_qna;
select * from tbl_skill_log;
select * from tbl_experience_program;

select id, skill_log_title from tbl_skill_log;
select id, qna_title from tbl_qna;

select id, notification_title, notification_is_read from tbl_main_notification where member_id = 7;



-- QnA 답변 알림 (취업톡톡)
insert into tbl_main_notification (member_id, notification_type, notification_title, notification_content, qna_id)
values (7, 'qna', '취업톡톡 답변이 달렸어요', '회원님의 질문에 새로운 답변이 등록되었습니다.', null);

-- 체험 프로그램 지원 현황 알림 (심사중)
insert into tbl_main_notification (member_id, notification_type, notification_title, notification_content, experience_program_id)
values (7, 'experience_apply', '[백엔드 개발 체험] 지원 현황이 업데이트됐어요', 'applied', null);

-- 체험 프로그램 지원 현황 알림 (참여중)
insert into tbl_main_notification (member_id, notification_type, notification_title, notification_content, experience_program_id)
values (7, 'experience_apply', '[프론트엔드 UI 체험] 지원 현황이 업데이트됐어요', 'document_pass', null);

-- 기술블로그 알림 (스킬로그)
insert into tbl_main_notification (member_id, notification_type, notification_title, notification_content, skill_log_id)
values (7, 'skill_log', '새로운 기술 블로그가 등록됐어요', '회원님이 관심 등록한 기업의 기술블로그가 새로 작성되었습니다.', null);


