package happy.schoolcarback.security.handler;

import happy.schoolcarback.security.exception.MyAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @Author 木月丶
 * @Description 登录失败处理器
 */
@Service
public class AuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException {
        this.returnJson(response);
    }

    private void returnJson(HttpServletResponse response)throws IOException {
        MyAuthenticationException myAuthenticationException = new MyAuthenticationException("账号或密码错误，请重试！");
        response.setStatus(0);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println("{\"message\": \""+ myAuthenticationException.getMessage() +"\",\"serverTime\": " + LocalDateTime.now() +"}");
    }
}
