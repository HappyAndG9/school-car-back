package happy.schoolcarback.security.handler;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import happy.schoolcarback.entity.Administrator;
import happy.schoolcarback.mapper.AdministratorMapper;
import happy.schoolcarback.result.ResponseUtil;
import happy.schoolcarback.result.Result;
import happy.schoolcarback.security.token.JwtTokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Author 木月丶
 * @Description 登录成功处理器
 */
@Service
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Resource
    JwtTokenProvider provider;

    @Resource
    AdministratorMapper mapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String token = provider.creatToken(authentication);
        User admin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<Administrator> wrapper = new QueryWrapper<>();
        wrapper.eq("username", admin.getUsername());
        Administrator administrator = mapper.selectOne(wrapper);
        map.put("nickname",administrator.getNickName());
        map.put("token",token);
        ResponseUtil.out(response, Result.successWithData(map));
        //返回token给前端
        returnJson(response, authentication);
    }

    private void returnJson(HttpServletResponse response,Authentication authentication) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter()
                .println("{\"token\": \"" + provider.creatToken(authentication) + "\"}");
    }
}
