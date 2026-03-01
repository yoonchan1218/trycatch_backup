-- 기존 뷰 재생성 시
drop view if exists view_member_profile;


create view view_member_profile as
select
    m.id,
    m.member_id,
    m.member_name,
    m.member_email,
    m.member_phone,
    m.address_id,
    m.member_status,
    m.member_agree_privacy,
    m.member_agree_marketing,
    m.created_datetime,
    m.updated_datetime,
    i.individual_member_birth,
    i.individual_member_gender,
    i.individual_member_education,
    i.individual_member_point,
    i.individual_member_level,
    i.individual_member_post_count,
    i.individual_member_question_count,
    if(m.member_profile_file_id is not null,
        concat('/api/files/display?filePath=', f.file_path, '&fileName=', f.file_name),
        null) as member_profile_image_url
from tbl_member m
left join tbl_individual_member i on m.id = i.id
left join tbl_file f on m.member_profile_file_id = f.id;

select * from view_member_profile;
