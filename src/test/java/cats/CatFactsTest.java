package cats;

import core.ApiRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pojos.CatFactsResponse;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class CatFactsTest extends ApiRequest {

    RequestSpecification requestSpecification;

    @BeforeClass
    @Parameters({"url"})
    public void setupRequestSpecification(String url){
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(url)
                .setBasePath("/facts").build();
    }

    @Test
    @Parameters({"factsCount", "factText"})
    public void verifyCatFactsGETTest(int factsCount, String factText) {
        List<CatFactsResponse> catFactsResponse = Arrays.asList(get(HttpStatus.SC_OK, requestSpecification)
                .getResponse()
                .getBody()
                .as(CatFactsResponse[].class));
        // Validate the response
        MatcherAssert.assertThat(
                catFactsResponse.size(), is(factsCount));
        List<String> factNames = catFactsResponse.stream()
                .map(CatFactsResponse::getText).collect(Collectors.toList());
        MatcherAssert.assertThat(factNames, hasItem(factText));
    }


}
