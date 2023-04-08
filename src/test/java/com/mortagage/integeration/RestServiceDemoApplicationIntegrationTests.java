/*
package com.mortagage;

import com.example.demo.domain.Reservation;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ReservationApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestServiceDemoApplicationIntegrationTests {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testRetrieveReservationsCourse() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/v1/reservations/"),
                HttpMethod.GET, entity, String.class);

       // String expected = "{\"id\":\"Course1\",\"name\":\"Spring\",\"description\":\"10 Steps\"}";

        //JSONAssert.assertEquals(String.valueOf(List.class), response.getBody(), false);
       // assertTrue(response.getBody().contains());
    }
    @Test
    public void addReservation() {

        Reservation reservation = new Reservation(1L, 1L, 100,1L);

        HttpEntity<Reservation> entity = new HttpEntity<>(reservation, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/v1/reservations"),
                HttpMethod.POST, entity, String.class);

        HttpStatus actual = response.getStatusCode();//Headers().get(HttpHeaders.ST).get(0);

        assertTrue(actual.equals(HttpStatus.OK));

    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}*/
