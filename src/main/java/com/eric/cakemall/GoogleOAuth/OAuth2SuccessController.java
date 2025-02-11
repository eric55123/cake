package com.eric.cakemall.GoogleOAuth;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2SuccessController {

    @GetMapping("/oauth2/success")
    public String oauth2Success(@AuthenticationPrincipal OAuth2User oauth2User) {
        if (oauth2User == null) {
            return "OAuth2User 為 null，驗證失敗！";
        }

        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        return "登入成功！歡迎 " + name + "，您的電子郵件為：" + email;
    }
}
