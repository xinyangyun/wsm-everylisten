package com.example.wsmeverylisten.service;

import com.example.wsmeverylisten.annotation.Metrics;
import com.example.wsmeverylisten.entity.UserEntity;

//@Service
//@Slf4j
public class UserService {

//    @Autowired
//    private UserRepository userRepository;

//    @Transactional
    @Metrics //启用方法监控
    public void createUser(UserEntity entity) {
//        userRepository.save(entity);
        if (entity.getName().contains("test"))
            throw new RuntimeException("invalid username!");
    }

//    public int getUserCount(String name) {
//        return userRepository.findByName(name).size();
//    }



}

