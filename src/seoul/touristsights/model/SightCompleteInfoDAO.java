package seoul.touristsights.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import seoul.touristsights.dto.SightCompleteInfo;
import seoul.touristsights.util.DBUtil;

public class SightCompleteInfoDAO {
	private static SightCompleteInfoDAO instance = new SightCompleteInfoDAO();
	private CommonMethod method = CommonMethod.getInstance();
	
	private SightCompleteInfoDAO() {}
	
	public static SightCompleteInfoDAO getInstance() {
		return instance;
	}
	
	// 조건에 해당하는 모든 관광지 정보 조회
	public ArrayList<SightCompleteInfo> selectTouristSightList( String query ) throws SQLException {
		ArrayList<SightCompleteInfo> sightList = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			rset = stmt.executeQuery( query );

			while ( rset.next() ) {
				method.add( sightList, rset );
			}

		} finally {
			DBUtil.close( con, stmt, rset );
		}
		
		return sightList;
	}
	
}
