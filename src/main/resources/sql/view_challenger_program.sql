-- 기존 뷰 재생성 시
drop view if exists view_challenger_program;

create view view_challenger_program as
(
select
    a.experience_program_id as id,
    a.member_id,
    ep.experience_program_title, ep.experience_program_description, ep.experience_program_level,
    ep.experience_program_recruitment_count, ep.experience_program_work_days, ep.experience_program_work_hours,
    ep.experience_program_deadline, ep.experience_program_status,
    ep.experience_program_view_count, ep.experience_program_job, ep.created_datetime, ep.updated_datetime,
    ep.corp_id, co.corp_company_name, co.corp_company_type,
    co.corp_business_number, co.corp_ceo_name, co.corp_kind_id, co.corp_kind_small_id,
    co.corp_company_size, co.corp_establishment_date, co.corp_website_url, co.corp_fax, co.corp_capital,
    co.corp_total_sales, co.corp_performance, co.corp_vision
from tbl_challenger c
join tbl_apply a on c.apply_id = a.id
join tbl_member m on a.member_id = m.id
join tbl_individual_member im on m.id = im.id
join tbl_experience_program ep on a.experience_program_id = ep.id
join tbl_corp co on ep.corp_id = co.id
);

select * from view_challenger_program;
