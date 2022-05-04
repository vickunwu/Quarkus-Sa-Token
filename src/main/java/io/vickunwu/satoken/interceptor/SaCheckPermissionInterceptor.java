package io.vickunwu.satoken.interceptor;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaFoxUtil;
import io.quarkus.arc.Priority;
import io.vickunwu.satoken.annotation.QSaCheckPermission;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author vickunwu
 */
@QSaCheckPermission
@Priority(Interceptor.Priority.APPLICATION+1)
@Interceptor
public class SaCheckPermissionInterceptor {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

        // obtain the method needs to be processed
        Method method = context.getMethod();
        // validate the class annotation first
        Class<?> declaringClass = method.getDeclaringClass();

        QSaCheckPermission classAnnotation = declaringClass.getAnnotation(QSaCheckPermission.class);
        QSaCheckPermission methodAnnotation = method.getAnnotation(QSaCheckPermission.class);
        if (classAnnotation != null) {
            checkByAnnotation(classAnnotation);
        }
        if (methodAnnotation != null) {
            checkByAnnotation(methodAnnotation);
        }
        return context.proceed();
    }

    private void checkByAnnotation(QSaCheckPermission anno) {
        // check whether login type exists
        StpLogic stpLogic = SaManager.getStpLogic(anno.type());
        String[] permissions = anno.value();
        try {
            if(anno.mode() == SaMode.AND) {
                stpLogic.checkPermissionAnd(permissions);
            } else {
                stpLogic.checkPermissionOr(permissions);
            }
        } catch (NotPermissionException e) {
            // as the permission validation fails, we can start the role validation
            if(anno.orRole().length > 0) {
                for (String role : anno.orRole()) {
                    String[] rArr = SaFoxUtil.convertStringToArray(role);
                    // if the converted role arr passes the validation, we call it a success and return
                    if(stpLogic.hasRoleAnd(rArr)) {
                        return;
                    }
                }
            }
            throw e;
        }
    }


}
