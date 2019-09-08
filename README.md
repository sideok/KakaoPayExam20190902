카카오페이 
=============

### 개발 환경
* 개발 Framework
  - Spring Boot
  - Spring Version : 5
* 개발 언어
  - java 8
* 배포환경
  - Maven

### 실행방법
* 하단의 github 경로 상의 "Exam-0.0.1-SNAPSHOT.jar", "start.bat" 파일을 
  다운로드 받아 동일한 경로의 폴더에 위치시킨 뒤 "start.bat" 파일을 실행시키면 tomcat WAS가 실행됩니다.
  - github : <https://github.com/sideok/KakaoPayExam20190902>
  - windows 기준으로 cmd 창에서 자바 1.8 버전 이상을 설치한 뒤 "java -jar Exam-0.0.1-SNAPSHOT.jar" 명령어를 사용하여 실행할 수 도 있다.
* 빌드는 Spring boot의 Maven을 이용하여 jar파일로 빌드를 수행하였습니다.
* 8080포트를 사용하도록 설정되어 있으므로 해당 포트가 미사용 중일 때 실행이 가능합니다.
  
### 테스트 방법
1. jar파일을 실행한 후 웹브라우저의 주소에 <http://localhost:8080> 로 접속합니다 
2. 최초로 Test 용 View가 출력되고 과제물 1~4번에 대한 간략한 설명을 확인합니다.
3. 입력값을 입력 혹은 생략하고 요청 버튼을 각 문제별로 클릭합니다
4. 문제 하단의 iframe 영역에 출력되는 응답 json 데이터를 확인합니다.
5. httpStatus 를 확인하기 위해서는 각 웹브라우저의 개발자도구를 참조하여 주시기 바랍니다.

### 문제해결


개발 프레임웍크, 문제해결 방법, 빌드 및 실행
방법을 기술하세요.
