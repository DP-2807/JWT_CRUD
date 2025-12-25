package Crud.CRUD.Controller;

import  Crud.CRUD.Module.User;
import Crud.CRUD.Repository.UserRepository;
import Crud.CRUD.Services.UserServices;
import Crud.CRUD.Util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserServices userServices;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/Register")
    public ResponseEntity<String> register(@RequestBody Map<String, String> bodys) {
        String email = bodys.get("email");
        String password = passwordEncoder.encode(bodys.get("password"));

        if (userRepository.findByemail(email).isPresent()) {
            return new ResponseEntity<>("Email Already Exists", HttpStatus.CONFLICT);
        }
        userServices.createUser(User.builder().email(email).password(password).build());
            return new ResponseEntity<>(" New Email Created", HttpStatus.CREATED);
    }
    @PostMapping("/Login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        var useroptional = userRepository.findByemail(email);
        if (useroptional.isEmpty()){
            return new ResponseEntity<>(" inavalid User Id", HttpStatus.UNAUTHORIZED);
        }
        User users = useroptional.get();
        if (!passwordEncoder.matches(password, users.getPassword())){
            return new ResponseEntity<>("invalid user",HttpStatus.UNAUTHORIZED);
        }
        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token",token));
    }
}