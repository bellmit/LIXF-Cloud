package ll.mrli.lixf.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import ll.mrli.lixf.common.entity.LixfConstant;
import ll.mrli.lixf.common.entity.LixfResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LixfServerProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头中获取 Zuul Token
        String token = request.getHeader(LixfConstant.ZUUL_TOKEN_HEADER);
        String zuulToken = new String(Base64Utils.encode(LixfConstant.ZUUL_TOKEN_VALUE.getBytes()));
        //校验 Zuul Token的正确性
        if(StringUtils.equals(zuulToken,token)) {
            return true;
        } else {
            LixfResponse lixfResponse = new LixfResponse();
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(JSONObject.toJSONString(lixfResponse.message("请通网关获取资源")));
            return false;
        }
    }
}
