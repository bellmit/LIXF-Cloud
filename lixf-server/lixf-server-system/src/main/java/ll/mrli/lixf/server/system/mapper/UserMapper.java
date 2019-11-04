package ll.mrli.lixf.server.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ll.mrli.lixf.common.entity.system.SystemUser;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<SystemUser> {

    /**
     * 查找用户详细信息
     * @param page       分页对象
     * @param systemUser 用户对象，用于传递查询条件
     * @return
     */
    IPage<SystemUser> findUserDetailpage(Page page, @Param("user") SystemUser systemUser);
}
