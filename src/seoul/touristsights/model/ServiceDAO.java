package seoul.touristsights.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import seoul.touristsights.dto.Service;
import seoul.touristsights.dto.SightCompleteInfo;
import seoul.touristsights.enumeration.Common;
import seoul.touristsights.enumeration.Query;
import seoul.touristsights.util.DBUtil;

public class ServiceDAO {
	private static ServiceDAO instance = new ServiceDAO();
	private ServiceDAO() {}
	
	public static ServiceDAO getInstance() {
		return instance;
	}
	
	// insert
	public boolean insertService( Service service ) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean insertResult = false;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement( Query.INSERT_SERVICE.getValue() );
			
			pstmt.setString( 1, service.getFacilityName() );
			pstmt.setString( 2, service.getMainEntranceLoad() );
			pstmt.setString( 3, service.getAccessibleParkingArea() );
			pstmt.setString( 4, service.getRemoveHeightDifferenceOfMainEntrance() );
			pstmt.setString( 5, service.getAccessibleElevator() );
			pstmt.setString( 6, service.getAccessibleToilet() );
			pstmt.setString( 7, service.getAccessibleGuestRoom() );
			pstmt.setString( 8, service.getAccessibleSeats() );
			pstmt.setString( 9, service.getAccessibleTicketOffice() );
			pstmt.setString( 10, service.getBlindConvenienceService() );
			pstmt.setString( 11, service.getDeafConvenienceService() );
			pstmt.setString( 12, service.getInformationService() );
			pstmt.setString( 13, service.getWheelChairRental() );
			
			insertResult = ( pstmt.executeUpdate() != 0 );
		} finally {
			DBUtil.close( con, pstmt );
		}
		
		return insertResult;
	}
	
}
