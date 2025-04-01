package lk.ijse.dep13.auth.api;

import lk.ijse.dep13.auth.to.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.*;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserHttpController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/login", consumes = "application/json")
    public String login(@RequestBody User user){
        try(Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/dep13_auth_app",
                        "postgres", "psql");
            Statement stm = connection.createStatement()) {
            ResultSet rst = stm.executeQuery("SELECT * FROM \"user\" WHERE username='%s' AND password='%s'"
                    .formatted(user.username(), user.password()));
            if (rst.next()){
                return "logged in";
            }else{
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/logout")
    public void logout(){

    }
}
