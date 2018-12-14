package com.futrue.asset.service.shiro.realm;

import com.futrue.asset.service.sys.SysUserService;
import com.futrue.common.constant.BusinessConstant;
import com.futrue.common.entity.sys.TbUser;
import com.futrue.common.exception.BusinessException;
import com.futrue.common.utils.Preconditions;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *  @Author: Yuhan.Tang
 *  @ClassName: UsernamePasswordAuthorizingRealm
 *  @package: com.futrue.asset.service.shiro.realm
 *  @Date: Created in 2018/7/19 下午7:26
 *  @email yuhan.tang@magicwindow.cn
 *  @Description: 
 */    
@Service
public class UsernamePasswordAuthorizingRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(UsernamePasswordAuthorizingRealm.class);

    @Autowired
    private SysUserService sysUserService;

    @Value("${shiro.credentialsSalt}")
    private String credentialsSalt;

    /**
     * 不用查询权限信息，放到jwt realm授权方法种处理
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) auth;

        String username = usernamePasswordToken.getUsername();
        if (StringUtils.isEmpty(username)) {
            throw new BusinessException(BusinessConstant.USER_NAME_IS_NULL);
        }

        TbUser sysUser = sysUserService.findByPhone(username);
        if (Preconditions.isBlank(sysUser)) {
            throw new BusinessException(BusinessConstant.USER_NAME_NOT_FOUND);
        }
        if(usernamePasswordToken.getType() == 1){

            sysUser.setPassWord((new SimpleHash("MD5", sysUser.getPassWord(), ByteSource.Util.bytes(credentialsSalt), 10)).toString());
        }
        return new SimpleAuthenticationInfo(sysUser, sysUser.getPassWord(), ByteSource.Util.bytes(credentialsSalt), getName());
    }

    /**
     * 重写 setCredentialsMatcher 方法为自定义的 Realm 设置 hash 验证方法
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //加密类型
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        hashedCredentialsMatcher.setHashIterations(10);
        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }

}