package com.bde.flix.security;

import com.bde.flix.model.entity.userman.User;
import com.bde.flix.model.repository.AccountRepository;
import com.bde.flix.security.Account.AccountDetails;
import com.bde.flix.security.jwt.JwtUtils;
import com.bde.flix.security.payloads.*;
import com.bde.flix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/authentication")
public class AuthController {
    @Autowired
    EmailSenderService sender;
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    AccountRepository accRepo;
    @Autowired
    PasswordEncoder passEnc;
    @Autowired
    JwtUtils jwtUtl;
    @Autowired
    UserService usrSrvc;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody SignInRecord data){

        String email = data.email();
        String password = data.password();

        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(auth);
        AccountDetails accDetails = (AccountDetails) auth.getPrincipal();
        ResponseCookie jwtCookie = jwtUtl.generateJwtCookie(accDetails);

        //TODO: remove after deployment
        List<String> roles = accDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());


        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new SignInResponse(accDetails.getEmail(), roles));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignInRecord data) {


        String email = data.email();
        String password = data.password();


        if (accRepo.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("Email has been taken!");
        }

        String jwtCookie = jwtUtl.generateTokenFromEmail(email, password);

        //TODO: switch to email verification after deployment
        try {
            sender.sendEmail(email, "Confirm email" , String.format("confirmation link:  http://localhost:8080/api/authentication/verifyEmail?ver=%s", jwtCookie));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }


        return ResponseEntity.ok().body(new RegisterResponse(email,"Email sent"));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtl.getCleanJwtCookie();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Session killed");

    }

    @GetMapping("/verifyEmail")
    public ResponseEntity<?> verifyEmail(@RequestParam(required = true) String ver){

        try {
            jwtUtl.validateJwtToken(ver);
        }
        catch (Exception e){
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }

        String jwt = jwtUtl.getUserNameFromJwtToken(ver);
        Set<String> set_passwd = Stream.of(jwt.trim().split("\\s*,\\s*"))
                .collect(Collectors.toSet());
        if (accRepo.existsByEmail(set_passwd.toArray()[1].toString())) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        User usr = usrSrvc.createuser(set_passwd.toArray()[1].toString(),passEnc.encode(set_passwd.toArray()[0].toString()));

        return ResponseEntity.ok().body("Email Verified");
    }

    @PostMapping("/sendReset")
    public ResponseEntity<?> resetPasswd(@RequestBody ForgottenEmail data) {


        String email = data.email();


        if (!accRepo.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("Email does not exist");
        }


        String passwd = getAlphaNumericString(16);

        String jwtCookie = jwtUtl.generateTokenFromEmail(email, passwd);

        try {
            sender.sendEmail(email, "Reset Password" , String.format("temporary password: %s\n " +
                    "click here to reset: http://localhost:8080/api/authentication/resetPasswd?ver=%s", passwd, jwtCookie));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }


        return ResponseEntity.ok().body(new RegisterResponse(email,"Email sent"));
    }
    @GetMapping("/resetPasswd")
    public ResponseEntity<?> resetPasswd(@RequestParam(required = true) String ver){

        try {
            jwtUtl.validateJwtToken(ver);
        }
        catch (Exception e){
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }

        String jwt = jwtUtl.getUserNameFromJwtToken(ver);
        Set<String> set_passwd = Stream.of(jwt.trim().split("\\s*,\\s*"))
                .collect(Collectors.toSet());

        usrSrvc.changePasswd(set_passwd.toArray()[1].toString(),
                passEnc.encode(set_passwd.toArray()[0].toString()));

        return ResponseEntity.ok().body("Password set to temporary password");
    }
    @PostMapping("/passwdChange")
    public ResponseEntity<?> changePasswd(@RequestBody PasswdChangeRecord data) {

        if (!accRepo.existsByEmail(data.email())) {
            return ResponseEntity.badRequest().body("Email does not exist");
        }
        try {
            jwtUtl.validateJwtToken(data.token());
        }
        catch (Exception e){
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }

        String accName = jwtUtl.getUserNameFromJwtToken(data.token());
        if(!accName.equals(data.email())) {
            System.out.println("*************");
            for(int i=0; i<25; i++){
                System.out.println("*");
            }
            System.out.println(String.format("We have been attacked:\nmail: %s\ntoken: %s\n", data.email(), accName));
            for(int i=0; i<25; i++){
                System.out.println("*");
            }
            System.out.println("*************");

            return ResponseEntity.unprocessableEntity().body("This attack has been noticed. GL HF loser");

        }
        usrSrvc.changePasswd(data.email(),passEnc.encode(data.passwd()));
        return ResponseEntity.ok().body("Password changed");
    }

    static String getAlphaNumericString(int n)
    {

        // choose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

}
