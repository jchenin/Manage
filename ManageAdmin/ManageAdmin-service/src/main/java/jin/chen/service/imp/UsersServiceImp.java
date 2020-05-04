package jin.chen.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jin.chen.mapper.AdminMapper;
import jin.chen.mapper.UsersMapper;
import jin.chen.pojo.Admin;
import jin.chen.pojo.AdminExample;
import jin.chen.pojo.Users;
import jin.chen.pojo.UsersExample;
import jin.chen.service.UsersService;
import jin.chen.utils.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersServiceImp implements UsersService {



    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedResult queryUsersList(Users users, Integer page, Integer pageSize) {
        String username = "";
        String nickname = "";
        if(users != null){
            username = users.getUsername();
            nickname = users.getNickname();
        }
        //开始分页
        PageHelper.startPage(page, pageSize);
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        //模糊查询username
        if(username != null && !"".equals(username)){
            criteria.andUsernameLike("%" + username + "%");
        }
        //模糊查询nickname
        if(nickname != null && !"".equals(nickname)){
            criteria.andNicknameLike("%" + nickname + "%");
        }
        List<Users> list =  usersMapper.selectByExample(example);
        PageInfo<Users> info = new PageInfo<>(list);
        PagedResult result = new PagedResult();
        //当前页数
        result.setPage(page);
        //总页数
        result.setTotal(info.getPages());
        //总记录数
        result.setRecords(info.getTotal());
        //每行显示的内容
        result.setRows(list);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean queryAdminExist(String username) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<Admin> list = adminMapper.selectByExample(example);
        if(list != null && list.size() > 0){
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addAdmin(Admin admin) {
        adminMapper.insert(admin);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean queryAdminSuccess(String username, String password) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);
        List<Admin> list = adminMapper.selectByExample(example);
        if(list != null && list.size() > 0){
            return true;
        }
        return false;
    }
}
