package seoul.touristsights.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import seoul.touristsights.dto.SightDetail;
import seoul.touristsights.enumeration.Query;
import seoul.touristsights.util.DBUtil;

public class SightDetailDAO {
	private static SightDetailDAO instance = new SightDetailDAO();
	private SightDetailDAO() {}
	
	public static SightDetailDAO getInstance() {
		return instance;
	}
	
	// insert
	public boolean insertSightDetail( SightDetail detail ) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean insertResult = false;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement( Query.INSERT_DETAIL.getValue() );
			
			pstmt.setString( 1, detail.getPhoneNumber() );
			pstmt.setString( 2, detail.getAddress() );
			pstmt.setString( 3, detail.getImage() );
			pstmt.setString( 4, detail.getHomepage() );
			
			insertResult = ( pstmt.executeUpdate() != 0 );
		} finally {
			DBUtil.close( con, pstmt );
		}
		
		return insertResult;
	}
}
