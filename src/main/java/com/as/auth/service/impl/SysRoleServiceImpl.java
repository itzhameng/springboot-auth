package com.as.auth.service.impl;

import com.as.auth.mapper.SysRoleMapper;
import com.as.auth.model.system.SysRole;
import com.as.auth.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
}
