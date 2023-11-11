-- 유저
INSERT INTO users (`email`, `nickname`, `title`, `image`, `user_role`, `create_at`) VALUES ('admin', 'admin', '내가 관리자라니!!', '이미지', 'ROLE_ADMIN', '2023-08-29 13:54:19.823');
-- 도전과제
INSERT INTO rewards (`reward_name`, `description`, `reward_level`, `goal_count`) VALUES ('어드민 유저가 뭔데?', '관리자가 되어보세요.', 'challenger', '10000000');
INSERT INTO rewards (`reward_name`, `description`, `reward_level`, `goal_count`) VALUES ('우리들의 첫번째 추억 기록', '첫번째 앨범 페이지를 생성해보세요.', 'bronze', '1');
INSERT INTO rewards (`reward_name`, `description`, `reward_level`, `goal_count`) VALUES ('콜롬버스 등장', '네모에 오신 것을 환영합니다.', 'gold', '1');

-- 진행도
INSERT INTO progresses (`progress_count`, `success`, `user_id`, `reward_id`) VALUES ('10000000', 'Y', '1', '1');
INSERT INTO progresses (`progress_count`, `success`, `user_id`, `reward_id`) VALUES ('0', 'N', '1', '2');
INSERT INTO progresses (`progress_count`, `success`, `user_id`, `reward_id`) VALUES ('0', 'Y', '1', '3');

-- 앨범
INSERT INTO albums (`album_name`, `description`, `image`, `category`) VALUES ('테스트 앨범', '테스트용 앨범', '""', 'Friends');

-- 칭호
INSERT INTO titles (`title_name`, `reward_id`) VALUES ('내가 관리자라니!!', '1');
INSERT INTO titles (`title_name`, `reward_id`) VALUES ('추억 앨범 관리자', '2');
INSERT INTO titles (`title_name`, `reward_id`) VALUES ('방문객', '3');

-- 칭호 획득
INSERT INTO collections (`create_at`, `user_id`, `title_id`) VALUES ('2023-08-29 13:54:19.823', '1', '1');
INSERT INTO collections (`create_at`, `user_id`, `title_id`) VALUES ('2023-08-29 13:54:19.823', '1', '3');
