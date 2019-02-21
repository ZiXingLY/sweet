package io.anshily.shiro.classical.filter;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JWTCredentialsMatcher implements CredentialsMatcher {
    private final Logger logger = LoggerFactory.getLogger(JWTCredentialsMatcher.class);

    /**
     * Matcher中直接调用工具包中的verify方法即可
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
//        String token = (String) authenticationToken.getCredentials();
//        ExUsernamePasswordToken etoken = (ExUsernamePasswordToken) authenticationToken;
        String token =((JWTToken)authenticationToken).getToken();

        Object stored = authenticationInfo.getCredentials();
        String salt = stored.toString();

//        User user = (User)authenticationInfo.getPrincipals().getPrimaryPrincipal();
//        try {
////            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
////            Algorithm algorithm = Algorithm.HMAC256(salt);
////            JWTVerifier verifier = JWT.require(algorithm)
////                    .withClaim("account", user.getPhone())
////                    .build();
////            verifier.verify(token);
//
//            Algorithm algorithm = Algorithm.HMAC256("hanghang");
//            JWTVerifier verifier = JWT.require(algorithm).withIssuer(authenticationToken.getPrincipal().toString()).build();
//            DecodedJWT jwt = verifier.verify(token);
//
//            return true;
//        } catch (UnsupportedEncodingException | JWTVerificationException e) {
//            logger.error("Token Error:{}", e.getMessage());
//        }
        System.out.println("认证token");
        return true;
    }
}
