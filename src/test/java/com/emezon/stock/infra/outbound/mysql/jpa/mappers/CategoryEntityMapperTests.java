package com.emezon.stock.infra.outbound.mysql.jpa.mappers;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryEntityMapperTests {

    @Test
    void CategoryEntityMapper_shouldHavePrivateConstructor() throws NoSuchMethodException {
        Constructor<CategoryEntityMapper> constructor = CategoryEntityMapper.class.getDeclaredConstructor();
        assertThrows(IllegalAccessException.class, constructor::newInstance);
    }

    @Test
    void CategoryEntityMapper_shouldThrowExceptionWhenInstantiated() throws NoSuchMethodException {
        Constructor<CategoryEntityMapper> constructor = CategoryEntityMapper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

}
