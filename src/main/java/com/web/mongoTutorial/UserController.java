package com.web.mongoTutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {


    private final UserRepository userRepository;
    private final UserDAL userDAL;

    //initialize DAL object via constructor autowiring
    @Autowired
    UserController(UserRepository userRepository, UserDAL userDAL){
        this.userRepository = userRepository;
        this.userDAL = userDAL;
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
       /* userRepository.delete(new User());
        userRepository.deleteAll();
        userRepository.delete(new User());
        userRepository.deleteById("");
        userRepository.existsById("");
        userRepository.save(new User());
        userRepository.insert(new User());
        userRepository.findById("");*/
        return userRepository.findAll();
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addNewUsers(@RequestBody User user) {
        return userRepository.save(user);
    }
    //change method implementation to use DAL and hence MongoTemplate
    @RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
    public Object getAllUserSettings(@PathVariable String userId) {
        User user = userDAL.findOne(userId);
        if (user != null) {
            return user.getUserSettings();
        } else {
            return "User not found.";
        }
    }

    //change method implementation to use DAL and hence MongoTemplate
    @RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
    public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
        User user = userDAL.findOne(userId);
        if (user != null) {
            return user.getUserSettings().get(key);
        } else {
            return "User not found.";
        }
    }

    @RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.POST)
    public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
        User user = userDAL.findOne(userId);
        if (user != null) {
            user.getUserSettings().put(key, value);
            userRepository.save(user);
            return "Key added";
        } else {
            return "User not found.";
        }
    }
}
