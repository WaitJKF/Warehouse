package DataBaseService;
/*
*
* 封装一个用户表操作接口
* */
import java.util.List;
import java.util.Map;

/**
 * Created by 93202 on 2018-04-15.
 */

public interface UserService {
    public boolean addUser(String [] strings);
    public boolean delUser(Object[] objects);
    public boolean update(Object[] objects);
    public Map<String, String > getOne(String[] name);
    public List<Map<String ,String >> getAll(String [] objects);
}
