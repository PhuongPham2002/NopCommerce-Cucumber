package RestAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetRequestTest {
    @Test
    public void testGetPosts(){
        Response response = RestAssured.given().auth().preemptive().basic("295eebe843a1747a60f534b98b8a67a6","26f3bfa5af713aa083be1ccf1ba15f58")
                        .when().get("http://localhost:5000/odata/v1/customers");
        System.out.println("Response Body: " + response.getBody().asString());
        Assert.assertEquals(response.statusCode(),200);
    }
}
