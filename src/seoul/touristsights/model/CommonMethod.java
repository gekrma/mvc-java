package seoul.touristsights.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import seoul.touristsights.deserialize.Content;
import seoul.touristsights.dto.Service;
import seoul.touristsights.dto.Sight;
import seoul.touristsights.dto.SightCompleteInfo;
import seoul.touristsights.dto.SightDetail;
import seoul.touristsights.enumeration.Common;
import seoul.touristsights.enumeration.Query;
import seoul.touristsights.util.DBUtil;

public class CommonMethod {
	private static CommonMethod instance = new CommonMethod();
	private static ServiceDAO serviceDAO = ServiceDAO.getInstance();
	private static SightDAO sightDAO = SightDAO.getInstance();
	private static SightDetailDAO detailDAO = SightDetailDAO.getInstance();
	
	private CommonMethod() {}
	
	public static CommonMethod getInstance() {
		return instance;
	}
	
	// 공통 로직 함수 - add
	public void add( ArrayList<SightCompleteInfo> list, ResultSet rset ) throws SQLException {
		
		list.add( 
				new SightCompleteInfo(
						new Sight(
									rset.getString( Common.FACILITY_NAME.getValue() ),
									rset.getString( Common.DISTRICT.getValue() ),
									rset.getString( Common.SECTION.getValue() ),
									rset.getString( Common.HITS.getValue() )
								),
						new Service(
									rset.getString( Common.FACILITY_NAME.getValue() ),
									rset.getString( Common.MAIN_ENTRANCE_LOAD.getValue() ),
									rset.getString( Common.ACCESSIBLE_PARKING_AREA.getValue() ),
									rset.getString( Common.REMOVE_HEIGHT_DIFFERENCE_OF_MAIN_ENTRANCE.getValue() ),
									rset.getString( Common.ACCESSIBLE_ELEVATOR.getValue() ),
									rset.getString( Common.ACCESSIBLE_TOILET.getValue() ),
									rset.getString( Common.ACCESSIBLE_GUESTROOM.getValue() ),
									rset.getString( Common.ACCESSIBLE_SEATS.getValue() ),
									rset.getString( Common.ACCESSIBLE_TICKET_OFFICE.getValue() ),
									rset.getString( Common.BLIND_CONVENIENCE_SERVICE.getValue() ),
									rset.getString( Common.DEAF_CONVENIENCE_SERVICE.getValue() ),
									rset.getString( Common.INFORMATION_SERVICE.getValue() ),
									rset.getString( Common.WHEELCHAIR_RENTAL.getValue() )
								),
						new SightDetail(
									rset.getString( Common.PHONE_NUMBER.getValue() ),
									rset.getString( Common.ADDRESS.getValue() ),
									rset.getString( Common.IMAGE.getValue() ),
									rset.getString( Common.HOMEPAGE.getValue() )
								)
				));
	}
	
	// insert 시 테이블별 분기
	public boolean divideInsert( Content content ) throws SQLException {
		boolean insertResult = true;
		
		while ( insertResult ) {
			
			insertResult = serviceDAO.insertService( 
					new Service(
								content.getSisulname(),
								content.getSt1(),
								content.getSt2(),
								content.getSt3(),
								content.getSt4(),
								content.getSt5(),
								content.getSt6(),
								content.getSt7(),
								content.getSt8(),
								content.getSt9(),
								content.getSt10(),
								content.getSt11(),
								content.getSt12()
							));
			
			insertResult = sightDAO.insertSight( 
					new Sight(
								content.getSisulname(),
								content.getGu(),
								content.getSection(),
								content.getHit()
							));
			
			insertResult = detailDAO.insertSightDetail( 
					new SightDetail(
								content.getTel(),
								content.getAddr(),
								content.getImages(),
								content.getHomepage()
							));

			break;
		}
		
		return insertResult;		
	}
		
	// update or delete
	public boolean execute( String query ) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean updateResult = false;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement( query );
			updateResult = ( pstmt.executeUpdate() != 0 );
		} finally {
			DBUtil.close( con, pstmt );
		}
		
		return updateResult;
	}
	
	// 다중 조건으로 조회 시 분기 로직
	public StringBuilder buildSelectQuery( StringBuilder query, HashMap<String, String[]> map ) {
		String[] values = null;
		boolean first = true;
		boolean hasHits = false;

		for ( String key : map.keySet() ) {
			values = map.get( key );
			query.append( first ? Query.WHERE.getValue() : Query.AND.getValue() );

			switch ( key ) {
				
				case "fname":
					query = buildFacilityname( query, values[0] );
					break;
			
				case "hits":
					query = buildHits( query );
					hasHits = true;
					break;
				
				case "district":
					query.append( Common.DISTRICT.getValue() + " " + Query.IN.getValue() );
					query = buildIn( query, values );
					break;
				
				case "service":
					query = buildService( query, values );
					break;
				
				case "section":
					query.append( Common.SECTION.getValue() + " " + Query.IN.getValue() );
					query = buildIn( query, values );
					break;
					
				default:
					query.append( Common.ID.getValue() + " = " + Integer.parseInt( values[0] ) ); // id 조회
			}
			
			first = false;
		}

		return query.append( hasHits ? Query.ORDER_BY_HITS_DESC.getValue() : "" );
	}
	
	// build( facilityName like )
	public StringBuilder buildFacilityname( StringBuilder builder, String facilityName ) {
		return builder.append( Common.FACILITY_NAME.getValue() + " " )
					  .append( Query.LIKE.getValue() )
					  .append( "'%" + facilityName + "%'" );
	}
	
	// build(hits)
	public StringBuilder buildHits( StringBuilder builder ) {
		return builder.append( Common.HITS.getValue() )
				  	  .append( " >= " + 2 );
	}
	
	// build(in)
	public StringBuilder buildIn( StringBuilder builder, String[] array ) {
		builder.append( "(" );
		
		for ( String value : array ) {
			builder.append( "'" + value + "'" + "," );
		}
		
		return builder.deleteCharAt( builder.length() - 1 ).append( ")" );
	}
	
	// build( service 테이블 ) 
	public StringBuilder buildService( StringBuilder builder, String[] array ) {

		for ( String value : array ) {
			builder.append( value + " = 'Y'" + Query.AND.getValue() );
		}

		return builder.delete( builder.lastIndexOf( Query.AND.getValue() ), builder.length() );
	}
	
	// build( (set) field = 'Y' or 'N', ... id = (id) )
	public StringBuilder buildServiceUpdate( StringBuilder builder, char whether, String[] serviceList ) {

		for ( String service : serviceList ) {
			builder.append( service + " = '" + whether + "', " );
		}
		
		return builder.delete( builder.lastIndexOf( "," ), builder.length() )
				   	  .append( Query.WHERE.getValue() )
				   	  .append( Common.FACILITY_NAME.getValue() )
				   	  .append( " = (" )
				   	  .append( Query.UPDATE_SERVICE_SUBQUERY.getValue() );
	}
	
	// build( (set) field = 'value' where id = (id) ) 
	public StringBuilder buildSightUpdate( StringBuilder builder, String field, Object value ) {
		return builder.append( field + " = " )
					  .append( value instanceof String ? ( "'" + value + "'" ) : value )
					  .append( Query.WHERE.getValue() )
					  .append( Common.ID.getValue() + " = " );
	}
	
}
