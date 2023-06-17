package com.archeruu.traffic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.archeruu.traffic.entity.TaskInfo;
import com.archeruu.traffic.service.TaskInfoService;
import com.archeruu.traffic.mapper.TaskInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author Archer
* @description 针对表【task_info(任务信息表)】的数据库操作Service实现
* @createDate 2023-05-06 21:38:11
*/
@Service
public class TaskInfoServiceImpl extends ServiceImpl<TaskInfoMapper, TaskInfo> implements TaskInfoService{

}




