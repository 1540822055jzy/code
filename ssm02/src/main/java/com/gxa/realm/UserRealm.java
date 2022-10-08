package com.gxa.realm;

import com.gxa.entity.User;
import com.gxa.mapper.UserMapperLogin;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserMapperLogin userMapperLogin;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        System.out.println("-----------------------认证方法-----------------------------");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = this.userMapperLogin.queryByName(token.getUsername());

        ByteSource salt = ByteSource.Util.bytes(user.getSalt());
        SimpleAuthenticationInfo sai = new SimpleAuthenticationInfo(user, user.getPassword(), salt, this.getName());

        return sai;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("------授权方法-------------");
       SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        Set<String> perms = userMapperLogin.queryPermsByUsername(user.getUsername());
        System.out.println(perms);
        authorizationInfo.addStringPermissions(perms);

        return authorizationInfo;
    }



    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        Object credentials = "ls";


//		Object result = new SimpleHash(hashAlgorithmName,credentials);
//		System.out.println(result);


//		Object result = new SimpleHash(hashAlgorithmName,credentials,null,1024);
//		System.out.println(result);

        Object salt = ByteSource.Util.bytes("789");;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, 1024);
        System.out.println(result);
    }
}
