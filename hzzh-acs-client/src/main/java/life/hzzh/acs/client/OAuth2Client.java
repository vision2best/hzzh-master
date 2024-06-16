package life.hzzh.acs.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import life.hzzh.acs.Client;
import life.hzzh.acs.dto.request.OAuthRequest;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public class OAuth2Client implements Client {
    private final String clientId;
    private final String clientSecret;
    private final String authServerUrl;
    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public OAuth2Client(String clientId, String clientSecret, String authServerUrl) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authServerUrl = authServerUrl;
        this.httpClient = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public String getAccessToken(String username, String password) throws Exception {
        String json = objectMapper.writeValueAsString(new OAuthRequest("password", clientId, clientSecret, username, password));
        RequestBody requestBody = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(authServerUrl + "/oauth/token")
                .post(requestBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            if (Objects.isNull(response.body())) {
                throw new NullPointerException("Unexpected code " + response);
            }
            String responseBody = response.body().string();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            return jsonNode.get("accessToken").asText();
        }
    }

}
