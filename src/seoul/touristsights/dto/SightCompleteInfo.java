package seoul.touristsights.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SightCompleteInfo {
	private Sight sight; // Sight 테이블
	private Service service; // service 테이블
	private SightDetail detail; // detail 테이블
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(sight);
		builder.append(", ");
		builder.append(service);
		builder.append(", ");
		builder.append(detail);
		
		return builder.toString();
	}
	
}
