package ll.mrli.lixf.server.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import ll.mrli.lixf.common.entity.LixfResponse;
import ll.mrli.lixf.common.entity.QueryRequest;
import ll.mrli.lixf.common.entity.system.SystemUser;
import ll.mrli.lixf.common.exception.LixfException;
import ll.mrli.lixf.common.utils.LixfUtil;
import ll.mrli.lixf.server.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:view')")
    public LixfResponse userList(QueryRequest queryRequest, SystemUser user) {
        Map<String, Object> dataTable = LixfUtil.getDataTable(userService.findUserDetail(user, queryRequest));
        return new LixfResponse().data(dataTable);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:add')")
    public void addUser(@Valid SystemUser user) throws LixfException {
        try {
            this.userService.createUser(user);
        } catch (Exception e) {
            String message = "新增用户失败";
            log.error(message, e);
            throw new LixfException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('user:update')")
    public void updateUser(@Valid SystemUser user) throws LixfException {
        try {
            this.userService.updateUser(user);
        } catch (Exception e) {
            String message = "修改用户失败";
            log.error(message, e);
            throw new LixfException(message);
        }
    }

    @DeleteMapping("/{userIds}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public void deleteUser(@NotBlank(message = "{required}") @PathVariable String userIds) throws LixfException {
        try {
            String[] ids = userIds.split(StringPool.COMMA);
            this.userService.deleteUser(ids);
        } catch (Exception e) {
            String message = "删除用户失败";
            log.error(message, e);
            throw new LixfException(message);
        }
    }
}
