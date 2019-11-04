package ll.mrli.lixf.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ll.mrli.lixf.common.entity.system.UserRole;

public interface IUserRoleService extends IService<UserRole> {

    void deleteUserRolesByRoleId(String[] roleIds);

    void deleteUserRolesByUserId(String[] userIds);
}
