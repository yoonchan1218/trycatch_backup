-- MyPage reference queries

-- profile
select
    m.id,
    m.member_id,
    m.member_name,
    m.member_email,
    m.member_phone,
    m.address_id,
    m.member_status,
    m.created_datetime,
    m.updated_datetime,
    i.individual_member_birth,
    i.individual_member_gender,
    i.individual_member_education,
    i.individual_member_point,
    i.individual_member_level,
    i.individual_member_post_count,
    i.individual_member_question_count
from tbl_member m
left join tbl_individual_member i
on m.id = i.id
where m.id = ?;

-- notifications
select
    id, member_id, notification_type, notification_title, notification_content,
    notification_is_read, notification_receive_at, created_datetime, updated_datetime
from tbl_main_notification
where member_id = ?
order by notification_receive_at desc;

-- deactivate member
update tbl_member
set member_status = 'inactive', updated_datetime = current_timestamp
where id = ?;
