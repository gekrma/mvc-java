package seoul.touristsights.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sight {
//	private String id (PK, SEQ) but DTO 에서는 쓰지 않음
	private String facilityName; // 시설명(FK)
	private String district; // 구역
	private String section; // 분류
	private String hits; // 좋아요 수
}
