package io.anshily.shiro.classical.filter;


//import com.qy.admin.service.SysPermissionInitService;
//import SysPermissionInit;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
//import java.util.List;
import java.util.Map;

/**
 * @author 作者 z77z
 * @date 创建时间：2017年2月10日 下午1:16:38
 */
//@Configuration
public class ShiroConfig {
//
//	@Autowired(required = false)
//    SysPermissionInitService sysPermissionInitService;

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * <p>
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {


//        System.out.println(securityManager);
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        filtersMap.put("cors", new CORSShrioFilter());
        filtersMap.put("jwt", new JwtAuthFilter());
        //限制同一帐号同时在线的个数。
        filtersMap.put("kickout", kickoutSessionControlFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/loginrefuse");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/indexrefuse");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/loginrefuse");


        // 权限控制map.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();


//        filterChainDefinitionMap.put("/**", "anon");


        filterChainDefinitionMap.put("/article/wxFindArticleByState", "user");
        filterChainDefinitionMap.put("/front/**", "anon");
        filterChainDefinitionMap.put("/regis", "anon");
        filterChainDefinitionMap.put("/forgotPass", "anon");
        filterChainDefinitionMap.put("/resetPass","anon");
//		filterChainDefinitionMap.put("/index","anon");
        filterChainDefinitionMap.put("/user/**", "anon");
        filterChainDefinitionMap.put("../../public/**", "anon");
        filterChainDefinitionMap.put("/public/**", "anon");

        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/ajaxLogin", "anon");
        filterChainDefinitionMap.put("/ajaxRegister", "anon");
        filterChainDefinitionMap.put("/uploads/**", "anon");
        filterChainDefinitionMap.put("/admin", "anon");
        filterChainDefinitionMap.put("/sys/**", "anon");
        filterChainDefinitionMap.put("/readArticle/**", "anon");
        filterChainDefinitionMap.put("/moreArticle/**", "anon");
        filterChainDefinitionMap.put("/list", "anon");
        filterChainDefinitionMap.put("/sysReadArticle/**", "anon");
        filterChainDefinitionMap.put("/sysCheckArticle/**", "anon");
        filterChainDefinitionMap.put("/sysChangeArticleState/**", "anon");
        filterChainDefinitionMap.put("/sysFindArticleByState/**", "anon");
        filterChainDefinitionMap.put("/sysFindUserExtend/**", "anon");
        filterChainDefinitionMap.put("/category/**", "anon");
        filterChainDefinitionMap.put("/article/**", "anon");
        filterChainDefinitionMap.put("/flash/**", "anon");
        filterChainDefinitionMap.put("/views/front/articleDetail.html", "anon");
        filterChainDefinitionMap.put("/credits/toReadFlashAddScore", "anon");
        filterChainDefinitionMap.put("/views/front/flashDetail.html", "anon");
        filterChainDefinitionMap.put("/credits/list/**", "anon");
        filterChainDefinitionMap.put("/flashList", "anon");
        filterChainDefinitionMap.put("/flash/sysList","anon");
        filterChainDefinitionMap.put("/emotion/**","anon");
        filterChainDefinitionMap.put("/share/**", "anon");
        filterChainDefinitionMap.put("/credits/**", "anon");
        filterChainDefinitionMap.put("/recharge", "anon");
        filterChainDefinitionMap.put("/views/front/ImageTest.html","anon");
        filterChainDefinitionMap.put("/loginrefuse","anon");
        filterChainDefinitionMap.put("/emotions/list","anon");
        filterChainDefinitionMap.put("/emotions/detail","anon");
//        分享相关
        filterChainDefinitionMap.put("/shareFlashDetail", "anon");
        filterChainDefinitionMap.put("/shareArticleDetail","anon");
        filterChainDefinitionMap.put("/credits/shareToAddByOpenid","anon");
        filterChainDefinitionMap.put("/credits/toReadFlashAddScoreByOpenid","anon");
//        支付相关
        filterChainDefinitionMap.put("/wx/pay/**", "anon");
//		filterChainDefinitionMap.put("/liker/**","anon");
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/.well-known/**","anon");

        // test
        filterChainDefinitionMap.put("/banner", "anon");
        filterChainDefinitionMap.put("/uploadImage/**","anon");
        filterChainDefinitionMap.put("/getBanner/**", "anon");
        filterChainDefinitionMap.put("/setImage/**","anon");
        filterChainDefinitionMap.put("/**", "jwt,cors,user");


        // 配置不会被拦截的链接 顺序判断,
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        // 从数据库获取动态的权限
        // filterChainDefinitionMap.put("/add", "perms[权限添加]");
        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        //logout这个拦截器是shiro已经实现好了的。
        // 从数据库获取
//		List<SysPermissionInit> list = sysPermissionInitService.selectAll();
//
//		for (SysPermissionInit sysPermissionInit : list) {
//			System.out.println(sysPermissionInit.getUrl());
//			filterChainDefinitionMap.put(sysPermissionInit.getUrl(),sysPermissionInit.getPermission_init());
//		}
        System.out.println("过滤器注入成功！");
        shiroFilterFactoryBean
                .setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
     *
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(3600);
        sessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
        System.out.println(sessionManager + "成功");
        return sessionManager;
    }

    /**
     * 配置session 缓存管理
     * @return
     */
//    @Bean
//    public EnterpriseCacheSessionDAO cacheSessionDAO(){
//        EnterpriseCacheSessionDAO cacheSessionDAO = new EnterpriseCacheSessionDAO();
//        cacheSessionDAO.setCacheManager(ehCacheManager());
//        return cacheSessionDAO;
//    }

//    @Bean
//    public EhCacheManager ehCacheManager() {
//        System.out.println("ShiroConfiguration.getEhCacheManager()");
//        EhCacheManager cacheManager = new EhCacheManager();
//        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
//        return cacheManager;
//    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

//        securityManager.setRealm(myShiroRealm());

        ArrayList<Realm> arrayList = new ArrayList<Realm>();
        arrayList.add(new MyShiroRealm());
        arrayList.add(new JWTShiroRealm());
        securityManager.setRealms(arrayList);
//        securityManager.setCacheManager(ehCacheManager());
        System.out.println("注入MyShiroRealm 成功  CacheManager 成功");
        return securityManager;
    }

