package net.ttcxy.tang.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功
 * created by huanglei on 2020/01/01
 */
@Component("myAuthenticationSuccessHandler")
public class   MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException{
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().append("登录成功");
    }
}
