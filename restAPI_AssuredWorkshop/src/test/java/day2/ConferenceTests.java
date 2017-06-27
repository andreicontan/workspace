package day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by andreicontan on 28/02/2017.
 */
public class ConferenceTests {

    private static String url = "https://testbashnl.herokuapp.com";
    private static String token;

//    @BeforeClass
    public static void loginAsAdminToken(){

        token = given().
                contentType(ContentType.URLENC).
                formParam("grant_type", "password").
                formParam("username", "userJos").
                formParam("password", "Password01").

                when().
                post(url + "/oauth/token").
                then().
                extract().body().path("access_token");


    }


    @Test
    public void loginAsAdmin(){

        given().
                contentType(ContentType.URLENC).
                formParam("grant_type", "password").
                formParam("username", "userJos").
                formParam("password", "Password01").

                when().
                post(url + "/oauth/token").
                then().
                statusCode(200).
                body("access_token", notNullValue());


    }

    @Test
    public void getAllConferences(){



        Response r = when().
                get(url + "/conferences");
//                        get("https://testbashnl.herokuapp.com/conferences").
        List<String> ids = r.getBody().jsonPath().getList("content.id");
        System.out.println(ids.get(0));

    }


   @Test
   public void getConferenceByID(){
       String id = "6f26c7ee-6e91-4a21-9042-210776765cde";
       when().
               get(url + "/conferences/"+id).
        then().
               statusCode(200).
               body("id", equalTo(id));
   }

    @Test
    public void deleteConferenceByID() {


        String token = given().
                formParam("grant_type", "password").
                formParam("username", "superadmin").
                formParam("password", "Password01").
                when().
                post(url + "/oauth/token")
                .then().
                        extract().body().//jsonPath().prettyPrint();
                        path("access_token");

        Response r = when().
                get(url + "/conferences");
//                        get("https://testbashnl.herokuapp.com/conferences").
        List<String> ids = r.getBody().jsonPath().getList("content.id");

        for (int i = 0; i < ids.size() - 5; i++) {
            Response del = given().header("Authorization", "Bearer " + token).
                    when().delete("https://testbashnl.herokuapp.com/conferences/" + ids.get(i));
            System.out.println(del.getStatusCode() + " " + i);
        }
    }

    @Test
    public void getConferenceBySpeaker(){

    }

    @Test
    public void createConference(){
        token = given().
                contentType(ContentType.URLENC).
                formParam("grant_type", "password").
                formParam("username", "userJos").
                formParam("password", "Password01").

                when().
                post(url + "/oauth/token").
                then().
                extract().body().path("access_token");

       Conference conference = generateConference();
        given().

                header("Authorization", "Bearer "+token).
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                body(conference).
        when().
                post(url + "/conferences").
        then().
                statusCode(201);
//        extract().body().jsonPath().prettyPrint();

    }

    private Conference generateConference(){
        Location loc = new Location();
        loc.setCity("CrazystanCity");
        loc.setCountry("Crazystan");
        loc.setUrl("www.crazyconf.cr");
        loc.setVenueName("TBD");
        Speaker speaker = new Speaker();
        speaker.setName("NoBody");
        speaker.setTitle("Hero");
        List<Speaker> speakers = new ArrayList<Speaker>();
        speakers.add(speaker);

        Conference conf = new Conference();
        conf.setC4pEndDate("2017-12-12");
        conf.setC4pStartDate("2017-12-12");
        conf.setEndDate("2017-12-12");
        conf.setStartDate("2017-12-12");
        conf.setLocation(loc);
        conf.setSpeakers(speakers);
        conf.setName("CrazyConf");


        return conf;
    }

}
