package io.vickunwu.satoken.runtime;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.action.SaTokenAction;
import cn.dev33.satoken.basic.SaBasicTemplate;
import cn.dev33.satoken.basic.SaBasicUtil;
import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.context.second.SaTokenSecondContextCreator;
import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.id.SaIdTemplate;
import cn.dev33.satoken.id.SaIdUtil;
import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.sso.SaSsoTemplate;
import cn.dev33.satoken.sso.SaSsoUtil;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.temp.SaTempInterface;
import io.quarkus.arc.Arc;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.annotations.Recorder;
import io.vickunwu.satoken.context.SaTokenContextForQuarkus;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.CDI;
import java.util.function.Consumer;

/**
 * SaTokenRecorder
 *
 * @author nayan
 * @date 2022/4/7 2:08 PM
 */
@Recorder
public class SaTokenRecorder {

    public void injectAllBean(@Observes StartupEvent event) {
        Arc.container().instance(SaTokenProducer.class).get();
        setConfig();
        setSaTokenDao();
        setStpInterface();
        setSaTokenAction();
        setSaTokenContext();
        setSaTokenSecondContext();
        setSaTokenListener();
        setSaTemp();
        setSaIdTemplate();
        setSaSsoBasicTemplate();
        setSaSsoTemplate();
        setStpLogic();
    }

    /**
     * 注入配置Bean
     *
     */
    public void setConfig() {
        injectBean(SaTokenConfig.class, SaManager::setConfig);
    }

    /**
     * 注入持久化Bean
     *
     */
    public void setSaTokenDao() {
        injectBean(SaTokenDao.class, SaManager::setSaTokenDao);
    }

    /**
     * 注入权限认证Bean
     *
     */
    public void setStpInterface() {
        injectBean(StpInterfaceImpl.class, SaManager::setStpInterface);
    }

    /**
     * 注入框架行为Bean
     *
     */
    public void setSaTokenAction() {
        injectBean(SaTokenAction.class, SaManager::setSaTokenAction);
    }

    /**
     * 注入上下文Bean
     *
     */
    public void setSaTokenContext() {
        injectBean(SaTokenContextForQuarkus.class, SaManager::setSaTokenContext);
    }

    /**
     * 注入二级上下文Bean
     *
     */
    public void setSaTokenSecondContext() {
        injectBean(SaTokenSecondContextCreator.class, context -> SaManager.setSaTokenSecondContext(context.create()));
    }

    /**
     * 注入侦听器Bean
     *
     */
    public void setSaTokenListener() {
        injectBean(SaTokenListener.class, SaManager::setSaTokenListener);
    }

    /**
     * 注入临时令牌验证模块 Bean
     *
     */
    public void setSaTemp() {
        injectBean(SaTempInterface.class, SaManager::setSaTemp);
    }

    /**
     * 注入 Sa-Id-Token 模块 Bean
     *
     */
    public void setSaIdTemplate() {
        injectBean(SaIdTemplate.class, template -> SaIdUtil.saIdTemplate = template);
    }

    /**
     * 注入 Sa-Token Http Basic 认证模块
     *
     */
    public void setSaSsoBasicTemplate() {
        injectBean(SaBasicTemplate.class, template -> SaBasicUtil.saBasicTemplate = template);
    }

    /**
     * 注入 Sa-Token-SSO 单点登录模块 Bean
     *
     */
    public void setSaSsoTemplate() {
        injectBean(SaSsoTemplate.class, template -> SaSsoUtil.saSsoTemplate = template);
    }

    /**
     * 注入自定义的 StpLogic
     *
     */
    public void setStpLogic() {
        injectBean(StpLogic.class, StpUtil::setStpLogic);
    }

    public <T> void injectBean(Class<T> clazz, Consumer<T> consumer) {
        CDI.current().select(clazz).stream().findFirst().ifPresent(consumer);
    }

}
