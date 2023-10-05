SET REFERENTIAL_INTEGRITY FALSE;
truncate table users;
truncate table reward;
truncate table progress;
truncate table title;
truncate table collection;
SET REFERENTIAL_INTEGRITY TRUE;

-- 유저
INSERT INTO users (`id`, `email`, `nickname`, `title`, `image`, `role`, `create_at`) VALUES ('1', 'admin', 'admin', '내가 관리자라니!!', '이미지', 'ROLE_ADMIN', '2023-08-29 13:54:19.823');

