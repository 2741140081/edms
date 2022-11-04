package com.marks.edms.security;

import com.marks.edms.dao.UserDao;
import com.marks.edms.domain.SysRole;
import com.marks.edms.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userDao.findByUserName(username);
        if (user == null) {
            throw  new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (SysRole role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole_name()));
            System.out.println(role.getRole_name());
        }
        System.out.println(user.getUsername()+"<=========>"+user.getPassword());
        return new User(user.getUsername(), user.getPassword(), authorities);

    }
}
