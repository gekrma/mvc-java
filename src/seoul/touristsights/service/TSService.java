package seoul.touristsights.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import seoul.touristsights.deserialize.Content;
import seoul.touristsights.deserialize.Deserialize;
import seoul.touristsights.dto.Service;
import seoul.touristsights.dto.Sight;
import seoul.touristsights.dto.SightCompleteInfo;
import seoul.touristsights.dto.SightDetail;
import seoul.touristsights.enumeration.Common;
import seoul.touristsights.enumeration.Query;
import seoul.touristsights.exception.NotExistException;
import seoul.touristsights.model.ServiceDAO;
import seoul.touristsights.model.SightCompleteInfoDAO;
import seoul.touristsights.model.CommonMethod;
import seoul.touristsights.model.SightDAO;
import seoul.touristsights.model.SightDetailDAO;

public class TSService {
	private static TSService instance = new TSService();

	private Deserialize deserialize = Deserialize.getInstance();
	private SightCompleteInfoDAO sightCompleteInfoDAO = SightCompleteInfoDAO.getInstance();
	private CommonMethod method = CommonMethod.getInstance();

	private TSService() {}
	
	public static TSService getInstance() {
		return instance;
	}
	
	// json 데이터 호출
	public List<Content> getJsonData() {
		return deserialize.getInitData();
	}
	
	// touristSight 저장
	public String insertTouristSight( Content content ) throws SQLException, NotExistException {
		boolean insertResult = method.divideInsert( content );
		
		if ( insertResult ) {
			return Common.SUCCESS_INSERT.getValue();	
		}
		
		throw new NotExistException( Common.FAIL_EXCEPTION.getValue() );
	}
	
	// 다중 조건으로 관광지 정보 불러오기
	public ArrayList<SightCompleteInfo> getTouristSightList( HashMap<String, String[]> map ) throws SQLException, NotExistException {
		StringBuilder query = new StringBuilder( Query.SELECT_DEFAULT.getValue() );
		String built = method.buildSelectQuery( query, map ).toString();
		ArrayList<SightCompleteInfo> list = sightCompleteInfoDAO.selectTouristSightList( built );
		
		if ( list.isEmpty() ) {
			throw new NotExistException( Common.FAIL_EMPTY.getValue() );
		}
		
		return list;
	}

	// 서비스 업데이트
	public String updateService( int id, char whether, String[] serviceList ) throws SQLException, NotExistException {
		StringBuilder query = new StringBuilder( Query.UPDATE_SERVICE_DEFAULT.getValue() );
		String built = method.buildServiceUpdate( query, whether, serviceList ).append( id + ")" ).toString();
		boolean updateResult = method.execute( built );
		
		if ( updateResult ) {
			return Common.SUCCESS_UPDATE.getValue();			
		}
		
		throw new NotExistException( Common.FAIL_CONDITION_EMPTY.getValue() );
	}
	
	// 관광지 업데이트
	public String updateSight( int id, String field, Object value ) throws SQLException, NotExistException {
		StringBuilder query = new StringBuilder( Query.UPDATE_SIGHT_DEFAULT.getValue() );
		String built = method.buildSightUpdate( query, field, value ).append( id ).toString();
		boolean updateResult = method.execute( built );
		
		if ( updateResult ) {
			return Common.SUCCESS_UPDATE.getValue();			
		}
		
		throw new NotExistException( Common.FAIL_CONDITION_EMPTY.getValue() );
	}
	
	// 관광지 상세정보 업데이트
	public String updateSightDetail( int id, String field, String value ) throws SQLException, NotExistException {
		StringBuilder query = new StringBuilder( Query.UPDATE_DETAIL_DEFAULT.getValue() );
		String built = method.buildSightUpdate( query, field, value ).append( id ).toString();
		boolean updateResult = method.execute( built );

		if ( updateResult ) {
			return Common.SUCCESS_UPDATE.getValue();			
		}
		
		throw new NotExistException( Common.FAIL_CONDITION_EMPTY.getValue() );
	}
	
	// id 로 관광지 상세정보 삭제
	public String deleteSightDetail( int id ) throws SQLException, NotExistException {
		StringBuilder query = new StringBuilder( Query.DELETE_DEFAULT.getValue() );
		String built = query.append( Query.DELETE_DETAIL.getValue() ).append( id ).toString();
		boolean deleteResult = method.execute( built );

		if ( deleteResult ) {
			return Common.SUCCESS_DELETE.getValue();
		}
		
		throw new NotExistException( Common.FAIL_CONDITION_EMPTY.getValue() );
	}
	
	// id 로 관광지 삭제
	public String deleteSight( int id ) throws SQLException, NotExistException {
		StringBuilder query = new StringBuilder( Query.DELETE_DEFAULT.getValue() );
		String built = query.append( Query.DELETE_SIGHT.getValue() ).append( id ).toString();
		boolean deleteResult = method.execute( built );
		
		if ( deleteResult ) {
			return Common.SUCCESS_DELETE.getValue();
		}
		
		throw new NotExistException( Common.FAIL_CONDITION_EMPTY.getValue() );
	}
	
	// facilityName 으로 서비스 삭제
	public String deleteService( String fname ) throws SQLException, NotExistException {
		StringBuilder query = new StringBuilder( Query.DELETE_DEFAULT.getValue() );
		String built = query.append( Query.DELETE_SERVICE.getValue() ).append( "'" + fname + "'" ).toString();
		boolean deleteResult = method.execute( built );
		
		if ( deleteResult ) {
			return Common.SUCCESS_DELETE.getValue();
		}
		
		throw new NotExistException( Common.FAIL_CONDITION_EMPTY.getValue() );
	}
	
}
