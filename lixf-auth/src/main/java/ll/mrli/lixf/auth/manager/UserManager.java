package ll.mrli.lixf.auth.manager;

import ll.mrli.lixf.auth.mapper.MenuMapper;
import ll.mrli.lixf.auth.mapper.UserMapper;
import ll.mrli.lixf.common.entity.system.Menu;
import ll.mrli.lixf.common.entity.system.SystemUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManager {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    public SystemUser findByName(String username) {
        return userMapper.findByName(username);
    }

    public String findUserPermissions(String username) {
        List<Menu> userPermissions = menuMapper.findUserPermissions(username);
       /* List<String> perms = new ArrayList<>();
        for (Menu m:userPermissions) {
            perms.add(m.getPerms());
        }
         return StringUtils.join(perms,",");*/
        return userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(","));
    }
}
