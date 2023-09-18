package happy.schoolcarback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import happy.schoolcarback.entity.Suggest;
import happy.schoolcarback.mapper.SuggestMapper;
import happy.schoolcarback.service.SuggestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class SuggestServiceImpl extends ServiceImpl<SuggestMapper, Suggest> implements SuggestService {

    @Resource
    SuggestMapper suggestMapper;

    private String rule = "suggest_time";

    @Override
    public Page<Suggest> getSuggests(Integer pageNum, Integer pageSize) {
        return this.getList(pageNum, pageSize, rule);
    }



    private Page<Suggest> getList(Integer pageNum , Integer PageSize , String rule){
        Page page = new Page(pageNum, PageSize);
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.orderByDesc(rule);
        return suggestMapper.selectPage(page, wrapper);
    }
}
