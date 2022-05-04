package io.vickunwu.satoken.config;

import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;

import java.util.Optional;

public interface SaCookieConfigForQuarkus {

    @WithName("domain")
    Optional<String> domain();

    @WithName("path")
    @WithDefault("/")
    String path();

    @WithName("secure")
    @WithDefault("false")
    Boolean secure();

    @WithName("http-only")
    @WithDefault("false")
    Boolean httpOnly();

    @WithName("same-site")
    @WithDefault("Lax")
    String sameSite();

}
