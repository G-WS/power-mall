package com.powernode.model;

import cn.hutool.core.lang.hash.Hash;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Security安全框架认识的安全用户对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityUser implements UserDetails {
    //商城后台管理系统用户的相关属性
    private Long userId;
    private String username;
    private String password;
    private Integer status;
    private Long shopId;
    private String loginType;
    private Set<String> perms = new HashSet<>();
    //商城购物系统会员的相关属性

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.loginType + this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.status == 1;
    }

    public Set<String> getPerms() {
        HashSet<String> finalPermsSet = new HashSet<>();
        //遍历用户权限集合
        perms.forEach(perm -> {
            if (perms.contains(",")) {
                //包含，说明一条权限里面有多个权限
                //根据，号进行分隔处理
                String[] realPerms = perm.split(",");
                for (String realPerm : realPerms) {
                    finalPermsSet.add(realPerm);
                }

            } else {
                //不包含即只有一条权限
                finalPermsSet.add(perm);
            }
        });
        return finalPermsSet;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.status == 1;
    }

    @Override
    public boolean isEnabled() {
        return this.status == 1;
    }
}
