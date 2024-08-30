package com.emezon.stock.app.mappers;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BrandDTOMapperTests {

    @Test
    void CreateBrandDTOMapper_shouldHavePrivateConstructor() throws NoSuchMethodException {
        Constructor<CategoryDTOMapper> constructor = CategoryDTOMapper.class.getDeclaredConstructor();
        assertThrows(IllegalAccessException.class, constructor::newInstance);
    }

    @Test
    void CreateBrandDTOMapper_shouldThrowExceptionWhenInstantiated() throws NoSuchMethodException {
        Constructor<CategoryDTOMapper> constructor = CategoryDTOMapper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

}
