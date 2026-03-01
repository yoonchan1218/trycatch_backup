-- main/list/detail test seed for experience programs
-- idempotent upsert script

START TRANSACTION;

-- 1) members (corp + individual)
INSERT INTO tbl_member (
    id, member_id, member_password, member_name, member_email, member_phone,
    member_agree_privacy, member_agree_marketing, created_datetime, updated_datetime
) VALUES
    (2001, 'user_demo', '1234', 'Demo User', 'user_demo@trycatch.local', '010-1111-1111', 1, 1, '2026-02-01 09:00:00', '2026-02-01 09:00:00'),
    (3001, 'corp_alpha', '1234', 'Alpha HR', 'corp_alpha@trycatch.local', '02-1111-1111', 1, 1, '2026-01-10 09:00:00', '2026-01-10 09:00:00'),
    (3002, 'corp_beta',  '1234', 'Beta HR', 'corp_beta@trycatch.local',  '02-2222-2222', 1, 1, '2026-01-11 09:00:00', '2026-01-11 09:00:00'),
    (3003, 'corp_gamma', '1234', 'Gamma HR', 'corp_gamma@trycatch.local', '02-3333-3333', 1, 1, '2026-01-12 09:00:00', '2026-01-12 09:00:00'),
    (3004, 'corp_delta', '1234', 'Delta HR', 'corp_delta@trycatch.local', '02-4444-4444', 1, 1, '2026-01-13 09:00:00', '2026-01-13 09:00:00')
ON DUPLICATE KEY UPDATE
    member_password = VALUES(member_password),
    member_name = VALUES(member_name),
    member_email = VALUES(member_email),
    member_phone = VALUES(member_phone),
    member_agree_privacy = VALUES(member_agree_privacy),
    member_agree_marketing = VALUES(member_agree_marketing),
    updated_datetime = VALUES(updated_datetime);

INSERT INTO tbl_individual_member (
    id, individual_member_birth, individual_member_gender, individual_member_education,
    individual_member_point, individual_member_level, individual_member_post_count, individual_member_question_count
) VALUES
    (2001, '2000-05-15', 'man', 'BACHELOR', 1200, 3, 4, 2)
ON DUPLICATE KEY UPDATE
    individual_member_birth = VALUES(individual_member_birth),
    individual_member_gender = VALUES(individual_member_gender),
    individual_member_education = VALUES(individual_member_education),
    individual_member_point = VALUES(individual_member_point),
    individual_member_level = VALUES(individual_member_level),
    individual_member_post_count = VALUES(individual_member_post_count),
    individual_member_question_count = VALUES(individual_member_question_count);

-- 2) corporations
INSERT INTO tbl_corp (
    id, corp_company_name, corp_business_number, corp_ceo_name,
    corp_company_type, corp_company_size, corp_establishment_date,
    corp_website_url, corp_fax, corp_capital, corp_total_sales, corp_main_business,
    corp_performance, corp_vision
) VALUES
    (3001, 'Alpha Tech', '110-81-10001', 'Kim Alpha', 'public', 'large', '2001-03-10', 'https://alpha.example.com', '02-1111-0000', 3000000000, '1200B KRW', 'AI Solutions', 'Expanded B2B platform', 'Technology company focused on practical growth'),
    (3002, 'Beta Mobility', '110-81-10002', 'Lee Beta', 'mid-size', 'mid-size', '2005-06-20', 'https://beta.example.com', '02-2222-0000', 1800000000, '980B KRW', 'Mobility Service', 'Expanded service area', 'Digital transformation for field operations'),
    (3003, 'Gamma Cloud', '110-81-10003', 'Park Gamma', 'public', 'small', '2012-09-03', 'https://gamma.example.com', '02-3333-0000', 900000000, '430B KRW', 'Cloud Infra', 'Secured public references', 'Reliable cloud operations for teams'),
    (3004, 'Delta Fintech', '110-81-10004', 'Choi Delta', 'startup', 'small', '2018-11-01', 'https://delta.example.com', '02-4444-0000', 300000000, '120B KRW', 'Fintech Service', 'Expanded payment partnerships', 'Build accessible financial products')
