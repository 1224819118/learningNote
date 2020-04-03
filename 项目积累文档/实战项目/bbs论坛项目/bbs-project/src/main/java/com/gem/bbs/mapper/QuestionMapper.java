package com.gem.bbs.mapper;

import com.gem.bbs.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: jzhang
 * @WX: 15250420158
 * @Date: 2020/2/13 10:42
 * @Description: 问题接口
 */
public interface QuestionMapper {
    /**
     * 保存问题
     */
    void insertQuestion(Question question);


    /**
     * 查询所有问题
     */
    List<Question> selectAll(@Param("beginPage") Integer beginPage, @Param("pageCount") Integer pageCount, @Param("title") String title);

    /**
     * 查询记录总数
     */
    Integer getTotal(@Param("title") String title);

    /**
     * 根据主键查询问题
     */
    Question selectOneByPrimaryKey(Integer id);
}
