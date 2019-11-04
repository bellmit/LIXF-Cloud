package ll.mrli.lixf.gateway.filter;

import com.netflix.zuul.context.RequestContext;
import ll.mrli.lixf.common.entity.LixfResponse;
import ll.mrli.lixf.common.utils.LixfUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LixfGatewayErrorFilter extends SendErrorFilter {

    @Override
    public Object run() {
        try {
            LixfResponse lixfResponse = new LixfResponse();
            RequestContext ctx = RequestContext.getCurrentContext();
            String serviceId = (String)ctx.get(FilterConstants.SERVICE_ID_KEY);


            ExceptionHolder exception = findZuulException(ctx.getThrowable());
            String errorCause = exception.getErrorCause();
            Throwable throwable = exception.getThrowable();
            String message = throwable.getMessage();
            message = StringUtils.isBlank(message) ? errorCause : message;
            lixfResponse = resolveExceptionMessage(message,serviceId,lixfResponse);

            HttpServletResponse response = ctx.getResponse();
            LixfUtil.makeResponse(
                    response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,lixfResponse);
            log.error("Zull sendError：{}", lixfResponse.getMessage());
        } catch (Exception e) {
            log.error("Zuul sendError", e);
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return  null;
    }

    public LixfResponse resolveExceptionMessage(String message,String serverId,LixfResponse lixfResponse) {
        if(StringUtils.containsIgnoreCase(message, "time out")) {
            return lixfResponse.message("请求" + serverId +"服务超时");
        }
        if(StringUtils.containsIgnoreCase(message, "forwarding error")) {
            return lixfResponse.message(serverId + "服务不可用");
        }
        return lixfResponse.message("Zuul请求"+ serverId +"服务异常");
    }
}
