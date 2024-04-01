package com.example.demo.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
   private String accessToken;
   private String refreshToken;

   public LoginResponse(String accessToken, String refreshToken) {
       this.accessToken = accessToken;
       this.refreshToken = refreshToken;
   }
}
