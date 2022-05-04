package io.vickunwu.satoken.annotation;

import cn.dev33.satoken.annotation.SaMode;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

/**
 * @author vickunwu
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@InterceptorBinding
public @interface QSaCheckRole {

    /**
     * 需要校验的角色标识
     * @return 需要校验的角色标识
     */
    @Nonbinding String [] value() default {};

    /**
     * 验证模式：AND | OR，默认AND
     * @return 验证模式
     */
    @Nonbinding SaMode mode() default SaMode.AND;

    /**
     * 账号类型
     * <p> 建议使用常量，避免因错误拼写带来的bug
     * @return see note
     */
    @Nonbinding String type() default "";

}
