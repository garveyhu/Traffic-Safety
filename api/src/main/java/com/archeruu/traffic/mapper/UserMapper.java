package com.archeruu.traffic.mapper;

import com.archeruu.traffic.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Archer
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-05-04 17:16:21
* @Entity com.archeruu.traffic.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




