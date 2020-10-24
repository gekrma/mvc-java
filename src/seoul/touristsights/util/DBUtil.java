package seoul.touristsights.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import seoul.touristsights.enumeration.Common;

public class DBUtil {
	static Properties propertiesInfo = new Properties();
	
	static {
		try {
			propertiesInfo.load( new FileInputStream( Common.DB_PROPERTIES.getValue() ) );			
		} catch ( IOException error ) {
			error.printStackTrace();
		}
	}
	
	static {
		try {
			Class.forName( propertiesInfo.getProperty( Common.JDBC_DRIVER.getValue() ) );
		} catch ( ClassNotFoundException error ) {
			error.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection( 
					propertiesInfo.getProperty( Common.JDBC_URL.getValue() ), 
					propertiesInfo.getProperty( Common.JDBC_ID.getValue() ), 
					propertiesInfo.getProperty( Common.JDBC_PW.getValue() )
				);
	}
	
	public static void close( Connection con, Statement stmt, ResultSet rset ) {
	
		try {
		
			if ( rset != null ) {
				rset.close();
				rset = null;
			}
			
			if ( stmt != null ) {
				stmt.close();
				stmt = null;
			}
			
			if ( con != null ) {
				con.close();
				con = null;
			}
		} catch ( SQLException error ) {
			error.printStackTrace();
		}
	}

	public static void close( Connection con, Statement stmt ) {
		
		try {
			
			if ( stmt != null ) {
				stmt.close();
				stmt = null;
			}
			
			if ( con != null ) {
				con.close();
				con = null;
			}
		} catch ( SQLException error ) {
			error.printStackTrace();
		}
	}
}
