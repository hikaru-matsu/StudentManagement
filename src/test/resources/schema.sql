Create Table If Not Exists students
(
  id Int Primary Key Auto_Increment,
  name Varchar(255) Not Null,
  kanaName Varchar(255) Not Null,
  nickname Varchar(255) ,
  email Varchar(255) Not Null,
  region Varchar(255) ,
  age Int ,
  gender Varchar(10) ,
  remark Varchar(255) ,
  isdeleted boolean Default False
);

Create Table If Not Exists students_courses
(
  id Int Primary Key Auto_Increment,
  student_id Int Not Null,
  course_name Varchar(255) Not Null,
  start_date Date Not Null,
  end_date Date
)