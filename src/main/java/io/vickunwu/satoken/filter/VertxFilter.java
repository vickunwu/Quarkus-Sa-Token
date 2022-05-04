package io.vickunwu.satoken.filter;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import io.quarkus.logging.Log;
import io.quarkus.vertx.web.RouteFilter;
import io.vertx.ext.web.RoutingContext;
import io.vickunwu.satoken.config.SaRouteConfigForQuarkus;
import io.vickunwu.satoken.config.SaTokenConfigForQuarkus;

import javax.enterprise.inject.spi.CDI;

/**
 * sa-token request filter
 *
 * apply to both resteasy non/reactive req
 *
 * @author vickunwu
 */
@SuppressWarnings("all")
public class VertxFilter {

    @RouteFilter
    void saTokenFilter(RoutingContext rc) {
        SaRouteConfigForQuarkus config = CDI.current().select(SaTokenConfigForQuarkus.class).get().route();
        if (config.excludePaths().isPresent() && SaRouter.match(config.excludePaths().get()).isHit) {
            rc.next();
        } else {
            if (SaRouter.match(config.includePaths()).isHit) {
                try {
                    StpUtil.checkLogin();
                    rc.next();
                } catch (NotLoginException e) {
                    rc.response().setStatusCode(401).putHeader("Content-Type", "text/plain;charset=utf-8").end(e.getMessage());
                    return;
                }
            }
        }
    }
}
