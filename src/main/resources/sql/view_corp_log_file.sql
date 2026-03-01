create view view_corp_logo_file as
(
select clf.id,
       clf.corp_id,
       f.file_path,
       f.file_name,
       f.file_original_name,
       f.file_size,
       f.file_content_type,
       f.created_datetime,
       f.updated_datetime
from tbl_corp_logo_file clf
         join tbl_file f
              on clf.id = f.id
    );


select *
from view_corp_logo_file;