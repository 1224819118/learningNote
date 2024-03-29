package com.gem.bbs.service;

import com.gem.bbs.entity.Question;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: jzhang
 * @WX: 15250420158
 * @Date: 2020/2/13 10:46
 * @Description:
 */
public interface QuestionService {

    /**
     * 根据主键查询问题
     */
    Question selectOne(Integer id);
    /**
     * 保存问题
     */
    void save(Question question,HttpSession session);

    /**
     * 获取总页数
     */
    Integer getTotalPage(Integer pageCount,String title);

    /**
     * 获取记录数
     */
    List<Question> findAll(Integer currentPage, Integer pageCount,String title);
}
