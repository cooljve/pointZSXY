package com.system.user.web.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysMenuModel {

    private String name;

    private String eName;

    private String description;

    private Long orderBy;

    private String img;

    private String link;

    private Integer parentId;

    private Date curDate;

    private String alias;

    private String extendF1;

    private String extendF2;

}
