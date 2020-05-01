package net.ttcxy.tangtang.mapper;

import net.ttcxy.tangtang.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * @author ：HuangLei
 * @date ：2020/4/18 0018 15:17
 */
public interface CommentMapper {

    /**
     * 添加评论
     * @param comment 评论
     * @return
     */
    @Insert("insert into blog_comment (id,blog_id,status,user_id,content,create_date)" +
            "values(#{id},#{blogId},#{status},#{userId},#{content},#{createDate})")
    int insertComment(Comment comment);

    /**
     * 删除评论 commentId
     * @param id
     * @return
     */
    @Delete("delete from blog_comment where id = #{id}")
    int deleteComment(String id);

    /**
     * 通过BlogId 查询评论
     * @param blogId
     * @return
     */
    List<Comment> selectComment(String blogId);
}