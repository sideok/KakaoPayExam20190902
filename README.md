카카오페이 과제전형
=============

>목차
------
[1.개발을 하고 싶어요](###-개발-환경)
[2.코딩을 잘하고 싶어요](#coding을-잘하고-싶어요)

내용
------
### 개발 환경
* 개발 Framework
  - Spring Boot
  - Spring Version : 5
* 개발 언어
  - java 8
* 배포환경
  - Maven

### 실행방법
* 실행환경 구성
  - java 8 이상 설치
  - 8080 port 미사용 상태
* 하단의 github 경로 상의 "Exam-0.0.1-SNAPSHOT.jar", "start.bat" 파일을 다운로드 받아 동일한 경로의 폴더에 위치시킨 뒤 "start.bat" 파일을 실행시키면 tomcat WAS가 실행됩니다.
  - github : <https://github.com/sideok/KakaoPayExam20190902>
  - 다른 방법으로는 shell에서 "java -jar Exam-0.0.1-SNAPSHOT.jar" 명령어를 사용하여 실행할 수 도 있습니다.(windows os 기준)
* 빌드는 Spring boot의 Maven을 이용하여 jar파일로 빌드를 수행하였습니다.

### 테스트 방법
1. jar파일을 실행한 후 웹브라우저의 주소에 <http://localhost:8080> 로 접속합니다 
2. 최초로 Test 용 View가 출력되고 과제물 1~4번에 대한 간략한 설명을 확인합니다.
3. 입력값을 입력 혹은 생략하고 요청 버튼을 각 문제별로 클릭합니다
4. 문제 하단의 iframe 영역에 출력되는 응답 json 데이터를 확인합니다.
5. httpStatus 를 확인하기 위해서는 각 웹브라우저의 개발자도구를 참조하여 주시기 바랍니다.

### 문제해결
* csv파일의 경로문제
  - 개발시점에서의 csv파일의 상대경로 참조와 jar파일로 배포를 한 후의 경로 참조가 다르게 인식 되는 문제가 있었습니다.
  - 이 문제를 해결하기 위해 현재 작동하고 있는 class의 경로를 참조하는 getResourceAsStream() 메서드를 사용하여 csv파일의 경로가 배포 후에도 참조될 수 있도록 수정하였습니다.
* SQL없이 서로 다른 data table 들을 참조하여 필요한 데이터를 찾아야 하는 문제
  - java 8 부터 추가된 Stream 의 filter 및 goruping 등의 기능을 통해 필요한 정보추출과 요약을 수행하였습니다.
