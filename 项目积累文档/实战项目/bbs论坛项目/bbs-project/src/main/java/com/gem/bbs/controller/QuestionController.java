package com.gem.bbs.controller;

import com.gem.bbs.entity.Answer;
import com.gem.bbs.entity.Question;
import com.gem.bbs.service.AnswerService;
import com.gem.bbs.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: jzhang
 * @WX: 15250420158
 * @Date: 2020/2/13 10:48
 * @Description: 问题控制器
 */
@Controller
@RequestMapping("/ques")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;

    @RequestMapping("/form")
    public String form() {
        return "questionForm";
    }

    @RequestMapping("/save")
    public String save(Question question, HttpSession session) {
        questionService.save(question, session);
        return "redirect:/index";
    }

    /**
     * 获取问题详情内容
     */
    @RequestMapping("/detail")
    public String detail(Integer id, Model model) {
        Question question = questionService.selectOne(id);
        //获取该问题的回复
        List<Answer> answerList = answerService.findListByQuestionId(id);

        model.addAttribute("question",question);
        model.addAttribute("answerList",answerList);
        return "questionDetail";
    }
}
