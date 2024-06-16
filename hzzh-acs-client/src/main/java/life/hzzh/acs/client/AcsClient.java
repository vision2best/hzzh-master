package life.hzzh.acs.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import life.hzzh.acs.Client;
import life.hzzh.acs.dto.request.UserRequest;
import life.hzzh.acs.dto.response.UserResponse;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public class AcsClient implements Client {
    private final OAuth2Client oauth2Client;
    private String accessToken;
    private final String baseUrl;
    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public AcsClient(OAuth2Client oauth2Client, String baseUrl) {
        this.oauth2Client = oauth2Client;
        this.baseUrl = baseUrl;
        this.httpClient = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public void authenticate(String username, String password) throws Exception {
        this.accessToken = oauth2Client.getAccessToken(username, password);
    }

    public void createUser(UserRequest userRequest) throws IOException {
        String url = baseUrl + "/users";
        String userJson = objectMapper.writeValueAsString(userRequest);

        RequestBody body = RequestBody.create(userJson, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        }
    }

    public UserResponse getUser(String userId) throws IOException {
        String url = baseUrl + "/users/" + userId;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            if (Objects.isNull(response.body())) {
                throw new NullPointerException("Unexpected code " + response);
            }
            String responseBody = response.body().string();
            return objectMapper.readValue(responseBody, UserResponse.class);
        }
    }

}
