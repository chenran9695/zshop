package com.cr.zshop.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author ：cr
 * @date ：Created in 2019/10/17 17:48
 * @description：$
 * @modified By：cr
 * @version: $
 */
public class SpringBeanHolder implements ApplicationContextAware {
    private static ApplicationContext ac;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }

    public static Object getBean(String beanName){
        return ac.getBean(beanName);
    }


}
