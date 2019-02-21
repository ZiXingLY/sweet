package io.anshily.shiro.classical.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JWTShiroRealm extends AuthorizingRealm {

    private final Logger logger = LoggerFactory.getLogger(JWTShiroRealm.class);


    JWTShiroRealm(){
        setCredentialsMatcher(new JWTCredentialsMatcher());
        setAuthenticationTokenClass(JWTToken.class);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String infoToken = ((JWTToken)token).getToken();
        DecodedJWT jwt = JWT.decode(infoToken);
        logger.info(jwt.getIssuer() + "info");
        return new SimpleAuthenticationInfo(jwt.getIssuer(), infoToken, getName());
    }
}
