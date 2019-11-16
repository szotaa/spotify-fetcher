package pl.szotaa.spotifyfetcher.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class AuthorizedRequestFactory {
    public static HttpEntity build(String accessToken) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);
        return new HttpEntity<>(headers);
    }
}
