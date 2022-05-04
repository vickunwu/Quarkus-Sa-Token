package io.vickunwu.satoken.interceptor;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpLogic;
import io.quarkus.arc.Priority;
import io.vickunwu.satoken.annotation.QSaCheckRole;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;

/**
 * @author vickunwu
 */
@QSaCheckRole
@Priority(Interceptor.Priority.APPLICATION+1)
@Interceptor
class SaCheckRoleInterceptor {

    @AroundInvoke
    Object intercept(InvocationContext context) throws Exception {

        // obtain the method needs to be processed
        Method method = context.getMethod();
        // validate the class annotation first
        Class<?> declaringClass = method.getDeclaringClass();

        QSaCheckRole classAnnotation = declaringClass.getAnnotation(QSaCheckRole.class);
        QSaCheckRole methodAnnotation = method.getAnnotation(QSaCheckRole.class);
        if (classAnnotation != null) {
            checkByAnnotation(classAnnotation);
        }
        if (methodAnnotation != null) {
            checkByAnnotation(methodAnnotation);
        }
        return context.proceed();
    }

    private void checkByAnnotation(QSaCheckRole anno) {
        // check whether login type exists
        StpLogic stpLogic = SaManager.getStpLogic(anno.type());
        String[] roles = anno.value();
        if(anno.mode() == SaMode.AND) {
            stpLogic.checkRoleAnd(roles);
        } else {
            stpLogic.checkRoleOr(roles);
        }
    }
}
