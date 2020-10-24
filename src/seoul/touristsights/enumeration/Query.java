package seoul.touristsights.enumeration;

public enum Query {
	
	INSERT_SERVICE( "insert into service values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" ),
	INSERT_SIGHT( "insert into sight values( SEQ.nextval, ?, ?, ?, ? )" ),
	INSERT_DETAIL( "insert into detail values( SEQ.nextval - 1, ?, ?, ?, ? )" ), // ORA-08002 임시방편
	
	SELECT_DEFAULT( "select * from sight natural join service natural join detail" ),
	
	UPDATE_SERVICE_DEFAULT( "update service set " ),
	UPDATE_SERVICE_SUBQUERY( "select fname from sight where id = " ),
	UPDATE_SIGHT_DEFAULT( "update sight set " ),
	UPDATE_DETAIL_DEFAULT( "update detail set " ),
	
	DELETE_DEFAULT( "delete from " ),
	DELETE_DETAIL( "detail where id = " ),
	DELETE_SIGHT( "sight where id = " ),
	DELETE_SERVICE( "service where fname = " ),
	
	ORDER_BY_HITS_DESC( " order by hits desc" ),
	
	WHERE( " where " ),
	LIKE( " like " ),
	IN( " in" ),
	AND( " and " );

	private final String value;
	
	Query( String key ) {
		this.value = key;
	}
	
	public String getValue() {
		return this.value;
	}
}
