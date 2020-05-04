package jin.chen.service;

import jin.chen.pojo.Admin;
import jin.chen.pojo.Users;
import jin.chen.utils.PagedResult;

public interface UsersService {
    /**
     * 查询所有用户
     * @param users
     * @param page
     * @return
     */
    public PagedResult queryUsersList(Users users, Integer page, Integer pageSize);

    /**
     * 注册时，先查询用户是否存在
     * @param username
     * @return
     */
    public boolean queryAdminExist(String username);

    /**
     * 新增用户
     */
    public void addAdmin(Admin admin);

    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    public  boolean queryAdminSuccess(String username, String password);
}
