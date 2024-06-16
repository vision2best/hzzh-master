package life.hzzh.gateway.controller;

import life.hzzh.acs.client.OAuth2Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Slf4j
public class AcsController {

    @GetMapping("/accesstoken")
    public ResponseEntity<String> accesstoken() throws Exception {
        OAuth2Client oAuth2Client = new OAuth2Client("appId", "1111", "http://localhost:8080");
        String accessToken = oAuth2Client.getAccessToken("15157183664", "123456");
        return ResponseEntity.ok(accessToken);
    }

}
