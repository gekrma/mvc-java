package seoul.touristsights.view;

import java.util.HashMap;

import seoul.touristsights.controller.TSController;
import seoul.touristsights.enumeration.Common;

public class StartView {

	public static void main(String[] args) {
		TSController controller = TSController.getInstance();
		HashMap<String, String[]> conditions = new HashMap<>();

//		System.out.println( "===== 1. json 데이터 저장 =====" );
//		controller.insertTouristSightList();
		
		System.out.println( "===== 2. 모든 관광지 정보 출력하기 =====" );
		conditions.clear();
		controller.selectConditions( conditions );
		
		System.out.println( "===== 3. 특정 시설명을 포함하는 관광지 정보 출력하기 =====" );
		conditions.clear();
		conditions.put( Common.FACILITY_NAME.getValue(), new String[] { "꿈의숲" } );
		controller.selectConditions( conditions );
		
		System.out.println( "===== 4. 좋아요 2개 이상 관광지 정보 좋아요 내림차순 출력하기 (=인기순) =====" );
		conditions.clear();
		conditions.put( Common.HITS.getValue(), null );
		controller.selectConditions( conditions );
		
		System.out.println( "===== 5-1. 특정 구역에 있는 관광지 출력하기 (다중선택 가능)=====" );
		conditions.clear();
		conditions.put( Common.DISTRICT.getValue(), new String[] { "성동구", "마포구" } );
		controller.selectConditions( conditions );
		
		System.out.println( "===== 5-2. 특정 구역에 존재하지 않는 관광지 예외처리 =====" );
		conditions.clear();
		conditions.put( Common.DISTRICT.getValue(), new String[] { "미추홀구" } );
		controller.selectConditions( conditions );
		
		System.out.println( "===== 6. 특정 서비스가 가능한 관광지 출력하기 (다중필수 가능) =====" );
//////		MAIN_ENTRANCE_LOAD( "entrance" ), 주출입구 접근로 여부
//////		ACCESSIBLE_PARKING_AREA( "parking" ), 장애인 전용 주차구역
//////		REMOVE_HEIGHT_DIFFERENCE_OF_MAIN_ENTRANCE( "remove_height" ), 주출입구 높이차이 제거
//////		ACCESSIBLE_ALEVATOR( "alevator" ), 장애인용 승강기
//////		ACCESSIBLE_TOILET( "toilet" ), 장애인 화장실
//////		ACCESSIBLE_GUESTROOM( "guestroom" ), 장애인용 객실 이용가능
//////		ACCESSIBLE_SEATS( "seats" ), 장애인용 관람석 이용가능
//////		ACCESSIBLE_TICKET_OFFICE( "ticket_office" ), 장애인 매표쇼
//////		BLIND_CONVENIENCE_SERVICE( "blind_service" ), 시각장애인 편의서비스
//////		DEAF_CONVENIENCE_SERVICE( "deaf_service" ), 청각장애인 편의서비스
//////		INFORMATION_SERVICE( "info_service" ), 안내 서비스
//////		WHEELCHAIR_RENTAL( "wheelchair" ) 휠체어 대여
		conditions.clear();
		conditions.put( Common.SERVICE.getValue(),  new String[] { Common.ACCESSIBLE_TICKET_OFFICE.getValue(), Common.ACCESSIBLE_TOILET.getValue() } );
		controller.selectConditions( conditions );
		
		System.out.println( "===== 7-1. 특정 분류에 해당하는 관광지 출력하기 (다중선택 가능) =====" );
//////		1. 수목원
//////		2. 박물관
//////		3. 기념관
//////		4. 공원
//////		5. 미술관
//////		6. 체험관
//////		7. 고궁
//////		8. 기타
		conditions.clear();
		conditions.put( Common.SECTION.getValue(),  new String[] { "공원", "미술관" } );
		controller.selectConditions( conditions );
		
		System.out.println( "===== 7-2. 특정 분류에 해당하지 않는 관광지 예외처리 =====" );
		conditions.clear();
		conditions.put( Common.SECTION.getValue(),  new String[] { "휴양지" } );
		controller.selectConditions( conditions );
		
		System.out.println( "===== 8. 다중 조건으로 관광지 출력하기 =====" );
		conditions.clear();
		
		conditions.put( Common.HITS.getValue(), null );
		conditions.put( Common.DISTRICT.getValue(), new String[] { "중구", "마포구" } );
		conditions.put( Common.SERVICE.getValue(), new String[] { Common.MAIN_ENTRANCE_LOAD.getValue(), Common.WHEELCHAIR_RENTAL.getValue() } );
		conditions.put( Common.SECTION.getValue(), new String[] { "고궁", "기념관" } );
		
		controller.selectConditions( conditions );

		System.out.println( "===== 9. id 로 하나의 관광지 정보를 구성하는 각 테이블 갱신하기 =====" );
		conditions.clear();
		conditions.put( Common.ID.getValue(), new String[] { "9" } );
		
		System.out.println( "===== 9-1-1. id 로 관광지를 조회하여 그에 연결된 서비스 테이블에서 입력한 서비스를 'Y' 혹은 'N' 으로 갱신 =====" );
		controller.selectConditions( conditions );
		controller.updateService( 9, 'Y', new String[] { Common.MAIN_ENTRANCE_LOAD.getValue(), Common.ACCESSIBLE_SEATS.getValue() } );
		controller.selectConditions( conditions );
		
		System.out.println( "===== 9-1-2. 존재하지 않는 id 로 조회하여 갱신 시 예외처리 =====" );
		controller.updateService( 500, 'Y', new String[] { Common.MAIN_ENTRANCE_LOAD.getValue(), Common.ACCESSIBLE_SEATS.getValue() } );
		
		System.out.println( "===== 9-2-1. id 로 관광지를 조회하여 입력한 필드와 값으로 갱신 =====" );
		controller.selectConditions( conditions );
		controller.updateSight( 9, Common.DISTRICT.getValue(), "마포구" );
		controller.selectConditions( conditions );
		
		System.out.println( "===== 9-2-2. 존재하지 않는 id 로 조회하여 갱신 시 예외처리 =====" );
		controller.updateSight( 500, Common.DISTRICT.getValue(), "마포구" );
		
		System.out.println( "===== 9-3-1. id 로 관광지 상세정보를 조회하여 입력한 필드와 값으로 갱신 =====" );
		controller.selectConditions( conditions );
		controller.updateSightDetail( 9, Common.PHONE_NUMBER.getValue(), null );
		controller.selectConditions( conditions );
		
		System.out.println( "===== 9-3-2. 존재하지 않는 id 로 조회하여 갱신 시 예외처리 =====" );
		controller.updateSightDetail( 500, Common.PHONE_NUMBER.getValue(), null );
		
		System.out.println( "===== 10. id 와 facilityName 으로 하나의 관광지 정보를 구성하는 각 테이블 삭제하기 =====" );
		conditions.clear();
		conditions.put( Common.FACILITY_NAME.getValue(), new String[] { "aA갤러리_aA디자인뮤지엄" } );
		
		System.out.println( "===== 10-1-1. id 로 관광지 상세정보 삭제하기 =====" );
		controller.deleteSightDetail( 1 );
		
		System.out.println( "===== 10-1-2. 이미 삭제했거나 존재하지 않는 관광지 상세정보 삭제 시 예외처리 =====" );
		controller.deleteSightDetail( 1 );
		controller.deleteSightDetail( 500 );
		
		System.out.println( "===== 10-2-1. id 로 관광지 삭제하기 =====" );
		controller.deleteSight( 1 );
		
		System.out.println( "===== 10-2-2. 이미 삭제했거나 존재하지 않는 관광지 삭제 시 예외처리 =====" );
		controller.deleteSight( 1 );
		controller.deleteSight( 500 );
		
		System.out.println( "===== 10-3-1. facilityName 으로 서비스 삭제하기 =====" );
		controller.deleteService( "aA갤러리_aA디자인뮤지엄" );
		
		System.out.println( "===== 10-3-2. 이미 삭제했거나 존재하지 않는 관광지 삭제 시 예외처리 =====" );
		controller.deleteService( "aA갤러리_aA디자인뮤지엄" );
		controller.deleteService( "abba갤러리" );
		controller.selectConditions( conditions );
	
	}
}
