Create Table students (
  id Integer Primary Key Auto_Increment,
  name Varchar(100) Not Null,
  kana_name Varchar(100) Not Null,
  nickname Varchar(100),
  email Varchar(50) Not Null,
  area Varchar(50),
  age Integer,
  gender Varchar(50),
  remark Varchar(50),
  is_deleted Boolean
);

Create Table students_courses (
  id Integer Primary Key  Auto_Increment,
  student_id Integer Not Null,
  course_name Varchar(50) Not Null,
  start_date TIMESTAMP,
  Foreign Key(student_id) REFERENCES students(id)
);