package ll.mrli.lixf.common.handler;

import ll.mrli.lixf.common.entity.LixfResponse;
import ll.mrli.lixf.common.utils.LixfUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LixfAuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        LixfResponse lixfResponse = new LixfResponse();
        LixfUtil.makeResponse(
                httpServletResponse,MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_UNAUTHORIZED,lixfResponse.message("token无效"));
    }
}
