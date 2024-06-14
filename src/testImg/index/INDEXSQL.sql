insert into INDIVIDUAL_INQUIRIES
(INQUIRY_CATEGORY_NO,INQUIRY_USER_NO,INDIVIDUAL_INQUIRY_TITLE,INDIVIDUAL_INQUIRY_CONTENT)
values
    (2 , 2 , '더미데이터 제목' ,'더미데이터 내용'),
    (2 , 2 , '더미데이터 제목' ,'더미데이터 내용');


select count(*)
from INDIVIDUAL_INQUIRIES
where INQUIRY_USER_NO = 2;



select * from INDIVIDUAL_INQUIRIES
where  INQUIRY_USER_NO = 2
  and INDIVIDUAL_INQUIRY_CREATE_DATE BETWEEN '2024-05-01' and '2024-05-02';

-- 컬럼에 카디널리티 수치 조회
select
    concat(ROUND(COUNT(DISTINCT INDIVIDUAL_INQUIRY_NO) / count(*) * 100 , 2),'%') AS inquiryNO_carinality,
    concat(ROUND(COUNT(DISTINCT INQUIRY_CATEGORY_NO) / count(*) * 100 , 2),'%') AS cateNO_carinality,
    concat(ROUND(COUNT(DISTINCT INQUIRY_USER_NO) / count(*) * 100 , 2),'%') AS  userNO_carinality,
    concat(ROUND(COUNT(DISTINCT INDIVIDUAL_INQUIRY_CONTENT) / count(*) * 100 , 2),'%') AS inquiryContent_carinality,
    concat(ROUND(COUNT(DISTINCT INDIVIDUAL_INQUIRY_CREATE_DATE) / count(*) * 100 , 2),'%') AS created_carinality,
    concat(ROUND(COUNT(DISTINCT INDIVIDUAL_INQUIRY_UPDATE_DATE) / count(*) * 100 , 2),'%') AS upated_carinality
from INDIVIDUAL_INQUIRIES;


-- 실행계획을 통한 적용된 인덱스 확인
explain
select * from INDIVIDUAL_INQUIRIES
where INDIVIDUAL_INQUIRY_CREATE_DATE BETWEEN '2024-03-01 00:00:00' AND '2024-03-01 23:59:59'
  and INQUIRY_USER_NO=2
;

-- 실행계획을 통한 적용된 인덱스 변경
explain
select * from INDIVIDUAL_INQUIRIES use index(FK_INQUIRY_USER_NO)
where INDIVIDUAL_INQUIRY_CREATE_DATE BETWEEN '2024-03-01 00:00:00' AND '2024-03-01 23:59:59'
  and INQUIRY_USER_NO=2
;

-- table에 존재하는 INDEX
show index from INDIVIDUAL_INQUIRIES;

-- INDEX 생성
create INDEX idx_createdDate ON INDIVIDUAL_INQUIRIES (INDIVIDUAL_INQUIRY_CREATE_DATE);

-- INDEX 제거
DROP INDEX idx_createdDate ON INDIVIDUAL_INQUIRIES;

select *
from INDIVIDUAL_INQUIRIES
where INDIVIDUAL_INQUIRY_CREATE_DATE BETWEEN '2024-03-01 00:00:00' AND  '2024-03-01 23:59:59'
  and INQUIRY_USER_NO = 2