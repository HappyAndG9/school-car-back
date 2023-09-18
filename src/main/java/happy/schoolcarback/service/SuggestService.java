package happy.schoolcarback.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import happy.schoolcarback.entity.Suggest;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 木月丶
 * @since 2023-02-05
 */
public interface SuggestService extends IService<Suggest> {
    //分页获取全部建议信息
    Page<Suggest> getSuggests(Integer pageNum, Integer pageSize);
}
