package com.archeruu.traffic.mapper;

import com.archeruu.traffic.entity.TaskInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Archer
* @description 针对表【task_info(任务信息表)】的数据库操作Mapper
* @createDate 2023-05-06 21:38:11
* @Entity com.archeruu.traffic.entity.TaskInfo
*/
@Mapper
public interface TaskInfoMapper extends BaseMapper<TaskInfo> {

}




