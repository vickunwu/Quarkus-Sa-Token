package io.vickunwu.satoken.context;

import cn.dev33.satoken.context.SaTokenContext;
import cn.dev33.satoken.context.model.SaRequest;
import cn.dev33.satoken.context.model.SaResponse;
import cn.dev33.satoken.context.model.SaStorage;
import io.quarkus.arc.Unremovable;
import io.quarkus.vertx.http.runtime.CurrentVertxRequest;
import io.vertx.ext.web.RoutingContext;
import io.vickunwu.external.spring.utils.PathMatcher;
import io.vickunwu.satoken.base.SaRequestForQuarkus;
import io.vickunwu.satoken.base.SaResponseForQuarkus;
import io.vickunwu.satoken.base.SaStorageForQuarkus;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

/**
 * @author vickunwu
 */
@ApplicationScoped
@Unremovable
public class SaTokenContextForQuarkus implements SaTokenContext {

    @Inject
    PathMatcher matcher;

    @Override
    public SaRequest getRequest() {
        RoutingContext context = CDI.current().select(CurrentVertxRequest.class).get().getCurrent();
        return new SaRequestForQuarkus(context.request());
    }

    @Override
    public SaResponse getResponse() {
        RoutingContext context = CDI.current().select(CurrentVertxRequest.class).get().getCurrent();
        return new SaResponseForQuarkus(context.response(), context);
    }

    @Override
    public SaStorage getStorage() {
        RoutingContext context = CDI.current().select(CurrentVertxRequest.class).get().getCurrent();
        return new SaStorageForQuarkus(context.request());
    }

    @Override
    public boolean matchPath(String pattern, String path) {
        return matcher.match(pattern, path);
    }
}