    /**
     * 初始化Authenticator
     */
//    @Bean
//    public Authenticator authenticator() {
//        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
//        //设置两个Realm，一个用于用户登录验证和访问权限获取；一个用于jwt token的认证
//        authenticator.setRealms(Arrays.asList(new MyShiroRealm(), new JWTShiroRealm()));
//        //设置多个realm认证策略，一个成功即跳过其它的
//        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
//        return authenticator;
//    }


    /**
     * cookie对象;
     * @return
     */
//    public SimpleCookie rememberMeCookie(){
//        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
//        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
//        simpleCookie.setMaxAge(2592000);
//        return simpleCookie;
//    }

    /**
     * cookie管理对象;记住我功能
     * @return
     */
//    public CookieRememberMeManager rememberMeManager(){
//        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//        cookieRememberMeManager.setCookie(rememberMeCookie());
//        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
//        cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
//        return cookieRememberMeManager;
//    }

    /**
     * 限制同一账号登录同时登录人数控制
     *
     * @return
     */
    public KickoutSessionControlFilter kickoutSessionControlFilter() {
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        //使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
        //这里我们还是用之前shiro使用的redisManager()实现的cacheManager()缓存管理
        //也可以重新另写一个，重新配置缓存时间之类的自定义缓存属性
//        kickoutSessionControlFilter.setCacheManager(ehCacheManager());
        //用于根据会话ID，获取会话进行踢出操作的；
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        //是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序。
        kickoutSessionControlFilter.setKickoutAfter(false);
        //同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
        kickoutSessionControlFilter.setMaxSession(1);
        //被踢出后重定向到的地址；
        kickoutSessionControlFilter.setKickoutUrl("/kickout");
        return kickoutSessionControlFilter;
    }

    /**
     * 禁用session, 不保存用户登录状态。保证每次请求都重新认证。
     * 需要注意的是，如果用户代码里调用Subject.getSession()还是可以用session，如果要完全禁用，要配合下面的noSessionCreation的Filter来实现
     */
    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator(){
        DefaultWebSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }

}
