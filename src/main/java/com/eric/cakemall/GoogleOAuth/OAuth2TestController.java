package com.eric.cakemall.GoogleOAuth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2TestController {

    @GetMapping("/api/test/oauth2/start")
    public String startOAuthTest() {
        return "<a href='/oauth2/authorization/google'>點擊這裡開始 Google 登入測試</a>";
    }
}
