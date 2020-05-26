package net.ttcxy.tangtang.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import net.ttcxy.tangtang.dao.BlogDao;
import net.ttcxy.tangtang.entity.BlogDto;
import net.ttcxy.tangtang.mapper.BlogMapper;
import net.ttcxy.tangtang.mapper.FavoriteMapper;
import net.ttcxy.tangtang.mapper.LikeDataMapper;
import net.ttcxy.tangtang.mapper.PageViewMapper;
import net.ttcxy.tangtang.model.*;
import net.ttcxy.tangtang.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * BlogService impl
 * @author huanglei
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    AuthDetailsImpl authDetails;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private PageViewMapper pageViewMapper;

    @Autowired
    private LikeDataMapper likeMapper;

    @Override
    public List<BlogDto> selectBlog(Integer pag) {
        PageHelper.startPage(pag, 10);
        return blogDao.selectBlog();
    }

    @Override
    public List<BlogDto> search(String title, Integer page) {
        PageHelper.startPage(page, 10);
        return blogDao.search(title);
    }

    @Override
    public List<BlogDto> searchByUsername(String username) {
        return blogDao.searchByUsername(username);
    }

    @Override
    public int insertBlog(Blog blog) {
        return blogMapper.insertSelective(blog);
    }

    @Override
    public int updateBlog(Blog blog) {
        String blogId = blog.getId();
        String userId = authDetails.getUserId();

        BlogExample blogExample = new BlogExample();
        blogExample.createCriteria().andIdEqualTo(blogId).andUserIdEqualTo(userId);

        return blogMapper.updateByExampleSelective(blog, blogExample);
    }

    @Override
    public int deleteBlog(String id) {
        return blogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int like(String userId, String blogId) {

        LikeData likeData = new LikeData();
        likeData.setBlogId(blogId);
        likeData.setUserId(userId);
        likeData.setCreateDate(new Date());
        try {
            return likeMapper.insertSelective(likeData);
        } catch (Exception e) {
            LikeDataExample likeExample = new LikeDataExample();
            likeExample.createCriteria().andBlogIdEqualTo(blogId).andUserIdEqualTo(userId);
            likeMapper.deleteByExample(likeExample);
            return 0;

        }
    }

    @Override
    public BlogDto selectBlogById(String id) {
        PageView pageView = new PageView();
        pageView.setId(IdUtil.fastSimpleUUID());
        pageView.setBlogId(id);
        pageView.setUserId(authDetails.getUserId());
        pageView.setCreateDatetime(new Date());
        pageViewMapper.insertSelective(pageView);

        return blogDao.selectBlogById(id);
    }

    @Override
    public Blog selectByPrimaryId(String id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    @Override
    public long selectLike(String userId, String blogId) {
        LikeDataExample likeExample = new LikeDataExample();
        likeExample.createCriteria().andBlogIdEqualTo(blogId).andUserIdEqualTo(userId);

        return likeMapper.countByExample(likeExample);
    }

    @Override
    public long selectFavorite(String userId, String blogId) {
        FavoriteExample favoriteExample = new FavoriteExample();
        favoriteExample.createCriteria().andBlogIdEqualTo(blogId).andUserIdEqualTo(userId);
        return favoriteMapper.countByExample(favoriteExample);
    }

    @Override
    public List<BlogDto> selectLikeBlogs(String userId) {
        return blogDao.selectLikeBlogs(userId);
    }

    @Override
    public List<BlogDto> selectByUserFavorite(String username) {
        return blogDao.selectByUserFavorite(username);
    }

    @Override
    public int favorite(String userId, String blogId) {
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setBlogId(blogId);
        favorite.setCreateDate(new Date());
        try {
            return favoriteMapper.insertSelective(favorite);
        } catch (Exception e) {
            FavoriteExample favoriteExample = new FavoriteExample();
            favoriteExample.createCriteria().andBlogIdEqualTo(blogId).andUserIdEqualTo(userId);

            favoriteMapper.deleteByExample(favoriteExample);
            return 0;
        }
    }

    @Override
    public long selectBlogCount() {
        // todo 记录博客数量，需要优化性能
        return blogMapper.countByExample(new BlogExample());
    }
}
