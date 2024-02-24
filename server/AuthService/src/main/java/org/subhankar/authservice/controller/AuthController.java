package org.subhankar.authservice.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.subhankar.authservice.config.CustomAuthenticationProvider;
import org.subhankar.authservice.config.JwtService;
import org.subhankar.authservice.integration.UserIntegration;
import org.subhankar.authservice.model.DO.Role;
import org.subhankar.authservice.model.DO.User;
import org.subhankar.authservice.model.DTO.*;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static javax.crypto.Cipher.SECRET_KEY;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CustomAuthenticationProvider authenticationManager;
    private final JwtService jwtProvider;
    private final UserIntegration userIntegration;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public ResponseEntity<Result> login(@RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
            Result Result=null;
            if(authentication.isAuthenticated()){
                User user = userIntegration.getUserByEmail(FetchUserDTO.builder().email(loginRequestDTO.getEmail()).build());
                String accessToken = jwtProvider.generateToken(user);
                Cookie cookie = new Cookie("token", accessToken);
                cookie.setMaxAge(3600);
                cookie.setSecure(true);
                cookie.setHttpOnly(true);
                cookie.setPath("/");

                response.addCookie(cookie);
                Result = Result.builder()
                        .code("200")
                        .message("User logged in successfully")
                        .data(accessToken)
                        .build();

            } else {
                throw new UsernameNotFoundException("invalid user request..!!");
            }


            return new ResponseEntity<>(Result, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            // Log the exception or relevant details
            System.out.println("Bad credentials provided for login: {}"+ e.getMessage());
            throw e;
        }catch (Exception e) {
            // Log the exception or relevant details
            System.out.println("Exception occurred while logging in: {}"+ e.getMessage());
            throw e;
        }

//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));

    }

    @PostMapping("/register")
    public ResponseEntity<Result> register(@RequestBody User userDO) {
        User user = User.builder()
                .name(userDO.getName())
                .roles(Set.of(Role.builder().name("USER").build()))
                .email(userDO.getEmail())
                .password(passwordEncoder.encode(userDO.getPassword()))
                .build();
        Result result = userIntegration.addUser(user);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @PostMapping("/logout")
    public ResponseEntity<Result> logout(HttpServletRequest request, HttpServletResponse response) {
        String token = null;
        String userEmail = null;

        if(request.getCookies() != null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("token")){
                    token = cookie.getValue();
                }
            }
        }

        if(token == null){
            Result result = Result.builder()
                    .code("401")
                    .message("Unauthorized")
                    .build();
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
        response.setHeader(HttpHeaders.SET_COOKIE, "token=; Max-Age=0; Path=/; HttpOnly; SameSite=None; Secure");
        Result result = Result.builder()
                .code("200")
                .message("User logged out successfully")
                .build();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/validate")
    public boolean validate(@PathVariable String token) {
        return jwtProvider.isTokenExpired(token);
    }

}