# 우리들의 네컷 모음집 "네모" - 2조

<img width="403" alt="image" src="https://github.com/Step3-kakao-tech-campus/Team2_BE/assets/98508955/0f52fc19-e558-430c-be8c-236c1050e833">
<br>

## 🖇 ️팀 내 배포 링크 모음

프론트 배포 인스턴스 주소 :

백 배포 인스턴스 주소 : [백 배포 인스턴스 주소](https://k255e0ec5dd13a.user-app.krampoline.com)

<br>

### 개발 기간
2023.09-11 (카카오 테크 캠퍼스 1기 - 3단계 진행 기간)

<br>

### 사용 기술 및 협업 스텍
jdk 11
springboot 2.7.15
mysql 8.0.28
redis 7.2
spring security
open feign
aws 2.26

<br>

## 🗂️ 최종 ERD

<<<< 최종 ERD 사진 >>>>

# 🔅 네모 프로젝트 설명

> 프로젝트 네모는 함께 찍은 네컷 사진들을 그룹 다이어리에 보관하고, 동시에 편집이 가능한 **공유형 다이어리 꾸미기 서비스**입니다.
> (\* 물론 다른 사진들도 가능합니다 😊)

<br>
<br>

## 프로젝트 제안 배경

요즘은 소유 경제가 아닌 경험 경제라고 합니다. 그만큼 어떤 경험을 했는지가 중요하다고 합니다.
**소중한 경험을 다같이 공유하고 꾸밀 수 있다면 얼마나 좋을까?** 에서 저희의 프로젝트는 시작되었습니다.

MZ세대의 "꾸미기 문화"는 주류 트렌드로 확실하게 자리잡고 있습니다. MZ 트렌드를 다루는 미디어 캐릿에서 "별다꾸" 라는 신조어가 생길 정도이니 말입니다.
MZ세대들의 소통창구인 인스타그램 해시태그 게시물 수에 따르면 다꾸 (413만개), 인생네컷 (125만개) 로
20-30 세대들이 얼마나 이 두 키워드에 열광하는 지 알 수 있습니다. 저희는 이 두개의 키워드를 조합해 함께 찍은 사진들을 그룹 다이어리에 보관하고, 동시에 편집이 가능한 공유형 다이어리 꾸미기 서비스 "네모"를 제안합니다.

<br>

## 5Whys?

<img width="585" alt="image" src="https://github.com/Step3-kakao-tech-campus/Team2_BE/assets/98508955/7ff5e39f-001c-455f-aa81-3f8ce1bdfb23">

<img width="582" alt="image" src="https://github.com/Step3-kakao-tech-campus/Team2_BE/assets/98508955/fc7785e0-90a7-4089-96f4-5795d571b2d2">

<br>

## 서비스 소개
> 실시간 공유형 다이어리 꾸미기 서비스 **네모**


1. 친구들과 나만이 가지고 있는 앨범! **나만의 포토 앨범 만들기**
   - "친구, 연인, 혼자, 가족" 등 여러 태그로 내 소중한 사람들과 함께 앨범을 만들어요!
   - 
     
2. 친구와 동시에 편집이 가능한 **동시 편집 기능**
   - 각자의 집에서 서로 뭘 꾸미는지 볼 수 있어요!
   - 아 쓸말이 없다..
     
3. QR로 받아온 네컷 사진을 바로 네모 앨범으로?
   - 네모 앨범에서 QR 인식을 하고 네컷 사진을 바로 앨범으로 불러와요!
    
4. 이제 앨범 꾸미기도 도전이다! 그룹끼리 그리고 나혼자만의 **도전과제**
   - 매일 찍는 사진들을 좀 더 재미있게 즐길 방법이 없을까? 바로 도전과제를 다같이 수행해봐요!
   - 사진찍고 꾸미는 즐거움도 느끼고 골드 단계를 수행하면 나만의 칭호도 획독 할 수 있어요!

5. 다른 그룹원이 삭제한 페이지를 다시 보고싶을땐 **휴지통**에서 다시 찾아오기!
   - 다른 그룹원이 만약 페이지를 삭제했다? 그럼 최대 7일간 보관되는 휴지통에서 다시 복구 시키면 돼요!
  
<br>


## 🎯 중점을 둔 기능

### FE

### BE

#### 1. 소셜 로그인

- 카카오 소셜로그인, 구글 소셜 로그인 이용
- [깃허브 URL](https://github.com/Step3-kakao-tech-campus/Team2_BE/tree/weekly/src/main/java/com/example/team2_be/auth)

#### 2. 실시간 웹 소켓 통신

- [깃허브 URL](https://github.com/Step3-kakao-tech-campus/Team2_BE/blob/weekly/src/main/java/com/example/team2_be/album/page/AlbumPageSocketHandler.java)

#### 3. 앨범 페이지 기능
- [깃허브 URL](https://github.com/Step3-kakao-tech-campus/Team2_BE/tree/weekly/src/main/java/com/example/team2_be/album/page)
