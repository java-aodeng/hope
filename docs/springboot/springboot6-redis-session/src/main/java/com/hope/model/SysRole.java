package com.hope.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-08 10:53
 **/
@Data
public class SysRole {
    private static final long serialVersionUID = 1L;

    private Integer roleId;
    private String role_uuid;
    private String role;
    private String description;
}