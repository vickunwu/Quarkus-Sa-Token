package io.vickunwu.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import io.smallrye.mutiny.Uni;
import io.vickunwu.utils.RedissonUtils;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * @author vickunwu
 */
@Path("/auth")
public class LoginResource {

    @POST
    @Path("/doLogin")
    @Produces(MediaType.APPLICATION_JSON)
    public SaResult doLogin(@QueryParam("name") String name, @QueryParam("pwd") String pwd) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        // if ("zhang".equals(name) && "123456".equals(pwd)) {
        //     StpUtil.login(10001);
        //     return SaResult.ok("登录成功").set("token", StpUtil.getTokenValue());
        // }
        return SaResult.error("登录失败");
    }
}
