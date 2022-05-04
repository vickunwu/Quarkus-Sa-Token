package io.vickunwu.satoken.config;

import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;

import java.util.List;
import java.util.Optional;

public interface SaRouteConfigForQuarkus {

    @WithName("interceptor")
    @WithDefault("true")
    Boolean interceptor();

    @WithName("include-paths")
    @WithDefault("/**")
    List<String> includePaths();

    @WithName("exclude-paths")
    Optional<List<String>> excludePaths();

}
