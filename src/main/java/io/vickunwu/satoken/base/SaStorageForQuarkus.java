package io.vickunwu.satoken.base;

import cn.dev33.satoken.context.model.SaStorage;
import io.vertx.core.http.HttpServerRequest;

/**
 * @author vickunwu
 */
public class SaStorageForQuarkus implements SaStorage {

    private final HttpServerRequest req;

    public SaStorageForQuarkus(HttpServerRequest req) {
        this.req = req;
    }

    @Override
    public Object getSource() {
        return req;
    }

    @Override
    public void set(String key, Object value) {
        req.formAttributes().set(key, String.valueOf(value));
    }

    @Override
    public Object get(String key) {
        return req.getFormAttribute(key);
    }

    @Override
    public void delete(String key) {
        req.formAttributes().remove(key);
    }
}
