package ll.mrli.lixf.common.handler;

import ll.mrli.lixf.common.entity.LixfResponse;
import ll.mrli.lixf.common.exception.LixfAuthException;
import ll.mrli.lixf.common.exception.LixfException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public LixfResponse handlerException(Exception e){
        log.error("系统内部异常，异常信息", e);
        return new LixfResponse().message("系统内部异常");
    }

    @ExceptionHandler(value = LixfAuthException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public LixfResponse handleLixfAuthException(LixfAuthException e) {
        log.error("系统错误", e);
        return new LixfResponse().message(e.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public LixfResponse handleAccessDeniedException(){
        return new LixfResponse().message("没有权限访问该资源");
    }

    @ExceptionHandler(value = LixfException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public LixfResponse handleLixfException(LixfException e) {
        log.error("系统错误",e);
        return new LixfResponse().message(e.getMessage());
    }

    /**
     * 统一处理请求参数校验(普通传参)
     *
     * @param e ConstraintViolationException
     * @return FebsResponse
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public LixfResponse handleConstrainViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for(ConstraintViolation<?> violation:violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ",");
            message.append(pathArr[1]).append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return new LixfResponse().message(message.toString());
    }

    /**
     * 统一请求参数校验（实体对象传参）
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public LixfResponse handleBindException(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for(FieldError error:fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return new LixfResponse().message(message.toString());
    }
}
