package io.vickunwu.satoken.config.adapter;

import cn.dev33.satoken.config.SaSsoConfig;
import io.vickunwu.satoken.config.SaSsoConfigForQuarkus;

/**
 * SaSsoConfigAdapter
 *
 * @author nayan
 * @date 2022/4/6 6:27 PM
 */
public class SaSsoConfigAdapter extends SaSsoConfig {
    private final SaSsoConfigForQuarkus config;

    public SaSsoConfigAdapter(SaSsoConfigForQuarkus config) {
        this.config = config;
    }

    @Override
    public long getTicketTimeout() {
        return config.ticketTimeout();
    }

    @Override
    public String getAllowUrl() {
        return config.allowUrl();
    }

    @Override
    public Boolean getIsSlo() {
        return config.isSlo();
    }

    @Override
    public Boolean getIsHttp() {
        return config.isHttp();
    }

    @Override
    public String getSecretkey() {
        return config.secretKey().orElse(null);
    }

    @Override
    public String getAuthUrl() {
        return config.authUrl().orElse(null);
    }

    @Override
    public String getCheckTicketUrl() {
        return config.checkTicketUrl().orElse(null);
    }

    @Override
    public String getUserinfoUrl() {
        return config.userInfoUrl().orElse(null);
    }

    @Override
    public String getSloUrl() {
        return config.sloUrl().orElse(null);
    }

    @Override
    public String getSsoLogoutCall() {
        return config.ssoLogoutCall().orElse(null);
    }
}
