package com.archeruu.traffic.controller;

import com.archeruu.core.vo.ResultVO;
import com.archeruu.traffic.entity.User;
import com.archeruu.traffic.service.UserService;
import com.archeruu.traffic.vo.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户接口
 *
 * @author Archer
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/insertUser")
    public ResultVO<?> insertUser(@RequestBody RegisterVO registerVO) {
        userService.insertUser(registerVO);
        return ResultVO.ok();
    }

    @PostMapping("/updateUser")
    public ResultVO<?> updateUser(@RequestBody User user) {
        String password = userService.getById(user.getId()).getPassword();
        user.setPassword(password);
        userService.updateById(user);
        return ResultVO.ok();
    }

    @GetMapping("/getUserList")
    public ResultVO<?> getUserList() {
        List<User> userList = userService.list();
        return ResultVO.ok(userList);
    }

    @GetMapping("/getUserById")
    public ResultVO<?> getUserById(@RequestParam("id") Integer id) {
        User user = userService.getById(id);
        user.setPassword("******");
        return ResultVO.ok(user);
    }

    @PostMapping ("/vertifyUser")
    public ResultVO<?> vertifyUser(@RequestBody User user) {
        User res = userService.vertifyUser(user);
        return ResultVO.ok(res);
    }

}
