package ll.mrli.lixf.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ll.mrli.lixf.common.entity.system.SystemUser;

public interface UserMapper extends BaseMapper<SystemUser> {
    SystemUser findByName(String username);
}
