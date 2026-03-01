-- 기존 뷰 재생성 시
drop view if exists view_participant;

create view view_participant as
(
select a.id,
       a.id as apply_id,
       COALESCE(c.challenger_status, 'in_progress') as challenger_status,
       a.created_datetime,
       COALESCE(c.updated_datetime, a.updated_datetime) as updated_datetime,
       a.experience_program_id,
       a.member_id,
       m.member_name,
       m.member_email,
       m.member_phone,
       m.member_profile_file_id,
       im.individual_member_birth,
       im.individual_member_gender
from tbl_apply a
LEFT JOIN tbl_challenger c ON a.id = c.apply_id
JOIN tbl_member m ON a.member_id = m.id
JOIN tbl_individual_member im ON m.id = im.id
WHERE a.apply_status NOT IN ('document_fail', 'cancelled')
);

select * from view_participant;

create or replace view view_applicant as
(
select a.id, a.experience_program_id, a.member_id, a.apply_status,
       a.created_datetime, a.updated_datetime,
       m.member_name, m.member_email, m.member_phone,
       im.individual_member_birth, im.individual_member_gender, im.individual_member_education
from tbl_apply a
         join tbl_member m on a.member_id = m.id
         join tbl_individual_member im on m.id = im.id
    );

