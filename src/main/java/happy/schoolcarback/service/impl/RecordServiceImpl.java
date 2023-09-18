package happy.schoolcarback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import happy.schoolcarback.entity.Record;
import happy.schoolcarback.mapper.RecordMapper;
import happy.schoolcarback.service.RecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 木月丶
 * @since 2023-02-05
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

    @Resource
    RecordMapper recordMapper;

    @Override
    public Page<Record> getRecords(Integer pageNum, Integer pageSize) {
        String rule = "transit_time";
        return this.getList(pageNum, pageSize, rule);
    }

    private Page<Record> getList(Integer pageNum , Integer PageSize , String rule){
        Page<Record> page = new Page<>(pageNum, PageSize);
        QueryWrapper<Record> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc(rule);
        return recordMapper.selectPage(page, wrapper);
    }
}
