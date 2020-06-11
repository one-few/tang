package net.ttcxy.dao;

import net.ttcxy.entity.UserDto;

/**
 * 用户
 * @author huanglei
 */
public interface UserDao {

    /**
     * 通过邮箱查询用户是否存在
     */
    int selectEmailIsTrue(String mail);

    /**
     * 通过username查询用户是否存在
     */
    int selectUsernameIsTrue(String username);

    /**
     * 查询用户详细信息
     */
    UserDto selectUserByName(String username);

    //TODO 通过用户ID 查询这个用户用于的权限





}