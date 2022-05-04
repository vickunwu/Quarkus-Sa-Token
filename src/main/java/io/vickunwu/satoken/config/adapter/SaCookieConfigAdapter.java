package io.vickunwu.satoken.config.adapter;

import cn.dev33.satoken.config.SaCookieConfig;
import io.vickunwu.satoken.config.SaCookieConfigForQuarkus;

/**
 * SaCookieConfigAdapter
 *
 * @author nayan
 * @date 2022/4/6 6:47 PM
 */
public class SaCookieConfigAdapter extends SaCookieConfig {

    private final SaCookieConfigForQuarkus config;

    public SaCookieConfigAdapter(SaCookieConfigForQuarkus config) {
        this.config = config;
    }

    @Override
    public String getDomain() {
        return config.domain().orElse(null);
    }

    @Override
    public String getPath() {
        return config.path();
    }

    @Override
    public Boolean getSecure() {
        return config.secure();
    }

    @Override
    public Boolean getHttpOnly() {
        return config.httpOnly();
    }

    @Override
    public String getSameSite() {
        return config.sameSite();
    }

}
