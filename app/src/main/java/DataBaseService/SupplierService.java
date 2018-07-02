package DataBaseService;
/*
*
* 封装一个供应商表操作接口
* */
import java.util.List;
import java.util.Map;

/**
 * Created by 93202 on 2018-04-26.
 */

public interface SupplierService {
    public boolean add(Object[] objects);
    public boolean del(Object[] objects);
    public boolean update(Object[] objects);
    public Map<String,String> getOne(String[] strings);
    public List<Map<String, String>> getAll(String[] strings);
}
