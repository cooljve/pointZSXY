package com.system.user.service.impl;

import com.system.core.util.CustomDateUtil;
import com.system.core.util.DigestMD5Util;
import com.system.core.util.ParamUtil;
import com.system.user.dao.AccessTokenDao;
import com.system.user.dao.SysRoleDao;
import com.system.user.dao.SysUserDao;
import com.system.user.dao.SysUserRoleDao;
import com.system.user.entity.AccessToken;
import com.system.user.entity.SysRole;
import com.system.user.entity.SysUser;
import com.system.user.entity.SysUserRole;
import com.system.user.service.SysUserService;
import com.system.user.web.model.SysUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private AccessTokenDao accessTokenDao;

    @Transactional(readOnly = true)
    public Page<SysUser> pageList(SysUserModel model, Pageable pageable) {
        Page<SysUser> page = sysUserDao.findAll(getSpecification(model), pageable);
        for (SysUser sysUser : page) {
            SysRole sysRole = sysRoleDao.findOne(sysUser.getRoleId());
            if (null != sysRole) {
                sysUser.setRoleName(sysRole.getName());
            } else {
                sysUser.setRoleName("");
            }
        }
        return page;
    }

    public Specification<SysUser> getSpecification(final SysUserModel model) {
        return new Specification<SysUser>() {
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Expression<Boolean>> andPredicates = new ArrayList<Expression<Boolean>>();
                Predicate namePredicate = null;

                namePredicate = cb.and(cb.equal(root.<Integer>get("isdelete"), 0));
                andPredicates.add(namePredicate);

                if (!StringUtils.isEmpty(model.getUsername())) {
                    namePredicate = cb.and(cb.like(root.<String>get("username"), "%" + model.getUsername() + "%"));
                    andPredicates.add(namePredicate);
                }
//				if(!ParamUtil.isEmpty(model.getTruename())){
//					namePredicate = cb.and(cb.like(root.<String>get("truename"),"%"+model.getTruename()+"%"));
//					andPredicates.add(namePredicate);
//				}
                if (!StringUtils.isEmpty(model.getCode())) {
                    namePredicate = cb.and(cb.like(root.<String>get("code"), "%" + model.getCode() + "%"));
                    andPredicates.add(namePredicate);
                }
                if (model.getStatus() != null && model.getStatus() != -1) {
                    namePredicate = cb.and(cb.equal(root.<Integer>get("status"), model.getStatus()));
                    andPredicates.add(namePredicate);
                }

                if (andPredicates.isEmpty()) {
                    return null;
                } else {
                    Predicate predicate = cb.conjunction();
                    predicate.getExpressions().addAll(andPredicates);
                    return predicate;
                }
            }

        };
    }

    @Override
    public SysUser getSysUserById(Integer id) {
        SysUser sysUser = sysUserDao.findOne(id);
        return sysUser;
    }

    public SysUser getSysUserByUsername(String userName, Integer id) {
        if (id == null) {
            return sysUserDao.findSysUserByUsername(userName);
        } else {
            return sysUserDao.findSysUserByUsername(userName, id);
        }
    }

    @Override
    @Transactional
    public Integer addSysUser(SysUserModel model) {
        SysUser sysUser = new SysUser();
        model.setPassword(DigestMD5Util.MD5(model.getPassword()));
        ParamUtil.bindBean(sysUser, model);
        sysUser.setIsDelete(0);
        //账号
        sysUser.setAccount(System.currentTimeMillis() + "");
        //添加时间
        sysUser.setCreateDate(CustomDateUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
        sysUserDao.save(sysUser);
        if (null != model.getRoleId() && -1 != model.getRoleId()) {
            addUserRoles(model.getRoleId(), sysUser.getId(), false);
        }
        return sysUser.getId();
    }

    @Transactional
    void addUserRoles(Integer roleId, Integer userid, boolean flag) {
        if (flag) {
            sysUserRoleDao.delByUserId(userid);
        }
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(userid);
        sysUserRole.setRoleId(roleId);
        sysUserRoleDao.save(sysUserRole);
    }

    @Override
    @Transactional
    public void updateSysUser(Integer id, SysUserModel model) {
        SysUser sysUser = sysUserDao.findOne(id);
        if (sysUser != null) {
            ParamUtil.bindBean(sysUser, model);
            sysUser.setModifyDate(CustomDateUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));

            sysUserDao.save(sysUser);
            if (null != model.getRoleId() && -1 != model.getRoleId()) {
                addUserRoles(model.getRoleId(), sysUser.getId(), true);
            }
        }
    }

    //手机端更新用户信息
    @Override
    @Transactional
    public void updateSysUserPhone(Integer id, String nickname, String zhiwei, String jiesao, Integer sex) {
        SysUser sysUser = sysUserDao.findOne(id);
        if (sysUser != null) {
            sysUser.setModifyDate(CustomDateUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
            //昵称
            if (null == nickname || nickname.equals("")) {
                sysUser.setNickname(sysUser.getNickname());
            } else {
                sysUser.setNickname(nickname);
            }
            //职位
            if (null == zhiwei || zhiwei.equals("")) {
                sysUser.setPosition(sysUser.getPosition());
            } else {
                sysUser.setPosition(zhiwei);
            }
            //介绍
            if (null == jiesao || jiesao.equals("")) {
                sysUser.setDetail(sysUser.getDetail());
            } else {
                sysUser.setDetail(jiesao);
            }

            sysUserDao.save(sysUser);
        }
    }


    @Override
    @Transactional
    public void updateSysUserVip(Integer id, SysUser model) {
        SysUser sysUser = sysUserDao.findOne(id);
        if (sysUser != null) {
            ParamUtil.bindBean(sysUser, model);
            sysUser.setRemark2(getNextDay(CustomDateUtil.getNowTime("yyyy-MM-dd HH:mm:ss")).toString());

            sysUserDao.save(sysUser);
            if (null != model.getRoleId() && -1 != model.getRoleId()) {
                addUserRoles(model.getRoleId(), sysUser.getId(), true);
            }
        }
    }

    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    @Override
    @Transactional
    public void delSysUser(String ids) {
        Integer[] ides = ParamUtil.toIntegers(ids.split(","));
        for (Integer integer : ides) {
            sysUserRoleDao.delByUserId(integer);//删除用户角色中间表
        }
        sysUserDao.deleteSysUser(ides);
    }

    @Override
    @Transactional
    public void upSysUserStatus(Integer status, String ids) {
        Integer[] ides = ParamUtil.toIntegers(ids.split(","));
        sysUserDao.updateSysUserStatus(status, ides);
    }

    @Override
    @Transactional
    public void upSysUserPass(String password, String ids) {
        Integer[] ides = ParamUtil.toIntegers(ids.split(","));
        password = DigestMD5Util.MD5(password);
        sysUserDao.updateSysUserPass(password, ides);
    }

    @Override
    public SysUser findSysUserByToken(String access_token) {
        List<AccessToken> list = accessTokenDao.findAccessTokenByToken(access_token);
        if (null != list && list.size() > 0) {
            SysUser customer = sysUserDao.findOne(list.get(0).getCusId());
            return customer;
        }
        return null;
    }
}
