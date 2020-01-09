package com.system.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_menu")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fieldHandler"})
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "MAX_MENU_SEQ")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", length = 200)
    private String name;

    @Column(name = "ENAME", length = 200)
    private String ename;

    @Column(name = "DESCRIPTION", length = 200)
    private String description;

    @Column(name = "ORDER_BY")
    private Long orderBy;

    @Column(name = "IMG", length = 200)
    private String img;

    @Column(name = "LINK", length = 200)
    private String link;

    @JoinColumn(name = "PARENT_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private SysMenu parentObj;

    @Column(name = "PARENT_ID")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentObj", cascade = {javax.persistence.CascadeType.ALL})
    @OrderBy("ORDER_BY")
    @JsonIgnore
    private Set<SysMenu> childSet;

    @Column(name = "CUR_DATE")
    private Date curDate;

    @Column(name = "ALIAS", length = 200)
    private String alias;

    @Column(name = "EXTENDF1", length = 200)
    private String extendF1;

    @Column(name = "EXTENDF2", length = 200)
    private String extendF2;

    public SysMenu(Integer id) {
        super();
        this.id = id;
    }
}
