package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    /**
     * 查询帖子（无参数时表示查询所有---动态sql）
     * @param userId
     * @param offset 起始行行号
     * @param limit 每一页最多显示的数据
     * @return
     */
    List<DiscussPost> selectDiscussPosts(@Param("userId") int userId, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询出表中数据的个数---动态sql
     * @Param("userId") 用于给起别名 注：当时动态条件时（在if中使用），且方法内仅有一个参数时，必须加上此注解
     */
    int selectDiscussPostRows(@Param("userId") int userId);

}
