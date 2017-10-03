package MavenProject.fgh;

import static org.hamcrest.Matchers.contains;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

@Listeners({MyTestListenerClass.class})
public class asd extends ExtentReportsClass {
	
	Response respone;
	String a;
	JSONArray array;
	JSONObject dsf;
	

	@BeforeClass
	public void beforeClass() {
		respone = RestAssured.given().when()
				.get("http://restcountries.eu/rest/v1/name/norway").then().
				extract().response();
		a = respone.asString();
		array = new JSONArray(a);		
		dsf = (JSONObject) array.get(0);
	}
	
	
	@Test
	public void test1() {
		logger = extent.startTest("test1");
		RestAssured.given().
	    when().
	        get("http://restcountries.eu/rest/v1/name/norway").
	    then().
	        assertThat().
	        statusCode(200).
	    and().
	        contentType(ContentType.JSON);			
	}
	
	@Test
	public void test2() {
		logger = extent.startTest("test2");
		RestAssured.given().
	    when().
	        get("http://restcountries.eu/rest/v1/name/norway").
	    then().
	        //assertThat().
	        body("name", contains("Norway")).
	        body("alpha2Code", contains("NO"));
	                
	}
	
	@Test
	public void test3() {
		logger = extent.startTest("test3");
		System.out.println(dsf.get("name"));
		Assert.assertEquals(dsf.get("name"), "Norway", "Fail");	                
	}
	
	@Test
	public void test4() {
		logger = extent.startTest("test4");
		JSONArray domainArrayJson = dsf.getJSONArray("topLevelDomain");
		ArrayList domainArrayExpected = new ArrayList<String>();
		domainArrayExpected.add(".no");
		ArrayList domainArrayActual = new ArrayList<String>();
		for(int i = 0; i< domainArrayJson.length(); i++){
			domainArrayActual.add(domainArrayJson.get(i));
			
		}
		Assert.assertEquals(domainArrayActual, domainArrayExpected, "Fail");
	                
	}
	
}
