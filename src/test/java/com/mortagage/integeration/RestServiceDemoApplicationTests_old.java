package com.mortagage.integeration;/*
package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static com.google.common.base.Predicates.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class RestServiceDemoApplicationTests {

    @Test
    public void givenUserDoesNotExists_whenUserInfoIsRetrieved_then404IsReceived()
            throws IOException {

        // Given
        String name = RandomStringUtils.randomAlphabetic(8);
        HttpUriRequest request = new HttpGet("https://api.github.com/users/" + name);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_NOT_FOUND));
    }

}
@Test
public void
givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson()
  throws ClientProtocolException, IOException {

   // Given
   String jsonMimeType = "application/json";
   HttpUriRequest request = new HttpGet( "https://api.github.com/users/eugenp" );

   // When
   HttpResponse response = HttpClientBuilder.create().build().execute( request );

   // Then
   String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
   assertEquals( jsonMimeType, mimeType );
}
@Test
public void
  givenUserExists_whenUserInformationIsRetrieved_thenRetrievedResourceIsCorrect()
  throws ClientProtocolException, IOException {

    // Given
    HttpUriRequest request = new HttpGet( "https://api.github.com/users/eugenp" );

    // When
    HttpResponse response = HttpClientBuilder.create().build().execute( request );

    // Then
    GitHubUser resource = RetrieveUtil.retrieveResourceFromResponse(
      response, GitHubUser.class);
    assertThat( "eugenp", Matchers.is( resource.getLogin() ) );
}
*/
