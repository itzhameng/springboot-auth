package com.as.auth;

import com.as.auth.mapper.SysRoleMapper;
import com.as.auth.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootAuthApplicationTests {

    @Autowired
    private SysRoleMapper roleMapper;
    @Test
    void contextLoads() {
        List<SysRole> sysRoles = roleMapper.selectList(null);
        for (SysRole sysRole : sysRoles) {
            System.out.println("sysRole = " + sysRole);
        }
    }

}
