package happy.schoolcarback.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import happy.schoolcarback.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 木月丶
 * @since 2023-02-05
 */
public interface RecordService extends IService<Record> {
    //分页获取全部进出校门记录信息
    Page<Record> getRecords(Integer pageNum, Integer pageSize);
}
