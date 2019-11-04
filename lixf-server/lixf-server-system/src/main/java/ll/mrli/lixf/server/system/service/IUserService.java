package ll.mrli.lixf.server.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import ll.mrli.lixf.common.entity.QueryRequest;
import ll.mrli.lixf.common.entity.system.SystemUser;

public interface IUserService extends IService<SystemUser> {

    /**
     * 查找用户详细信息
     * @param request request
     * @param user    用户对象，用于传递查询条件
     * @return IPage
     * @return
     */
    IPage<SystemUser> findUserDetail(SystemUser user, QueryRequest request);

    /**
     * 新增用户
     * @param user
     */
    void createUser(SystemUser user);

    /**
     * 修改用户
     * @param user
     */
    void updateUser(SystemUser user);

    /**
     * 删除用户
     * @param userIds
     */
    void deleteUser(String[] userIds);
}
