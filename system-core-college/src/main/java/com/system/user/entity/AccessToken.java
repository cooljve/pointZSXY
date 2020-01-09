package com.system.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "t_access_token")
public class AccessToken implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "APP_ACCESS_TOKEN_SEQ")
    @Column(name = "ID")
    private Integer id;
    /**
     * access_token凭证
     **/
    @Column(name = "ACCESS_TOKEN", length = 1000)
    private String accessToken;
    /**
     * '凭证有效时间，单位：秒'
     **/
    @Column(name = "EXPIRES_IN")
    private Long expiresIn;
    /**
     * 用户的id
     **/
    @Column(name = "CUS_ID")
    private Integer cusId;
    /**
     * 当前时间毫秒数
     **/
    @Column(name = "ACCESS_TIME")
    private Long accessTime;

}
