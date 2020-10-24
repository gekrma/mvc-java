package seoul.touristsights.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SightDetail {
	private String phoneNumber; // 전화번호
	private String address; // 주소
	private String image; // 이미지 경로
	private String homepage; // 홈페이지
}
