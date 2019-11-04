package ll.mrli.lixf.auth.controller;

import ll.mrli.lixf.auth.service.ValidateCodeService;
import ll.mrli.lixf.common.entity.LixfResponse;
import ll.mrli.lixf.common.exception.LixfAuthException;
import ll.mrli.lixf.common.exception.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@RestController
public class SecurityController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;
    @Autowired
    private ValidateCodeService validateCodeService;

    @GetMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws ValidateCodeException, IOException {
        validateCodeService.create(request,response);
    }

    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @DeleteMapping("signout")
    public LixfResponse signout(HttpServletRequest request) throws LixfAuthException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization,"bearer ", "");
        LixfResponse lixfResponse = new LixfResponse();
        if(!consumerTokenServices.revokeToken(token)) {
            throw new LixfAuthException("退出登录失败");
        }
        return lixfResponse.message("退出登录成功");
    }
}
