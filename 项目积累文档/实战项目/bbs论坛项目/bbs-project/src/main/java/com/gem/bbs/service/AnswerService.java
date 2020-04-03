package com.gem.bbs.service;

import com.gem.bbs.entity.Answer;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: jzhang
 * @WX: 15250420158
 * @Date: 2020/2/13 15:28
 * @Description: 回复服务层接口
 */
public interface AnswerService {
    /**
     * 根据问题id查询回复列表
     */
    List<Answer> findListByQuestionId(Integer id);

    /**
     * 保存问题
     */
    void save(Answer answer,HttpSession session);
}
