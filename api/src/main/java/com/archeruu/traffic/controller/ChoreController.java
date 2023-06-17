package com.archeruu.traffic.controller;

import cn.hutool.extra.mail.MailUtil;
import com.archeruu.ai.utils.ChatApiLocalProxyUtil;
import com.archeruu.core.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 杂类接口
 *
 * @author Archer
 */
@RestController
@RequestMapping("/chore")
public class ChoreController {

    @GetMapping("/sendMe")
    public ResultVO<?> sendMe(@RequestParam String message) {
        MailUtil.send("16607043480@163.com", "执勤无忧用户反馈", message, false);
        return ResultVO.ok();
    }

    @GetMapping("/getTag")
    public ResultVO<?> getTag(@RequestParam String content) {
        String prompt = "接下来我会告诉你的我任务内容，我的需求是标签总结，你需要分析我这段内容然后总结为一个标签，注意标签只能是一个两个字的中文词！比如：”执勤“，你只需要回复标签内容，不用输出其他，我的内容是：" + content;
        String res = ChatApiLocalProxyUtil.chatApi(prompt);
        return ResultVO.ok(res);
    }
}
