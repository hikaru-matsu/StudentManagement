Insert Into students(id, name, kanaName, nickname, email, region, age, gender, isdeleted)
Values(1, '佐藤かずま', 'サトウカズマ' , 'かずまさん', 'Kazuma@example.com', '日本', 19, '男性', false),
      (2, 'アクア', 'アクア' , '駄目神', 'Akua@example.com', '天界', 1000, '女性', false),
      (3, 'めぐみん', 'メグミン' , '頭のおかしな子', 'Megumin@example.com', '紅魔郷', 18, '女性', false),
      (4, 'ダクネス', 'ダクネス' , 'ララティーナ', 'Dakunes@exapmle.com', 'アクセル', 20, '女性', false);

Insert Into students_courses (id, student_id, course_name, start_date)
Values(1, 1, '冒険者', '2016-05-10'),
      (2, 1, '引きニート', '2014-12-24'),
      (3, 2, 'アークプリースト', '2016-05-10'),
      (4, 3, 'アークウィザード', '2016-03-31'),
      (5, 4, 'クルセイダー', '2015-04-01');