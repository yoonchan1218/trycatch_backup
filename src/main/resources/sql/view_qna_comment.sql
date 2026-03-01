-- 1. 컬럼 변경 (individual_member_id → member_id)
ALTER TABLE tbl_qna_comment DROP FOREIGN KEY fk_qna_comment_member;
ALTER TABLE tbl_qna_comment CHANGE individual_member_id member_id BIGINT;
ALTER TABLE tbl_qna_comment MODIFY member_id BIGINT UNSIGNED;
ALTER TABLE tbl_qna_comment ADD CONSTRAINT fk_qna_comment_member FOREIGN KEY (member_id) REFERENCES tbl_member(id);
ALTER TABLE tbl_qna_likes DROP FOREIGN KEY fk_qna_likes_member;
ALTER TABLE tbl_qna_likes MODIFY member_id BIGINT UNSIGNED;
ALTER TABLE tbl_qna_likes ADD CONSTRAINT fk_qna_likes_member FOREIGN KEY (member_id) REFERENCES tbl_member(id);
-- 2. 뷰 재생성
drop view if exists view_qna_comment;

create view view_qna_comment as
(
select c.id, c.qna_id, c.member_id, m.member_name,
       c.qna_comment_parent, c.qna_comment_content,
       lf.file_path as logo_file_path, lf.file_name as logo_file_name,
       f.file_path as comment_file_path, f.file_name as comment_file_name,
       c.created_datetime, c.updated_datetime
from tbl_qna_comment c
left join tbl_member m on c.member_id = m.id
left join tbl_corp corp on c.member_id = corp.id
left join view_corp_logo_file lf on corp.id = lf.corp_id
left join tbl_qna_comment_file cf on c.id = cf.qna_comment_id
left join tbl_file f on cf.id = f.id
);

CREATE INDEX idx_qna_comment_qna_id ON tbl_qna_comment (qna_id);

ALTER TABLE tbl_qna_comment DROP INDEX uk_qna_member_comment;

select * from view_qna_comment;
