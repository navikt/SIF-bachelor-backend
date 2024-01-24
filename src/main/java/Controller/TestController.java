package Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@EnableWebMvc
@RestController
public class TestController {

   @GetMapping("/api/test")
    public String test(){
       return "ok";
    }
}
