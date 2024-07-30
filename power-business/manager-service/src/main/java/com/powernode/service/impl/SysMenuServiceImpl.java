package com.powernode.service.impl;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Set;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.powernode.domain.SysMenu;
import com.powernode.mapper.SysMenuMapper;
import com.powernode.service.SysMenuService;
@Service
@CacheConfig(cacheNames = "com.powernode.service.impl.SysMenuServiceImpl")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService{

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    @Cacheable(key = "#loginUserId")
    public Set<SysMenu> queryUserMenuListByUserId(Long loginUserId) {
        // 根据用户标识查询菜单权限集合
        Set<SysMenu> menus = sysMenuMapper.selectUserMenuListByUserId(loginUserId);
        // 将菜单权限集合的数据转换为树结构（即：数据结构应该为层级关系的）
        return transformTree(menus,0L);
    }

    /**
     * 集合转换为树结构
     *  1.已知菜单深度 <=2
     *
     *  2.未知菜单深度
     *
     * @param menus
     * @param pid
     * @return
     */
    private Set<SysMenu> transformTree(Set<SysMenu> menus, Long pid) {
        // 已知菜单深度<=2
        // 从菜单集合中获取根节点集合
        /*Set<SysMenu> roots = menus.stream()
                .filter(m -> m.getParentId().equals(pid))
                .collect(Collectors.toSet());
        // 循环遍历根节点集合
        roots.forEach(root -> {
            // 从菜单集合中过滤出它的父节点值与当前根节点的id值一致的菜单集合
            Set<SysMenu> child = menus.stream()
                    .filter(m -> m.getParentId().equals(root.getMenuId()))
                    .collect(Collectors.toSet());
            root.setList(child);
        });*/

        // 未知菜单深度
        // 获取根节点集合
        Set<SysMenu> roots = menus.stream()
                .filter(m -> m.getParentId().equals(pid))
                .collect(Collectors.toSet());
        // 循环节点集合
        roots.forEach(r -> r.setList(transformTree(menus,r.getMenuId())));
        return roots;
    }
}
