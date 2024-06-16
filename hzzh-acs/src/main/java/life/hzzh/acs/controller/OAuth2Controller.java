package life.hzzh.acs.controller;


import life.hzzh.acs.dto.request.OAuthRequest;
import life.hzzh.acs.dto.response.OAuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Slf4j
public class OAuth2Controller {

    @PostMapping("/oauth/token")
    public ResponseEntity<OAuthResponse> accessToken(@RequestBody OAuthRequest request) {
        return ResponseEntity.ok(OAuthResponse.builder().accessToken("hello").build());
    }
}
