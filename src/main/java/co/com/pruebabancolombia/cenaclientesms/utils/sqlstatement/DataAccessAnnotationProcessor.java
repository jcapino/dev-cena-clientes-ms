package co.com.pruebabancolombia.cenaclientesms.utils.sqlstatement;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * Procesador de la anotación de SqlStatement
 */
@Component
public class DataAccessAnnotationProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        this.configureFieldInjection(bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }

    private void configureFieldInjection(Object bean) {
        Class<?> managedBeanClass = bean.getClass();
        ReflectionUtils.FieldCallback fieldCallback = new DataAccessFieldCallback(bean);
        ReflectionUtils.doWithFields(managedBeanClass, fieldCallback);
    }
}
