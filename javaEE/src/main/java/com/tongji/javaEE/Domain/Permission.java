package com.tongji.javaEE.Domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "vip_permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "permissions")
    private String permissions;

    @Column(name = "vip_level")
    private int vipLevel;

    public Permission(){super();}

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }

}
