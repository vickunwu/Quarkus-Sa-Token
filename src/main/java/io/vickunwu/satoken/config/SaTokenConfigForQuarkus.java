package io.vickunwu.satoken.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;

import java.util.Optional;

/**
 * @author vickunwu
 */
@ConfigMapping(prefix = "sa-token")
public interface SaTokenConfigForQuarkus {

    @WithName("token-name")
    @WithDefault("satoken")
    String tokenName();

    @WithName("timeout")
    @WithDefault("2592000")
    Long timeout();

    @WithName("activity-timeout")
    @WithDefault("-1")
    Long activityTimeout();

    @WithName("is-concurrent")
    @WithDefault("true")
    Boolean isConcurrent();

    @WithName("is-share")
    @WithDefault("true")
    Boolean isShare();

    @WithName("is-read-body")
    @WithDefault("true")
    Boolean isReadBody();

    @WithName("is-read-head")
    @WithDefault("true")
    Boolean isReadHead();

    @WithName("is-read-cookie")
    @WithDefault("true")
    Boolean isReadCookie();

    @WithName("token-style")
    @WithDefault("uuid")
    String tokenStyle();

    @WithName("data-refresh-period")
    @WithDefault("30")
    Integer dataRefreshPeriod();

    @WithName("token-session-check-login")
    @WithDefault("true")
    Boolean tokenSessionCheckLogin();

    @WithName("auto-renew")
    @WithDefault("true")
    Boolean autoRenew();

    @WithName("token-prefix")
    Optional<String> tokenPrefix();

    @WithName("is-print")
    @WithDefault("false")
    Boolean isPrint();

    @WithName("is-log")
    @WithDefault("false")
    Boolean isLog();

    @WithName("jwt-secret-key")
    Optional<String> jwtSecretKey();

    @WithName("id-token-timeout")
    @WithDefault("86400")
    Long idTokenTimeout();

    @WithName("basic")
    Optional<String> basic();

    @WithName("curr-domain")
    Optional<String> currDomain();

    @WithName("check-id-token")
    Optional<Boolean> checkIdToken();

    /**
     * Cookie配置对象
     */
    SaCookieConfigForQuarkus cookie();

    /**
     * SSO单点登录配置对象
     */
    SaSsoConfigForQuarkus sso();


    /**
     * 路由拦截配置
     */
    SaRouteConfigForQuarkus route();

}
