package com.marks.edms.domain;

import java.util.List;

public class SysUser {
    private Integer user_id;
    private String username;
    private String password;

    private List<SysRole> roles;



    public SysUser() {
    }

    public SysUser(Integer user_id, String username, String password, List<SysRole> roles) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
