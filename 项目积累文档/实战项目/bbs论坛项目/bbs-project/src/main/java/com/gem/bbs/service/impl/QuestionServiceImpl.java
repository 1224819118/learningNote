package com.gem.bbs.service.impl;

import com.gem.bbs.entity.Question;
import com.gem.bbs.entity.User;
import com.gem.bbs.mapper.QuestionMapper;
import com.gem.bbs.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @Author: jzhang
 * @WX: 15250420158
 * @Date: 2020/2/13 10:47
 * @Description:
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public Question selectOne(Integer id) {
        return questionMapper.selectOneByPrimaryKey(id);
    }

    @Override
    public void save(Question question,HttpSession session) {
        question.setCreatetime(new Date());
        User user = (User) session.getAttribute("user");
        question.setUserId(user.getId());//记录当前登录用户信息
        questionMapper.insertQuestion(question);
    }

    @Override
    public Integer getTotalPage(Integer pageCount, String title) {
        if (title == null) title = "";
        Integer total = questionMapper.getTotal(title);
        return (total - 1) / pageCount + 1;
    }

    @Override
    public List<Question> findAll(Integer currentPage, Integer pageCount, String title) {
        return questionMapper.selectAll((currentPage - 1) * pageCount,pageCount,title);
    }
}
