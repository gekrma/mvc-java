package seoul.touristsights.enumeration;

public enum Common {
	JSON_PATH( "C:\\0.posco\\01.java\\step17_SeoulTouristSights\\data\\SeoulTouristSightsInformation.json" ),
	
	DB_PROPERTIES( "db.properties" ),
	JDBC_DRIVER( "jdbc.driver" ),
	JDBC_URL( "jdbc.url" ),
	JDBC_ID( "jdbc.id" ),
	JDBC_PW( "jdbc.pw" ),
	
	SUCCESS_INSERT( "성공적으로 등록되었습니다" ),
	SUCCESS_UPDATE( "성공적으로 갱신되었습니다" ),
	SUCCESS_DELETE( "성공적으로 삭제되었습니다" ),
	
	FAIL_INSERT( "등록에 실패하였습니다." ),
	FAIL_SELECT( "조회에 실패하였습니다." ),
	FAIL_EMPTY( "아무런 정보가 없습니다." ),
	FAIL_UPDATE( "갱신에 실패하였습니다." ),
	FAIL_CONDITION_EMPTY( "입력한 조건에 해당하는 테이블이 없습니다" ),
	FAIL_EXCEPTION( "SQLException 이외의 경우입니다"),
	FAIL_DELETE( "삭제에 실패하였습니다." ),

	FACILITY_NAME( "fname" ),
	DISTRICT( "district" ),
	SECTION( "section" ),
	HITS( "hits" ),
	
	MAIN_ENTRANCE_LOAD( "entrance" ),
	ACCESSIBLE_PARKING_AREA( "parking" ),
	REMOVE_HEIGHT_DIFFERENCE_OF_MAIN_ENTRANCE( "remove_height" ),
	ACCESSIBLE_ELEVATOR( "elevator" ),
	ACCESSIBLE_TOILET( "toilet" ),
	ACCESSIBLE_GUESTROOM( "guestroom" ),
	ACCESSIBLE_SEATS( "seats" ),
	ACCESSIBLE_TICKET_OFFICE( "ticket_office" ),
	BLIND_CONVENIENCE_SERVICE( "blind_service" ),
	DEAF_CONVENIENCE_SERVICE( "deaf_service" ),
	INFORMATION_SERVICE( "info_service" ),
	WHEELCHAIR_RENTAL( "wheelchair" ),
	
	PHONE_NUMBER( "tel" ),
	ADDRESS( "address" ),
	IMAGE( "image" ),
	HOMEPAGE( "homepage" ),
	ID( "id" ),
	
	SERVICE( "service" );
	
	private final String value;
	
	Common( String key ) {
		this.value = key;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
