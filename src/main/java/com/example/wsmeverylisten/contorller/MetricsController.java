package com.example.wsmeverylisten.contorller;


import com.example.wsmeverylisten.entity.UserEntity;
import com.example.wsmeverylisten.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController //自动进行监控
@RequestMapping("metricstest")
public class MetricsController {

//    @Autowired
    private UserService userService;

    @GetMapping("transaction")
    public int transaction(@RequestParam("name") String name) {
        try {
            userService.createUser(new UserEntity(name));
        } catch (Exception ex) {
            log.error("create user failed because {}", ex.getMessage());
        }

//        return userService.getUserCount(name);

        return 1;
    }

}
