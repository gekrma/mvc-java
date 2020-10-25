package seoul.touristsights.view;

import java.util.ArrayList;
import java.util.stream.IntStream;

import seoul.touristsights.dto.SightCompleteInfo;

public class SuccessView {

	public static void showSuccessMsg( String msg ) {
		System.out.println( msg );
	}
	
	public static void showTouristSights( ArrayList<SightCompleteInfo> list ) {
		
                            IntStream.range( 0, list.size() )
                                     .mapToObj( index -> ( index + 1 ) + ". " + list.get( index ) )
                                     .forEach( System.out :: println );
	}
}
