package io.vickunwu.satoken.filter;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import io.quarkus.vertx.web.RouteFilter;
import io.vertx.ext.web.RoutingContext;
import io.vickunwu.satoken.config.SaRouteConfigForQuarkus;
import io.vickunwu.satoken.config.SaTokenConfigForQuarkus;

import javax.enterprise.inject.spi.CDI;

/**
 * sa-token request filter
 * <p>
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
            return;
        }

        if (SaRouter.match(config.includePaths()).isHit) {
            try {
                StpUtil.checkLogin();
                rc.next();
            } catch (NotLoginException e) {
                rc.response().setStatusCode(401)
                        .putHeader("Content-Type", "application/json;charset=utf-8")
                        .end(SaResult.code(401).setMsg(e.getMessage()).toString());
                return;
            }
        }
    }
}
