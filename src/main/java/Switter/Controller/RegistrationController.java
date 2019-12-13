package Switter.Controller;

import Switter.Domain.Role;
import Switter.Domain.User;
import Switter.Repose.UserRepose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.CollectionTable;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepose userRepose;

    @GetMapping("/registration")
    public String registration (){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDb = userRepose.findByUsername(user.getUsername());

        if (userFromDb!= null){
            model.put("message", "User exsist");
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepose.save(user);
        return "redirect:/login";
    }
}
