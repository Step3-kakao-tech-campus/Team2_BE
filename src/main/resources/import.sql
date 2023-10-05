SET REFERENTIAL_INTEGRITY FALSE;
truncate table users;
truncate table reward;
truncate table progress;
truncate table title;
truncate table collection;
SET REFERENTIAL_INTEGRITY TRUE;

-- 유저
INSERT INTO users (`id`, `email`, `nickname`, `title`, `image`, `role`, `create_at`) VALUES ('1', 'admin', 'admin', '내가 관리자라니!!', '이미지', 'ROLE_ADMIN', '2023-08-29 13:54:19.823');

-- 도전과제
INSERT INTO reward (`id`, `reward_name`, `description`, `level`, `goal_count`) VALUES ('1', '우리들의 첫번째 추억 기록', '첫번째 앨범 페이지를 생성해보세요.', 'bronze');
INSERT INTO reward (`id`, `reward_name`, `description`, `level`, `goal_count`) VALUES ('2', '콜롬버스 등장', '네모에 오신 것을 환영합니다.', 'gold');

