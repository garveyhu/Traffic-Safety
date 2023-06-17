package com.archeruu.traffic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.archeruu.traffic.entity.Role;
import com.archeruu.traffic.service.RoleService;
import com.archeruu.traffic.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
 * 针对表【role】的数据库操作
 *
 * @author Archer
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

}




