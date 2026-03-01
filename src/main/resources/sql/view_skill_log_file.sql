create view view_skill_log_file as
(
select skf.id,
        skf.skill_log_id,
        f.file_path,
        f.file_name,
        f.file_original_name,
        f.file_size,
        f.file_content_type,
        f.created_datetime,
        f.updated_datetime
 from tbl_skill_log_file skf
          join tbl_file f
               on skf.id = f.id
 );

select * from view_skill_log_file;