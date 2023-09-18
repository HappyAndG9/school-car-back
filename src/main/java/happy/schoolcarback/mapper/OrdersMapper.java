package happy.schoolcarback.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import happy.schoolcarback.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import happy.schoolcarback.vo.OrdersVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 木月丶
 * @since 2023-02-05
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
    /**
     * @author 木月丶
     * @description 分页获取全部订单
     * @return List<OrdersVo>
     */
    @Select("select order_id, user_id, user_nick_name, order_number,serial_number, " +
                    "UNIX_TIMESTAMP(start_time)*1000 start_time, UNIX_TIMESTAMP(end_time)*1000 end_time, " +
                    "duration,amount, comment, state ,is_delete " +
                    "from `orders` " +
                    "where is_delete = 0 " +
                    "order by start_time DESC")
    IPage<OrdersVo> getOrderVoIPage(Page<OrdersVo> page);

    @Select("select order_id, user_id, user_nick_name, order_number,serial_number, " +
            "UNIX_TIMESTAMP(start_time)*1000 start_time, UNIX_TIMESTAMP(end_time)*1000 end_time, " +
            "duration,amount, comment, state ,is_delete " +
            "from `orders` " +
            "where is_delete = 0 " +
            "order by start_time DESC")
    List<OrdersVo> getOrderVoList();


    /**
     * @author 木月丶
     * @description 查询今日订单总数
     * @return int
     */
    @Select("SELECT COUNT(*) AS today_orders " +
            "FROM `orders` " +
            "WHERE DATE(start_time) = CURDATE()")
    int getTodayOrdersTotal();



    /**
     * @author 木月丶
     * @description 查询过去七天每一天的订单总数
     * @return List<Map<Date,Integer>>
     */
    @Select("SELECT DATE(d.date) AS DATE, IFNULL(COUNT(o.order_id), 0) AS total\n" +
            "FROM (\n" +
            "    SELECT CURDATE() - INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY AS DATE\n" +
            "    FROM (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9)  AS a\n" +
            "    CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8     UNION ALL SELECT 9) AS b\n" +
            "    CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8     UNION ALL SELECT 9) AS c\n" +
            ") AS d\n" +
            "LEFT JOIN `orders` AS o ON DATE(o.end_time) = DATE(d.date)\n" +
            "WHERE d.date >= CURDATE() - INTERVAL 6 DAY\n" +
            "GROUP BY DATE(d.date)\n" +
            "ORDER BY DATE(d.date) ASC")
    List<Map<Date,Integer>> getThePastSevenDaysOrdersTotal();

    /**
     * @author 木月丶
     * @description 获取上周周报数据（上周每天的日期，每天的订单总数量，每天的订单总金额）
     * @return List<Map<String,Object>>
     */
    @Select("SELECT dates.date, COUNT(o.order_id) AS total_orders, IFNULL(SUM(o.amount),0) AS total_amount\n" +
            "FROM (\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL (WEEKDAY(CURDATE())+7) DAY) + INTERVAL (a.a + (10 * b.a)) DAY AS DATE\n" +
            "    FROM (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a\n" +
            "    CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b\n" +
            ") AS dates\n" +
            "LEFT JOIN `orders` AS o ON DATE(o.end_time) = dates.date\n" +
            "WHERE dates.date >= DATE_SUB(DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY), INTERVAL 7 DAY)\n" +
            "  AND dates.date <= DATE_SUB(DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY), INTERVAL 1 DAY)\n" +
            "GROUP BY dates.date\n" +
            "ORDER BY dates.date ASC;")
    List<Map<String,Object>> getLastWeekReports();


    /**
     * @author 木月丶
     * @description 获取上上周周报数据（上上周每天的日期，每天的订单总数量，每天的订单总金额）
     * @return List<Map<String,Object>>
     */
    @Select("SELECT dates.date, COUNT(o.order_id) AS total_orders, IFNULL(SUM(o.amount),0) AS total_amount\n" +
            "FROM (\n" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL (WEEKDAY(CURDATE())+14) DAY) + INTERVAL (a.a + (10 * b.a)) DAY AS DATE\n" +
            "    FROM (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS a\n" +
            "    CROSS JOIN (SELECT 0 AS a UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) AS b\n" +
            ") AS dates\n" +
            "LEFT JOIN `orders` AS o ON DATE(o.end_time) = dates.date\n" +
            "WHERE dates.date >= DATE_SUB(DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY), INTERVAL 14 DAY)\n" +
            "  AND dates.date <= DATE_SUB(DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY), INTERVAL 8 DAY)\n" +
            "GROUP BY dates.date\n" +
            "ORDER BY dates.date ASC;")
    List<Map<String,Object>> getLastLastWeekReports();
}
