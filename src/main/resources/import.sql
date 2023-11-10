-- 유저
INSERT INTO users (`id`, `email`, `nickname`, `title`, `image`, `user_role`, `create_at`) VALUES ('1', 'admin', 'admin', '내가 관리자라니!!', '이미지', 'ROLE_ADMIN', '2023-08-29 13:54:19.823');

-- 도전과제
INSERT INTO rewards (`id`, `reward_name`, `description`, `reward_level`, `goal_count`) VALUES ('1', '어드민 유저가 뭔데?', '관리자가 되어보세요.', 'challenger', '10000000');
INSERT INTO rewards (`id`, `reward_name`, `description`, `reward_level`, `goal_count`) VALUES ('2', '우리들의 첫번째 추억 기록', '첫번째 앨범 페이지를 생성해보세요.', 'bronze', '1');
INSERT INTO rewards (`id`, `reward_name`, `description`, `reward_level`, `goal_count`) VALUES ('3', '콜롬버스 등장', '네모에 오신 것을 환영합니다.', 'gold', '1');
INSERT INTO rewards (`id`, `reward_name`, `description`, `reward_level`, `goal_count`) VALUES ('4', '혼술? 아니 혼컷!', '혼자만의 네컷을 찍어보세요', 'gold', '1');
INSERT INTO rewards (`id`, `reward_name`, `description`, `reward_level`, `goal_count`) VALUES ('5', '혹시 당신은 네모 중독자인가요?', '네모로 친구들과 함께 추억 저장하기의 첫 시작!', 'gold', '1');
INSERT INTO rewards (`id`, `reward_name`, `description`, `reward_level`, `goal_count`) VALUES ('6', '다 모임', '그룹 내 그룹원이 2명 이상이에요.', 'gold', '2');
INSERT INTO rewards (`id`, `reward_name`, `description`, `reward_level`, `goal_count`) VALUES ('7', '페이지 꾸미기', '단일 페이지에 텍스트 및 스티커를 붙여보세요.', 'gold', '1');
INSERT INTO rewards (`id`, `reward_name`, `description`, `reward_level`, `goal_count`) VALUES ('8', '추억은 지워도 추억입니다.', '타 그룹원의 다꾸를 삭제해보세요.', 'gold', '1');
INSERT INTO rewards (`id`, `reward_name`, `description`, `reward_level`, `goal_count`) VALUES ('9', '당신은 마당발?!', '소속 앨범이 5개 이상이에요.', 'gold', '5');
INSERT INTO rewards (`id`, `reward_name`, `description`, `reward_level`, `goal_count`) VALUES ('10', '다꾸 밖에도 좀 나가고 그래봐..', '앨범 꾸미기 페이지에 머문 누적 시간 100시간 달성', 'gold', '100');

-- 진행도
INSERT INTO progresses (`id`, `progress_count`, `success`, `user_id`, `reward_id`) VALUES ('1', '10000000', 'Y', '1', '1');
INSERT INTO progresses (`id`, `progress_count`, `success`, `user_id`, `reward_id`) VALUES ('2', '0', 'N', '1', '2');
INSERT INTO progresses (`id`, `progress_count`, `success`, `user_id`, `reward_id`) VALUES ('3', '0', 'Y', '1', '3');

-- 앨범
INSERT INTO albums (`id` , `album_name`, `description`, `image`, `category`) VALUES ('1', '테스트 앨범', '테스트용 앨범', '""', 'Friends');

-- 칭호
INSERT INTO titles (`id` , `title_name`, `reward_id`) VALUES ('1', '내가 관리자라니!!', '1');
INSERT INTO titles (`id` , `title_name`, `reward_id`) VALUES ('2', '추억 앨범 관리자', '2');
INSERT INTO titles (`id` , `title_name`, `reward_id`) VALUES ('3', '방문객', '3');
INSERT INTO titles (`id` , `title_name`, `reward_id`) VALUES ('4', '네컷인싸', '4');
INSERT INTO titles (`id` , `title_name`, `reward_id`) VALUES ('5', '네모 홍보팀장', '5');
INSERT INTO titles (`id` , `title_name`, `reward_id`) VALUES ('6', '다 모임', '6');
INSERT INTO titles (`id` , `title_name`, `reward_id`) VALUES ('7', '다꾸왕', '7');
INSERT INTO titles (`id` , `title_name`, `reward_id`) VALUES ('8', '우정 파괴범', '8');
INSERT INTO titles (`id` , `title_name`, `reward_id`) VALUES ('9', 'M슈퍼인싸Z', '9');
INSERT INTO titles (`id` , `title_name`, `reward_id`) VALUES ('10', '방구석 다꾸 전문가', '10');

-- 칭호 획득
INSERT INTO collections (`id`, `create_at`, `user_id`, `title_id`) VALUES ('1', '2023-08-29 13:54:19.823', '1', '1');
INSERT INTO collections (`id`, `create_at`, `user_id`, `title_id`) VALUES ('2', '2023-08-29 13:54:19.823', '1', '3');