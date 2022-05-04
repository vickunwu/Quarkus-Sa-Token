package io.vickunwu.satoken.base;

import cn.dev33.satoken.context.model.SaResponse;
import cn.dev33.satoken.exception.SaTokenException;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

/**
 * @author vickunwu
 */
public class SaResponseForQuarkus implements SaResponse {

    private final HttpServerResponse resp;

    private final RoutingContext routerContext;

    public SaResponseForQuarkus(HttpServerResponse resp, RoutingContext routerContext) {
        this.resp = resp;
        this.routerContext = routerContext;
    }

    @Override
    public Object getSource() {
        return resp;
    }

    @Override
    public SaResponse setStatus(int httpStatus) {
        resp.setStatusCode(httpStatus);
        return this;
    }

    @Override
    public SaResponse setHeader(String name, String value) {
        resp.putHeader(name, value);
        return this;
    }

    @Override
    public SaResponse addHeader(String name, String value) {
        resp.putHeader(name, value);
        return this;
    }

    @Override
    public Object redirect(String url) {
        try {
            routerContext.redirect(url);
        } catch (Exception e) {
            throw new SaTokenException(e);
        }
        return null;
    }
}
