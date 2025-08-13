package com.gtalent.tutor.Controller;

import com.gtalent.tutor.Services.UserService;
import com.gtalent.tutor.models.User;
import com.gtalent.tutor.models.request.LoginRequest;
import com.gtalent.tutor.models.request.RegisterRequest;
import com.gtalent.tutor.repositories.UserRepository;
import com.gtalent.tutor.responses.RegisterUserResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/session")
@CrossOrigin("*")
public class SessionAuthController {
    private final UserService userService;
    private final UserRepository userRepository;



    @Autowired
    public SessionAuthController(UserService userService,UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<User>login(@RequestBody LoginRequest request, HttpSession session){
        Optional<User>user = userService.findByUsernameAndPassword(request.getUsername(),request.getPassword());
        if(user.isPresent()){
            session.setAttribute("userId",user.get().getId());
            return ResponseEntity.ok(user.get());
        }
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(HttpSession session){
        Integer userId =(Integer) session.getAttribute("userId");
        if(userId == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Optional<User> user = userRepository.findById(userId);
        return ResponseEntity.ok(user.get());
    }
    @PostMapping("/logout")
    public ResponseEntity<Void>logout(HttpSession session){
        session.invalidate();
        return  ResponseEntity.ok().build();
    }

    @PostMapping("/register")

    public ResponseEntity<RegisterUserResponse> registerAccount(@RequestBody RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
        //避免ID被更改,啟用自動新增ID
        User newUser =new User();//建立新用戶
        newUser.setId(null); //自動新增ID
        newUser.setUsername(request.getUsername());//自訂使用者名稱
        newUser.setEmail(request.getEmail());//自訂Email
        newUser.setPassword(request.getPassword()); // 自訂密碼

        User savedUser = userRepository.save(newUser);//儲存新用戶資料



        RegisterUserResponse response = new RegisterUserResponse(
                savedUser.getUsername(),
                savedUser.getEmail()
                //我想要回傳的東西(只有使用者名稱跟信箱)
                //日後可新增運用信箱找回密碼
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);//已新增完成
    }
}
