package Switter.Controller;

import Switter.Domain.Message;
import Switter.Domain.User;
import Switter.Repose.MessageRepose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepose messageRepose;

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("main")
    public String main(Map<String, Object> model){
        Iterable<Message> messages = messageRepose.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map <String, Object> model
    ){
        //Сохраняем сообщение
        Message message = new Message(text, tag, user);

        messageRepose.save(message);

        //Отдаем сообщение пользователю
        Iterable<Message> messages = messageRepose.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter (
            @RequestParam String filter,
            Map <String, Object> model
    ) {
        Iterable <Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepose.findByTag(filter);
        } else {
            messages = messageRepose.findAll();
        }

        model.put("messages", messages);
        return "main";
    }
}
