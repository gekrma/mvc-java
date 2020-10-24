package seoul.touristsights.deserialize;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import seoul.touristsights.enumeration.Common;

public class Deserialize {
	private static Deserialize instance = new Deserialize();
	private List<Content> sights = null;
	
	private Deserialize() {
		Gson gson = new Gson();
		
		try {
			JsonReader jsonReader = new JsonReader( new FileReader( Common.JSON_PATH.getValue() ) );
			DeserializeObject deserializeObject = gson.fromJson( jsonReader, DeserializeObject.class );
			sights = Stream.of( deserializeObject.getDATA() ).collect( Collectors.toList() );
		} catch ( IOException error ) {
			error.printStackTrace();
		}
	}
	
	public static Deserialize getInstance() {
		return instance;
	}
	
	public List<Content> getInitData() {
		return sights;
	}
}
