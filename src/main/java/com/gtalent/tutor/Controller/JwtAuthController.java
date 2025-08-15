package com.gtalent.tutor.Controller;


import com.gtalent.tutor.Services.AuthService;
import com.gtalent.tutor.models.request.LoginRequest;
import com.gtalent.tutor.models.request.RegisterRequest;
import com.gtalent.tutor.responses.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
@CrossOrigin("*")
public class JwtAuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse>register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse>auth(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.auth(request));
    }


}
