package jin.chen.mapper;

import jin.chen.pojo.Users;
import jin.chen.pojo.UsersExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {
    int countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(String id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}