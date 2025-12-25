package Crud.CRUD.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
@GetMapping("/get")
    public String match(){
    return "Hello Dhina Praveen your Code is Working Fine !! ";
}
}
