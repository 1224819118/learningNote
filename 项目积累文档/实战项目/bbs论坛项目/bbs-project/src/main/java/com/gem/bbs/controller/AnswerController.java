package com.gem.bbs.controller;

import com.gem.bbs.entity.Answer;
import com.gem.bbs.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author: jzhang
 * @WX: 15250420158
 * @Date: 2020/2/13 15:47
 * @Description: 回复控制器
 */
@Controller
@RequestMapping("/ans")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @RequestMapping("/save")
    public String save(Answer answer, HttpSession session) {
        answerService.save(answer,session);
        return "redirect:/ques/detail?id=" + answer.getQuestionId();
    }
}
