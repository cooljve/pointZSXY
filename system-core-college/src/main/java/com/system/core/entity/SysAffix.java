package com.system.core.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统上传的附件(包括图片)
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "sys_affix")
public class SysAffix implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "SYS_AFFIX_SEQ")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", length = 250)
    private String name;

    @Column(name = "SOURCE", length = 250)
    private String source;


    @Column(name = "TYPE", length = 200)
    private String type;

    @Column(name = "SIZE", length = 50)
    private String size;

    @Column(name = "EXTNAME", length = 50)
    private String extname;

    @Column(name = "CREATE_DATE")
    private Date createDate;
    /**
     * 图片的宽度
     */
    @Column(name = "WIDTH")
    private Integer width;
    /**
     * 图片的高度
     */
    @Column(name = "HEIGHT")
    private Integer height;

    public SysAffix(Integer id) {
        super();
        this.id = id;
    }
}
