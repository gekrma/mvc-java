package seoul.touristsights.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import seoul.touristsights.dto.Sight;
import seoul.touristsights.enumeration.Common;
import seoul.touristsights.enumeration.Query;
import seoul.touristsights.util.DBUtil;

public class SightDAO {
	private static SightDAO instance = new SightDAO();
	private SightDAO() {}
	
	public static SightDAO getInstance() {
		return instance;
	}
	
	// insert
	public boolean insertSight( Sight sight ) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean insertResult = false;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement( Query.INSERT_SIGHT.getValue() );
			
			pstmt.setString( 1, sight.getFacilityName() );
			pstmt.setString( 2, sight.getDistrict() );
			pstmt.setString( 3, sight.getSection() );
			pstmt.setString( 4, sight.getHits() );
			
			insertResult = ( pstmt.executeUpdate() != 0 );
		} finally {
			DBUtil.close( con, pstmt );
		}
		
		return insertResult;
	}

}
