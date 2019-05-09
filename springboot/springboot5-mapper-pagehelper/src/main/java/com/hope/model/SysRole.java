package com.hope.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-08 10:53
 **/
@Data
@Table(name = "sys_role")
public class SysRole {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private Integer roleId;
    private String role_uuid;
    private String role;
    private String description;
    private Integer status;
    private Date create_time;
    private Date update_time;
}