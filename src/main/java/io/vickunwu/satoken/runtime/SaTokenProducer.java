package io.vickunwu.satoken.runtime;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.context.SaTokenContext;
import io.quarkus.arc.DefaultBean;
import io.quarkus.arc.Unremovable;
import io.vickunwu.satoken.config.SaTokenConfigForQuarkus;
import io.vickunwu.satoken.config.adapter.SaTokenConfigAdapter;
import io.vickunwu.satoken.context.SaTokenContextForQuarkus;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * SaTokenProvider
 *
 * @author nayan
 * @date 2022/4/6 3:47 PM
 */
@ApplicationScoped
public class SaTokenProducer {

    private final SaTokenConfigForQuarkus config;

    public SaTokenProducer(SaTokenConfigForQuarkus config) {
        this.config = config;
    }

    @Produces
    @DefaultBean
    @Unremovable
    @ApplicationScoped
    public SaTokenConfig config() {
        return new SaTokenConfigAdapter(config);
    }

    @Produces
    @DefaultBean
    @Unremovable
    @ApplicationScoped
    public SaTokenContext saTokenContextForQuarkus() {
        return new SaTokenContextForQuarkus();
    }
}
