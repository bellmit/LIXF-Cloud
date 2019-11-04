package ll.mrli.lixf.auth.service;

import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import ll.mrli.lixf.auth.properties.LixfAuthProperties;
import ll.mrli.lixf.auth.properties.LixfValidateCodeProperties;
import ll.mrli.lixf.common.entity.LixfConstant;
import ll.mrli.lixf.common.exception.ValidateCodeException;
import ll.mrli.lixf.common.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class ValidateCodeService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private LixfAuthProperties lixfAuthProperties;

    public void create(HttpServletRequest request, HttpServletResponse response) throws ValidateCodeException, IOException {
        String key = request.getParameter("key");
        if(StringUtils.isBlank(key)) {
            throw new ValidateCodeException("验证码key不能为空");
        }
        LixfValidateCodeProperties code = lixfAuthProperties.getCode();
        setHeader(response, code.getType());

        Captcha captcha = createCaptcha(code);
        redisService.set(LixfConstant.CODE_PREFIX + key,StringUtils.lowerCase(captcha.text()),code.getTime());
        captcha.out(response.getOutputStream());
    }

    /**
     * 校验验证码
     * @param key   前端发送的 key
     * @param value 前端发送待校验的值 value
     * @throws ValidateCodeException
     */
    public void check(String key, String value) throws ValidateCodeException {
        Object codeInRedis = redisService.get(LixfConstant.CODE_PREFIX + key);
        if(StringUtils.isBlank(value)) {
            throw new ValidateCodeException("请输入验证码");
        }
        if(codeInRedis == null) {
            throw new ValidateCodeException("验证码已过期");
        }
        if(!StringUtils.equalsIgnoreCase(value,String.valueOf(codeInRedis))) {
            throw new ValidateCodeException("验证码不正确");
        }
    }

    private Captcha createCaptcha(LixfValidateCodeProperties code) {
        Captcha captcha = null;
        if(StringUtils.equalsIgnoreCase(code.getType(), LixfConstant.GIF)) {
            captcha = new GifCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        } else {
            captcha = new SpecCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        }
        captcha.setCharType(code.getCharType());
        return captcha;
    }

    private void setHeader(HttpServletResponse response, String type) {
        if(StringUtils.equalsIgnoreCase(type, LixfConstant.GIF)) {
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }
}
