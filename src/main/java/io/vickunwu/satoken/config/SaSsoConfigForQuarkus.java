package io.vickunwu.satoken.config;

import io.smallrye.common.constraint.Nullable;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;

import java.util.Optional;

/**
 * @author vickunwu
 */
public interface SaSsoConfigForQuarkus {

    @WithName("ticket-timeout")
    @WithDefault("300")
    Long ticketTimeout();

    @WithName("allow-url")
    @WithDefault("*")
    String allowUrl();

    @WithName("is-slo")
    @WithDefault("false")
    Boolean isSlo();

    @WithName("is-http")
    @WithDefault("false")
    Boolean isHttp();

    @WithName("secretkey")
    Optional<String> secretKey();

    @WithName("auth-url")
    Optional<String> authUrl();

    @WithName("check-ticket-url")
    Optional<String> checkTicketUrl();

    @WithName("userinfo-url")
    Optional<String> userInfoUrl();

    @WithName("slo-url")
    Optional<String> sloUrl();

    @WithName("ssoLogoutCall")
    Optional<String> ssoLogoutCall();

}
