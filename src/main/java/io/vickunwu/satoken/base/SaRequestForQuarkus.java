package io.vickunwu.satoken.base;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.context.model.SaRequest;
import cn.dev33.satoken.context.model.SaResponse;
import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.util.SaFoxUtil;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpServerRequest;

import java.util.Optional;

/**
 * @author vickunwu
 */
public class SaRequestForQuarkus implements SaRequest {

    private final HttpServerRequest req;

    public SaRequestForQuarkus(HttpServerRequest req) {
        this.req = req;
    }

    @Override
    public Object getSource() {
        return req;
    }

    @Override
    public String getParam(String param) {
        return req.getParam(param);
    }

    @Override
    public String getHeader(String key) {
        return req.getHeader(key);
    }

    @Override
    public String getCookieValue(String name) {
        Cookie cookie = this.req.getCookie(name);
        return Optional.ofNullable(cookie).map(Cookie::getValue).orElse(null);
    }

    @Override
    public String getRequestPath() {
        return req.path();
    }

    @Override
    public String getUrl() {
        String currDomain = SaManager.getConfig().getCurrDomain();
        if (!SaFoxUtil.isEmpty(currDomain)) {
            return currDomain + this.getRequestPath();
        }
        return req.scheme() + "://" + req.host() + "/" + this.getRequestPath();
    }

    @Override
    public String getMethod() {
        return req.method().name();
    }

    @Override
    public Object forward(String path) {
        try {
            SaResponse response = SaManager.getSaTokenContextOrSecond().getResponse();
            return response.redirect(req.scheme() + "://" + req.host() + "/" + path);
        } catch (Exception e) {
            throw new SaTokenException(e);
        }
    }
}
