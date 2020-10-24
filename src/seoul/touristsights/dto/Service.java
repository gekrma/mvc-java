package seoul.touristsights.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString( exclude = { "facilityName" } )
@NoArgsConstructor
@AllArgsConstructor
public class Service {
	private String facilityName; // 시설명(PK)
	private String mainEntranceLoad; // 주출입구 접근로
	private String accessibleParkingArea; // 장애인 전용 주차구역
	private String removeHeightDifferenceOfMainEntrance; // 주출입구 높이차이 제거
	private String accessibleElevator; // 장애인용 승강기
	private String accessibleToilet; // 장애인용 화장실
	private String accessibleGuestRoom; // 장애인용 객실
	private String accessibleSeats; // 장애인용 관람석
	private String accessibleTicketOffice; // 장애인 매표소
	private String blindConvenienceService; // 시각장애인 편의서비스
	private String deafConvenienceService; // 청각장애인 편의서비스
	private String informationService; // 안내 서비스
	private String wheelChairRental; // 휠체어 대여
}
