package edu.quote.collection.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class BaseConverter {

    protected <T, E> E convert(T source, Class<E> targetClass) {
        E target = getClassInstance(targetClass);
        if (Objects.isNull(source) || Objects.isNull(target)) {
            return null;
        }
        try {
            BeanUtils.copyProperties(source, target);
        } catch (BeansException e) {
            log.error("Error while mapping.", e);
        }
        return target;
    }

    private <T> T getClassInstance(Class<T> targetClass) {
        T classInstance = null;
        try {
            classInstance = targetClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            log.error("Destination class is invalid.", e);
        }
        return classInstance;
    }
}
