package ll.mrli.lixf.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ll.mrli.lixf.common.entity.system.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> findUserPermissions(String username);
}
