Insert Into students(name, kana_name, nickname, email, area, age, gender)
Values('テスト太郎', 'テストタロウ', 'テッくん', 'Test@example.com', '不思議の国', 99, 'ガチ両刀'),
('MikeTython', 'マイクタイソン', 'アイアンマン', 'Tython@example.com', 'アメリカ', 60, '男性'),
('JonJohns', 'ジョンジョーンズ', 'ボーンズ', 'JJ@example.com', 'アメリカ', 35, '男性');

Insert Into students_courses(student_id, course_name)
Values(1, 'テストコース'),
(2, 'ボクシングコース'),
(2, '大麻コース'),
(3, 'MMAコース'),
(3, 'ドーピングコース');