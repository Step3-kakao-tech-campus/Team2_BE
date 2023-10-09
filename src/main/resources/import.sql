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
INSERT INTO reward (`id`, `reward_name`, `description`, `level`, `goal_count`) VALUES ('1', '어드민 유저가 뭔데?', '관리자가 되어보세요.', 'challenger', '10000000');
INSERT INTO reward (`id`, `reward_name`, `description`, `level`, `goal_count`) VALUES ('2', '우리들의 첫번째 추억 기록', '첫번째 앨범 페이지를 생성해보세요.', 'bronze', '1');
INSERT INTO reward (`id`, `reward_name`, `description`, `level`, `goal_count`) VALUES ('3', '콜롬버스 등장', '네모에 오신 것을 환영합니다.', 'gold', '1');

-- 진행도
INSERT INTO progress (`id`, `count`, `success`, `user_id`, `reward_id`) VALUES ('1', '10000000', 'Y', '1', '1');
INSERT INTO progress (`id`, `count`, `success`, `user_id`, `reward_id`) VALUES ('2', '0', 'N', '1', '2');
INSERT INTO progress (`id`, `count`, `success`, `user_id`, `reward_id`) VALUES ('3', '0', 'Y', '1', '3');


-- 칭호
INSERT INTO title (`id` , `title_name`, `reward_id`) VALUES ('1', '내가 관리자라니!!', '1');
INSERT INTO title (`id` , `title_name`, `reward_id`) VALUES ('2', '추억 앨범 관리자', '2');
INSERT INTO title (`id` , `title_name`, `reward_id`) VALUES ('3', '방문객', '3');

-- 칭호 획득
INSERT INTO collection (`id`, `create_at`, `user_id`, `title_id`) VALUES ('1', '2023-08-29 13:54:19.823', '1', '1');
INSERT INTO collection (`id`, `create_at`, `user_id`, `title_id`) VALUES ('2', '2023-08-29 13:54:19.823', '1', '3');