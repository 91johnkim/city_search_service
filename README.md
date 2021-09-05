

### < City Search Service > 

### 개발 스펙
* Java 16
* SpringBoot 2.5.4
* Jpa
* H2 (in-memory)
* Gradle

### 서비스 설명

1) 도시 조회 / 등록 ( localhost:8080/api/v1/cities )
   1) 단건 조회 (/도시명) GET 
   2) 리스트 조회 (/list) GET 
   3) 등록 POST
   
2) 여행 조회 / 등록 ( localhost:8080/api/v1/trip)
   1) 조회 /여행번호 GET
   2) 등록 POST


### 빌드 및 실행

해당 서비스를 빌드, 실행하기 위해서 아래 내역을 우선 확인하시기 바랍니다.

1) JDK 설치 확인 : java --version 
2) Gradle 설치 확인 : gradle --version
3) Git 설치확인 : git --version

   

빌드 및 실행방법

1. git clone 명령으로 해당 프로젝트 파일을 받아옵니다.
   1. git clone https://github.com/91johnkim/city_search_service.git

2. Gradle로 해당 프로젝트를 빌드 합니다. project의 bulid 디렉터리에 산출물을 확인합니다.
   1. cd city_search_service
   2. ./gradlew clean build
   3. city_search_service/build/libs/ 

3. Java 명령으로 빌드된 파일을 실행시킵니다.
   1. java -jar 산출물.jar 
   


