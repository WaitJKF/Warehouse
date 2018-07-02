package DataBaseService;
/*
*
* 封装一个商品表操作接口
* */
import java.util.List;
import java.util.Map;

/**
 * Created by 93202 on 2018-04-16.
 */

public interface ProductService {
    public boolean addProduct(Object[] objects);
    public boolean delProduct(Object[] objects);
    public boolean update(Object[] objects);
    public Map<String ,String> getOne(String[] strings);
    public List<Map<String ,String >> getAll(String[] strings);
}
