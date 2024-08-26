package com.emezon.stock.infra.output.mysql.jpa.mappers;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BrandEntityMapperTests {

    @Test
    void BrandEntityMapper_shouldHavePrivateConstructor() throws NoSuchMethodException {
        Constructor<BrandEntityMapper> constructor = BrandEntityMapper.class.getDeclaredConstructor();
        assertThrows(IllegalAccessException.class, constructor::newInstance);
    }

    @Test
    void BrandEntityMapper_shouldThrowExceptionWhenInstantiated() throws NoSuchMethodException {
        Constructor<BrandEntityMapper> constructor = BrandEntityMapper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

}
