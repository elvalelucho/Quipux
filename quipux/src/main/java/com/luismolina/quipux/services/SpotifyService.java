package com.luismolina.quipux.services;

import com.luismolina.quipux.models.SpotifyTokenRequest;
import com.luismolina.quipux.models.SpotifyTokenResponse;
import io.jsonwebtoken.io.Encoders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class SpotifyService {

    private SpotifyTokenResponse getSpotifyAccessToken(){
        RestClient restClient = RestClient.builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory())
                .baseUrl("https://accounts.spotify.com/api/token")
                .defaultHeader("Authorization", "Basic " + Encoders.BASE64.encode("98aeab5d08c14e76a7e50b48100673fb:bc3dea58c21f42fdb0e59fe67503abad".getBytes(StandardCharsets.UTF_8)))
                .build();

        ResponseEntity<SpotifyTokenResponse> result = restClient.post()
                                            .body(new SpotifyTokenRequest())
                                            .retrieve()
                                            .toEntity(SpotifyTokenResponse.class);

        return result.getBody();
    }

    private List<String> getSpotifyGenres(){

        SpotifyTokenResponse spotifyTokenResponse = getSpotifyAccessToken();

        RestClient restClient = RestClient.builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory())
                .baseUrl("https://api.spotify.com/v1/recommendations/available-genre-seeds")
                .defaultHeader("Authorization", "Bearer " + spotifyTokenResponse.access_token)
                .build();

        ResponseEntity<List> result = restClient.post()
                .body(new SpotifyTokenRequest())
                .retrieve()
                .toEntity(List.class);

        return result.getBody();
    }
}
