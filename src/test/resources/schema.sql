Create Table If Not Exists students
(
  id Integer Not Null,
  name Varchar(255) Not Null,
  kanaName Varchar(255) Not Null,
  nickname Varchar(255) ,
  email Varchar(255) Not Null,
  region Varchar(255) ,
  age Integer ,
  gender Varchar(10) ,
  remark Varchar(255) ,
  isdeleted boolean
);

Create Table If Not Exists students_courses
(
  id Integer Not Null,
  student_id Integer Not Null,
  course_name Varchar(255) Not Null,
  start_date Date Not Null,
  end_date Date
)