ON DUPLICATE KEY UPDATE
    corp_company_name = VALUES(corp_company_name),
    corp_business_number = VALUES(corp_business_number),
    corp_ceo_name = VALUES(corp_ceo_name),
    corp_company_type = VALUES(corp_company_type),
    corp_company_size = VALUES(corp_company_size),
    corp_establishment_date = VALUES(corp_establishment_date),
    corp_website_url = VALUES(corp_website_url),
    corp_fax = VALUES(corp_fax),
    corp_capital = VALUES(corp_capital),
    corp_total_sales = VALUES(corp_total_sales),
    corp_main_business = VALUES(corp_main_business),
    corp_performance = VALUES(corp_performance),
    corp_vision = VALUES(corp_vision);

-- 3) experience programs (12 rows for main/list/detail and paging)
INSERT INTO tbl_experience_program (
    id, corp_id, experience_program_title, experience_program_description,
    experience_program_level, experience_program_recruitment_count,
    experience_program_work_days, experience_program_work_hours,
    experience_program_deadline, experience_program_status,
    experience_program_view_count, experience_program_job,
    created_datetime, updated_datetime
) VALUES
    (5001, 3001, 'Alpha Backend API Internship', '<p>Hands-on API implementation and code review with Spring stack.</p>', 'b', 6, '2026-03-10 ~ 2026-04-10', '09:00~18:00', '2026-03-20', 'recruiting', 980,  'Backend Development', '2026-02-18 10:00:00', '2026-02-28 10:00:00'),
    (5002, 3002, 'Beta Data Analyst Internship', '<p>Analyze operation logs and build practical dashboard reports.</p>', 'c', 4, '2026-03-12 ~ 2026-04-11', '10:00~19:00', '2026-03-18', 'recruiting', 1200, 'Data Analysis',   '2026-02-17 09:00:00', '2026-02-26 09:00:00'),
    (5003, 3003, 'Gamma DevOps Internship', '<p>Improve CI/CD pipeline and monitor production services.</p>', 'd', 5, '2026-03-15 ~ 2026-04-15', '09:30~18:30', '2026-03-22', 'recruiting', 650,  'DevOps',      '2026-02-19 14:00:00', '2026-02-27 15:00:00'),
    (5004, 3004, 'Delta Frontend Internship', '<p>Improve payment UI and verify accessibility standards.</p>', 'a', 3, '2026-03-18 ~ 2026-04-18', '09:00~18:00', '2026-03-25', 'recruiting', 300,  'Frontend Development', '2026-02-20 08:30:00', '2026-03-01 08:30:00'),
    (5005, 3001, 'Alpha QA Automation Internship', '<p>Create test cases and operate regression automation scripts.</p>', 'c', 2, '2026-03-14 ~ 2026-04-05', '09:00~17:00', '2026-03-19', 'recruiting', 430,  'QA',          '2026-02-16 11:00:00', '2026-02-25 13:00:00'),
    (5006, 3002, 'Beta Product Planning Internship', '<p>Write product docs and run user interview sessions.</p>', 'b', 3, '2026-03-09 ~ 2026-04-02', '10:00~18:00', '2026-03-17', 'recruiting', 880,  'Product Planning',   '2026-02-15 09:00:00', '2026-02-24 17:30:00'),
    (5007, 3003, 'Gamma Security Ops Internship', '<p>Learn practical security operation with checklist-driven process.</p>', 'd', 2, '2026-03-20 ~ 2026-04-20', '09:00~18:00', '2026-03-27', 'recruiting', 520,  'Security',         '2026-02-14 13:00:00', '2026-02-23 10:00:00'),
    (5008, 3004, 'Delta Growth Marketing Internship', '<p>Operate campaign execution and write weekly performance reports.</p>', 'a', 2, '2026-03-22 ~ 2026-04-12', '10:00~19:00', '2026-03-29', 'recruiting', 210,  'Marketing',       '2026-02-13 10:30:00', '2026-02-22 11:00:00'),
    (5009, 3001, 'Alpha Data Engineering Internship (Closed)', '<p>Build ETL flow and review batch processing design.</p>', 'e', 2, '2026-02-01 ~ 2026-02-28', '09:00~18:00', '2026-02-05', 'closed',     1500, 'Data Engineering', '2026-01-20 10:00:00', '2026-02-20 10:00:00'),
    (5010, 3002, 'Beta Product Ops Internship (In Progress)', '<p>Track operation metrics and manage incident communication.</p>', 'b', 3, '2026-03-01 ~ 2026-03-31', '09:00~18:00', '2026-02-20', 'draft',      410,  'Service Operations',   '2026-02-10 09:00:00', '2026-03-01 09:10:00'),
    (5011, 3003, 'Gamma Technical Support Internship (Finished)', '<p>Handle customer technical inquiries and organize docs.</p>', 'a', 2, '2026-01-10 ~ 2026-02-10', '09:00~18:00', '2026-01-15', 'cancelled',  50,   'Technical Support',      '2026-01-05 09:00:00', '2026-02-19 16:00:00'),
    (5012, 3004, 'Delta Backoffice Development Internship', '<p>Implement settlement backoffice feature and run test scenario.</p>', 'c', 4, '2026-03-11 ~ 2026-04-09', '09:30~18:30', '2026-03-21', 'recruiting', 770,  'Backend Development',   '2026-02-12 14:00:00', '2026-02-21 14:00:00')
