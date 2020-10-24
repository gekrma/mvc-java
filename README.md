** 데이터가 되는 json 파일 경로는 enumeration 패키지의 Common 클래스에서 JSON_PATH 에 입력해두었음<br />
** JSON_PATH( "C:\\0.posco\\01.java\\SeoulTouristSights\\data\\SeoulTouristSightsInformation.json" )<br />
** 만약에 01.java 가 아닌 폴더에서 import 했거나, mvc-java 프로젝트 폴더 상위에 또다른 폴더가 있다면 맞춰서 수정해주어야 함

** DDL 전 필수작업<br />
** cmd -> sqlplus / as sysdba -> alter system set processes=300 scope=spfile -> shutdown immediate -> startup<br />
** 한 번에 많은 작업을 하기 위한 프로세스 수 변경(프로세스 수의 default 는 100)<br />
** 이후 데이터베이스에 대하여 shutdown immediate(강제종료) 와 startup(시작) 을 해야만 비로소 프로세스 수 변경이 반영됨<br />
** (추가적인 DML 작업은 없습니다.)

1. 주제
	- 서울시 장애인 관광지 목록에서 기본정보 및 서비스별로 조회
	
2. 구성
	*(sql field, json key (, constraint )) : json 파일 내 key 를 sql table 에서 이러한 field 로 naming

	1. Service 
		- 시설명(fname, sisulname, PK)*, 주출입구 접근로 여부(entrance, st1), 장애인 전용 주차구역(parking, st2), 
		    주출입구 높이차이 제거(remove_height, st3), 장애인용 승강기(elevator, st4), 장애인 화장실(toilet, st5), 
		    장애인용 객실 이용가능(guestroom, st6), 장애인용 관람석 이용가능(seats, st7), 장애인 매표소(ticket_office, st8), 
		    시각장애인 편의서비스(blind_service, st9), 청각장애인 편의서비스(deaf_service, st10), 안내 서비스(info_servㅑce, st11), 
		    휠체어 대여(wheelchair, st12) 
		    
	2. Sight - id(SQL Sequence, PK), 시설명(fname, sisulname, FK), 구역(district, gu), 분류(section, null), 좋아요 수(hits, hit)
	 
	3. SightDetail - id(SQL Sequence, FK), 전화번호(tel, tel), 주소(address, addr), 이미지(image, images), 홈페이지(homepage, homepage)
	
	4. SightCompleteInfo - Service 와 Sight 그리고 SightDetail 까지 모두 멤버변수로 지정한 클래스
	
	5. DeserializeObject, Content - 해당 json 파일을 읽기가능한 객체로 받아오는 클래스
	
3. 패턴
	1. Singleton
	2. MVC
	3. DTO
	4. Enum
	
4. 기능
	1. 모든 관광지 조회
	2. 특정 시설명을 포함하는 관광지 조회
	3. 좋아요 수가 2개 이상인 관광지 좋아요 내림차순으로 조회(=인기순)
	4. 특정 구역에 있는 관광지 조회
	5. 특정 서비스가 가능한 관광지 조회
	6. 특정 분류에 해당하는 관광지 조회
	7. 이상의 조건들을 다중으로 선택하여 관광지 조회
	8. id 로 하나의 관광지 정보를 구성하는 각 테이블을 갱신
	9. id 와 fname 으로 하나의 관광지 정보를 구성하는 각 테이블을 삭제 
	