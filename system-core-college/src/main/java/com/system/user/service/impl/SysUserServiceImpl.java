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
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static com.system.core.util.Constant.COMMA;
import static com.system.core.util.Constant.YYYY_MM_DD_HH_MM_SS;
import static com.system.core.util.CustomDateUtil.getNextDay;

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
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Expression<Boolean>> andPredicates = new ArrayList<Expression<Boolean>>();
                Predicate namePredicate = null;

                namePredicate = criteriaBuilder.and(criteriaBuilder.equal(root.<Integer>get("isdelete"), 0));
                andPredicates.add(namePredicate);

                if (!StringUtils.isEmpty(model.getUsername())) {
                    namePredicate = criteriaBuilder.and(criteriaBuilder.like(root.get("username"), "%" + model.getUsername() + "%"));
                    andPredicates.add(namePredicate);
                }
//				if(!ParamUtil.isEmpty(model.getTruename())){
//					namePredicate = cb.and(cb.like(root.<String>get("truename"),"%"+model.getTruename()+"%"));
//					andPredicates.add(namePredicate);
//				}
                if (!StringUtils.isEmpty(model.getCode())) {
                    namePredicate = criteriaBuilder.and(criteriaBuilder.like(root.get("code"), "%" + model.getCode() + "%"));
                    andPredicates.add(namePredicate);
                }
                if (model.getStatus() != null && model.getStatus() != -1) {
                    namePredicate = criteriaBuilder.and(criteriaBuilder.equal(root.<Integer>get("status"), model.getStatus()));
                    andPredicates.add(namePredicate);
                }

                if (andPredicates.isEmpty()) {
                    return null;
                } else {
                    Predicate predicate = criteriaBuilder.conjunction();
                    predicate.getExpressions().addAll(andPredicates);
                    return predicate;
                }
            }

        };
    }

    @Override
    public SysUser getSysUserById(Integer id) {
        return sysUserDao.findOne(id);
    }

    public SysUser getSysUserByUsername(String userName, Integer id) {
        return id == null ? sysUserDao.findSysUserByUsername(userName) : sysUserDao.findSysUserByUsername(userName, id);
    }

    @Override
    @Transactional
    public Integer addSysUser(SysUserModel model) {
        SysUser sysUser = new SysUser();
        model.setPassword(DigestMD5Util.MD5(model.getPassword()));
        ParamUtil.bindBean(sysUser, model);
        sysUser.setAccount(System.currentTimeMillis() + "");
        sysUser.setCreateDate(CustomDateUtil.getNowTime(YYYY_MM_DD_HH_MM_SS));
        sysUserDao.save(sysUser);
        updateUserRole(sysUser, model.getRoleId(), false);
        return sysUser.getId();
    }

    @Transactional
    void addUserRole(Integer roleId, Integer userId, boolean deleteUserRole) {
        if (deleteUserRole) {
            sysUserRoleDao.delByUserId(userId);
        }
        SysUserRole sysUserRole = new SysUserRole(userId, roleId);
        sysUserRoleDao.save(sysUserRole);
    }

    @Override
    @Transactional
    public void updateSysUser(Integer id, SysUserModel model) {
        SysUser sysUser = sysUserDao.findOne(id);
        if (sysUser != null) {
            ParamUtil.bindBean(sysUser, model);
            sysUser.setModifyDate(CustomDateUtil.getNowTime(YYYY_MM_DD_HH_MM_SS));
            sysUserDao.save(sysUser);
            updateUserRole(sysUser, model.getRoleId(), true);
        }
    }

    //手机端更新用户信息
    @Override
    @Transactional
    public void updateSysUserPhone(Integer id, String nickname, String position, String detail, Integer sex) {
        SysUser sysUser = sysUserDao.findOne(id);
        if (sysUser != null) {
            sysUser.setModifyDate(CustomDateUtil.getNowTime(YYYY_MM_DD_HH_MM_SS));
            if (!StringUtils.isEmpty(nickname)) {
                sysUser.setNickname(nickname);
            }
            if (!StringUtils.isEmpty(position)) {
                sysUser.setPosition(position);
            }
            if (!StringUtils.isEmpty(detail)) {
                sysUser.setDetail(detail);
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
            sysUser.setRemark2(getNextDay(CustomDateUtil.getNowTime(YYYY_MM_DD_HH_MM_SS)).toString());
            sysUserDao.save(sysUser);
            updateUserRole(sysUser, model.getRoleId(), true);
        }
    }

    @Override
    @Transactional
    public void delSysUser(String ids) {
        Integer[] userIDs = ParamUtil.toIntegers(ids.split(COMMA));
        sysUserRoleDao.delByUserIds(userIDs);
        sysUserDao.deleteSysUser(userIDs);
    }

    @Override
    @Transactional
    public void updateSysUserStatus(Integer status, String ids) {
        Integer[] ides = ParamUtil.toIntegers(ids.split(COMMA));
        sysUserDao.updateSysUserStatus(status, ides);
    }

    @Override
    @Transactional
    public void updateSysUserPassword(String password, String ids) {
        Integer[] ides = ParamUtil.toIntegers(ids.split(COMMA));
        password = DigestMD5Util.MD5(password);
        sysUserDao.updateSysUserPassword(password, ides);
    }

    @Override
    public SysUser findSysUserByToken(String accessToken) {
        List<AccessToken> list = accessTokenDao.findAccessTokenByToken(accessToken);
        if (ObjectUtils.isEmpty(list)) {
            return null;
        }
        return sysUserDao.findOne(list.get(0).getUserId());
    }

    private void updateUserRole(SysUser sysUser, Integer roleId, boolean b) {
        if (null != roleId && -1 != roleId) {
            addUserRole(roleId, sysUser.getId(), b);
        }
    }
}
