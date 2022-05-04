package io.vickunwu.satoken.exception.mapper;

import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

/**
 * @author vickunwu
 */
public class ExceptionMapper {

    @ServerExceptionMapper
    public RestResponse<?> handleNotPermissionException(NotPermissionException e) {
        return RestResponse.status(RestResponse.Status.FORBIDDEN, e.getMessage());
    }

    @ServerExceptionMapper
    public RestResponse<?> handleNotRoleException(NotRoleException e) {
        return RestResponse.status(RestResponse.Status.FORBIDDEN, e.getMessage());
    }

}
