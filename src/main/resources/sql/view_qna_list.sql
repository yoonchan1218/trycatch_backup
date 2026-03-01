-- 기존 뷰 재생성 시
drop view if exists view_qna_list;

create view view_qna_list as
(
select q.*,
       m.member_name,
       if(m.member_profile_file_id is not null,
           concat('/api/files/display?filePath=', f.file_path, '&fileName=', f.file_name),
           null) as member_profile_image_url,
       (select count(*) from tbl_qna_likes l where l.qna_id = q.id) as qna_like_count,
       (select count(*) from tbl_qna_comment c where c.qna_id = q.id) as qna_comment_count
from tbl_qna q
left join tbl_member m on q.individual_member_id = m.id
left join tbl_file f on m.member_profile_file_id = f.id
);

select * from view_qna_list;
