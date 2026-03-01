create view view_experience_program_file as
(
select epf.id,
       epf.experience_program_id,
        f.file_path,
        f.file_name,
        f.file_original_name,
        f.file_size,
        f.file_content_type,
        f.created_datetime,
        f.updated_datetime
 from tbl_experience_program_file epf
          join tbl_file f
               on epf.id = f.id
 );

select * from view_experience_program_file;