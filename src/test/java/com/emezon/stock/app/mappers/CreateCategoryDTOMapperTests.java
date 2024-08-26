package com.emezon.stock.app.mappers;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateCategoryDTOMapperTests {

    @Test
    void CreateCategoryDTOMapper_shouldHavePrivateConstructor() throws NoSuchMethodException {
        Constructor<CreateCategoryDTOMapper> constructor = CreateCategoryDTOMapper.class.getDeclaredConstructor();
        assertThrows(IllegalAccessException.class, constructor::newInstance);
    }

    @Test
    void CreateCategoryDTOMapper_shouldThrowExceptionWhenInstantiated() throws NoSuchMethodException {
        Constructor<CreateCategoryDTOMapper> constructor = CreateCategoryDTOMapper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

}
