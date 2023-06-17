package com.archeruu.traffic.service;

import com.archeruu.traffic.entity.User;
import com.archeruu.traffic.vo.RegisterVO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
* @author Archer
* @description 针对表【user】的数据库操作Service
* @createDate 2023-05-04 17:16:21
*/
@Service
public interface UserService extends IService<User> {
    void insertUser(RegisterVO registerVO);

    boolean uniqueUsername(String username);

    User vertifyUser(User user);
}
