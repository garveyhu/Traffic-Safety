package com.archeruu.traffic.controller;

import com.archeruu.core.vo.ResultVO;
import com.archeruu.traffic.entity.TaskInfo;
import com.archeruu.traffic.service.TaskInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 任务信息接口
 *
 * @author Archer
 */
@RestController
@RequestMapping("/taskInfo")
public class TaskInfoController {

    @Autowired
    private TaskInfoService taskInfoService;

    @PostMapping("/insertTask")
    public ResultVO<?> insertTask(@RequestBody TaskInfo taskInfo) {
        return ResultVO.ok(taskInfoService.save(taskInfo));
    }

    @PostMapping("/updateTaskById")
    public ResultVO<?> updateTaskById(@RequestBody TaskInfo taskInfo) {
        if (taskInfoService.getById(taskInfo.getId()) == null) {
            return ResultVO.ok(taskInfoService.save(taskInfo));
        } else {
            return ResultVO.ok(taskInfoService.updateById(taskInfo));
        }
    }

    @GetMapping("/deleteTaskById")
    public ResultVO<?> deleteTaskById(@RequestParam String id) {
        return ResultVO.ok(taskInfoService.removeById(id));
    }

    @GetMapping("/getTaskById")
    public ResultVO<?> getTaskById(@RequestParam String id) {
        return ResultVO.ok(taskInfoService.getById(id));
    }

    @GetMapping("/getTaskList")
    public ResultVO<?> getTaskList() {
        // 创建时间排序
        LambdaQueryWrapper<TaskInfo> queryWrapper = new LambdaQueryWrapper<TaskInfo>();
        queryWrapper.orderByAsc(TaskInfo::getCreateTime);
        return ResultVO.ok(taskInfoService.list(queryWrapper));
    }

}
