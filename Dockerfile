# gradle:7.3.3-jdk11 이미지를 기반으로 함
FROM gradle:7.3.3-jdk11 as build

# 작업 디렉토리 설정
WORKDIR /home/gradle/project

# spring 소스 코드 이미지에 복사
COPY . .

# gradlew를 이용한 프로젝트 필드
RUN chmod +x gradlew
RUN ./gradlew clean build -x test

# 빌드 결과를 위한 새로운 단계
FROM openjdk:11-jre-slim

# 작업 디렉토리 설정
WORKDIR /app

# 빌드 결과 복사
COPY --from=build /home/gradle/project/build/libs/*.jar ./app.jar

# 빌드 결과 jar 파일을 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
