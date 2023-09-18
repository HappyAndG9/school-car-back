package happy.schoolcarback.utils;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 木月丶
 * @Description 搜索工具
 */
public class SearchUtils<T> {
    //模糊查询
    public List<T> fuzzyQuery(List<T> list, String type, String key) throws IllegalAccessException {
        ArrayList<T> arrayList = new ArrayList<>();
        for (T t:list){
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (type == null){
                    if (field.get(t) != null && field.get(t).toString().contains(key)){
                        arrayList.add(t);
                        break;
                    }
                }else {
                    if (type.equals(field.getName())){
                        if (field.get(t) != null && field.get(t).toString().contains(key)){
                            arrayList.add(t);
                        }
                    }
                }
            }
        }
        return arrayList;
    }
}
