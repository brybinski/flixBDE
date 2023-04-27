package com.bde.flix.security;

import com.bde.flix.security.payloads.RegisterResponse;
import com.bde.flix.service.UserService;
import com.bde.flix.model.entity.userman.User;
import com.bde.flix.security.Account.AccountDetails;
import com.bde.flix.security.payloads.SignInResponse;
import com.bde.flix.security.jwt.JwtUtils;
import com.bde.flix.model.repository.AccountRepository;
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
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/authentication")
public class AuthController {

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


    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestParam(required = true) String email,
                                      @RequestParam(required = true) String password){

        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(auth);
        AccountDetails accDetails = (AccountDetails) auth.getPrincipal();
        ResponseCookie jwtCookie = jwtUtl.generateJwtCookie(accDetails);

        List<String> roles = accDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());


        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new SignInResponse(accDetails.getId(), accDetails.getEmail(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestParam(required = true) String email,
                                          @RequestParam(required = true) String password) {
        if (accRepo.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("Email has been taken!");
        }

        User usr = usrSrvc.createuser(email,passEnc.encode(password));


        return ResponseEntity.ok().body(new RegisterResponse(usr.getId(),usr.getEmail(),"account registered"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtl.getCleanJwtCookie();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Session killed");

    }
}