ON DUPLICATE KEY UPDATE
    corp_id = VALUES(corp_id),
    experience_program_title = VALUES(experience_program_title),
    experience_program_description = VALUES(experience_program_description),
    experience_program_level = VALUES(experience_program_level),
    experience_program_recruitment_count = VALUES(experience_program_recruitment_count),
    experience_program_work_days = VALUES(experience_program_work_days),
    experience_program_work_hours = VALUES(experience_program_work_hours),
    experience_program_deadline = VALUES(experience_program_deadline),
    experience_program_status = VALUES(experience_program_status),
    experience_program_view_count = VALUES(experience_program_view_count),
    experience_program_job = VALUES(experience_program_job),
    created_datetime = VALUES(created_datetime),
    updated_datetime = VALUES(updated_datetime);

-- 4) thumbnail files for experience programs
INSERT INTO tbl_file (
    id, file_path, file_name, file_original_name, file_size, file_content_type,
    created_datetime, updated_datetime
) VALUES
    (9101, 'seed/program', 'program-5001.png', 'program-5001.png', '68', 'image', '2026-03-01 10:00:00', '2026-03-01 10:00:00'),
    (9102, 'seed/program', 'program-5002.png', 'program-5002.png', '68', 'image', '2026-03-01 10:00:00', '2026-03-01 10:00:00'),
    (9103, 'seed/program', 'program-5003.png', 'program-5003.png', '68', 'image', '2026-03-01 10:00:00', '2026-03-01 10:00:00'),
    (9104, 'seed/program', 'program-5004.png', 'program-5004.png', '68', 'image', '2026-03-01 10:00:00', '2026-03-01 10:00:00'),
    (9105, 'seed/program', 'program-5005.png', 'program-5005.png', '68', 'image', '2026-03-01 10:00:00', '2026-03-01 10:00:00'),
    (9106, 'seed/program', 'program-5006.png', 'program-5006.png', '68', 'image', '2026-03-01 10:00:00', '2026-03-01 10:00:00'),
    (9107, 'seed/program', 'program-5007.png', 'program-5007.png', '68', 'image', '2026-03-01 10:00:00', '2026-03-01 10:00:00'),
    (9108, 'seed/program', 'program-5008.png', 'program-5008.png', '68', 'image', '2026-03-01 10:00:00', '2026-03-01 10:00:00'),
    (9109, 'seed/program', 'program-5009.png', 'program-5009.png', '68', 'image', '2026-03-01 10:00:00', '2026-03-01 10:00:00'),
    (9110, 'seed/program', 'program-5010.png', 'program-5010.png', '68', 'image', '2026-03-01 10:00:00', '2026-03-01 10:00:00'),
    (9111, 'seed/program', 'program-5011.png', 'program-5011.png', '68', 'image', '2026-03-01 10:00:00', '2026-03-01 10:00:00'),
    (9112, 'seed/program', 'program-5012.png', 'program-5012.png', '68', 'image', '2026-03-01 10:00:00', '2026-03-01 10:00:00')
ON DUPLICATE KEY UPDATE
    file_path = VALUES(file_path),
    file_name = VALUES(file_name),
    file_original_name = VALUES(file_original_name),
    file_size = VALUES(file_size),
    file_content_type = VALUES(file_content_type),
    updated_datetime = VALUES(updated_datetime);

INSERT INTO tbl_experience_program_file (id, experience_program_id) VALUES
    (9101, 5001),
    (9102, 5002),
    (9103, 5003),
    (9104, 5004),
    (9105, 5005),
    (9106, 5006),
    (9107, 5007),
    (9108, 5008),
    (9109, 5009),
    (9110, 5010),
    (9111, 5011),
    (9112, 5012)
ON DUPLICATE KEY UPDATE
    experience_program_id = VALUES(experience_program_id);

COMMIT;
