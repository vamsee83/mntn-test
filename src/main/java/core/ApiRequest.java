package core;


import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.MatcherAssert;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;

@Log4j2
public class ApiRequest {

    private String requestUrl;
    private Response response;
    private ContentType contentType;
    private Map<String, String> headers = new HashMap<>();

    public ApiRequest post(Object body, Integer responseCode, RequestSpecification requestSpecification) {
        log.info(String.format("Triggering POST request for body %s", body.toString()));

        response = RestAssured.given()
            .spec(requestSpecification)
            .filter(new RequestLoggingFilter())
            .filter(new ResponseLoggingFilter())
            .body(body).headers(getHeaders())
            .when()
            .post();

        // Assert the response code actual vs expected
        MatcherAssert.assertThat("Response didn't match", response.getStatusCode(),
            equalTo(responseCode));

        return this;

    }

    public ApiRequest get(Integer responseCode, RequestSpecification requestSpecification) {
        log.info("Triggering GET request for body");

        response = RestAssured.given()
                .spec(requestSpecification)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when()
                .headers(getHeaders())
                .get();

        // Assert the response code actual vs expected
        MatcherAssert.assertThat("Response didn't match", response.getStatusCode(),
                equalTo(responseCode));

        return this;

    }

    private Map<String, String> getHeaders(){
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", ContentType.JSON.toString());
        headers.put("Accept", ContentType.JSON.toString());
        return headers;
    }

    public ApiRequest setRequestUrl(String url) {
        this.requestUrl = url;
        return this;
    }

    public ApiRequest setContentType(ContentType contentType) {
        this.contentType = contentType;
        return this;
    }

    public Response getResponse() {
        return response;
    }

}
