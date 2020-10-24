package seoul.touristsights.controller;

import java.sql.SQLException;
import java.util.HashMap;

import seoul.touristsights.deserialize.Content;
import seoul.touristsights.enumeration.Common;
import seoul.touristsights.exception.NotExistException;
import seoul.touristsights.service.TSService;
import seoul.touristsights.view.FailView;
import seoul.touristsights.view.SuccessView;

public class TSController {
	private static TSController instance = new TSController();
	private TSService service = TSService.getInstance();
	
	private TSController() {}
	
	public static TSController getInstance() {
		return instance;
	}
	
	// 관광지 저장
	public void insertTouristSight( Content content ) {
		
		try {
			SuccessView.showSuccessMsg( service.insertTouristSight( content ) );
		} catch ( SQLException | NotExistException error ) {
			if ( error instanceof SQLException ) {
				error.printStackTrace();
				FailView.showFailMsg( Common.FAIL_INSERT.getValue() );
			} else {
				FailView.showFailMsg( error.getMessage() );
			}
		}
	}
	
	// 관광지 리스트 저장
	public void insertTouristSightList() {
		service.getJsonData().stream().forEach( content -> insertTouristSight( content ) );
	}

	// 다중 조건으로 관광지 출력하기
	public void selectConditions( HashMap<String, String[]> map ) {
		
		try {
			SuccessView.showTouristSights( service.getTouristSightList( map ) );
		} catch ( SQLException | NotExistException error ) {
			if ( error instanceof SQLException ) {
				error.printStackTrace();
			} else {
				FailView.showFailMsg( error.getMessage() );
			}
		}
	}
	
	// 서비스(service 테이블) 업데이트
	public void updateService( int id, char whether, String[] serviceList ) {
		
		try {
			SuccessView.showSuccessMsg( service.updateService( id, whether, serviceList ) );
		} catch ( SQLException | NotExistException error ) {
			if ( error instanceof SQLException ) {
				error.printStackTrace();
				FailView.showFailMsg( Common.FAIL_UPDATE.getValue() );
			} else {
				FailView.showFailMsg( error.getMessage() );
			}
		}
	}
	
	// 관광지(sight 테이블) 업데이트
	public void updateSight( int id, String field, Object value ) {
		
		try {
			SuccessView.showSuccessMsg( service.updateSight( id, field, value ) );
		} catch ( SQLException | NotExistException error ) {
			if ( error instanceof SQLException ) {
				error.printStackTrace();
				FailView.showFailMsg( Common.FAIL_UPDATE.getValue() );
			} else {
				FailView.showFailMsg( error.getMessage() );
			}
		}
	}
	
	// 관광지 상세정보(detail 테이블) 업데이트
	public void updateSightDetail( int id, String field, String value ) {
		
		try {
			SuccessView.showSuccessMsg( service.updateSightDetail( id, field, value ) );
		} catch ( SQLException | NotExistException error ) {
			if ( error instanceof SQLException ) {
				error.printStackTrace();
				FailView.showFailMsg( Common.FAIL_UPDATE.getValue() );
			} else {
				FailView.showFailMsg( error.getMessage() );
			}
		}
	}
	
	// 관광지 상세정보 삭제
	public void deleteSightDetail( int id ) {
		
		try {
			SuccessView.showSuccessMsg( service.deleteSightDetail( id ) );
		} catch ( SQLException | NotExistException error ) {
			if ( error instanceof SQLException ) {
				error.printStackTrace();
				FailView.showFailMsg( Common.FAIL_DELETE.getValue() );
			} else {
				FailView.showFailMsg( error.getMessage() );
			}
		}
	}
	
	// 관광지 삭제
	public void deleteSight( int id ) {
		
		try {
			SuccessView.showSuccessMsg( service.deleteSight( id ) );
		} catch ( SQLException | NotExistException error ) {
			if ( error instanceof SQLException ) {
				error.printStackTrace();
				FailView.showFailMsg( Common.FAIL_DELETE.getValue() );
			} else {
				FailView.showFailMsg( error.getMessage() );
			}
		}
	}
	
	// 서비스 삭제
	public void deleteService( String facilityName ) {
		
		try {
			SuccessView.showSuccessMsg( service.deleteService( facilityName ) );
		} catch ( SQLException | NotExistException error ) {
			if ( error instanceof SQLException ) {
				error.printStackTrace();
				FailView.showFailMsg( Common.FAIL_DELETE.getValue() );
			} else {
				FailView.showFailMsg( error.getMessage() );
			}
		}
	}
	
}
