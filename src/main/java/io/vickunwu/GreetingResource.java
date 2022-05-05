package io.vickunwu;

import cn.dev33.satoken.stp.StpUtil;
import io.smallrye.mutiny.Uni;
import io.vickunwu.satoken.annotation.QSaCheckRole;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @QSaCheckRole("admin")
    public Uni<?> hello() {
        return Uni.createFrom().item(StpUtil.getTokenInfo());
    }
}