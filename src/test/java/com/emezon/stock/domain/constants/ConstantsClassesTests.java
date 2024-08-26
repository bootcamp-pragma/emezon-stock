package com.emezon.stock.domain.constants;

import com.emezon.stock.domain.common.constants.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConstantsClassesTests {

    @Test
    void BrandConstraints_shouldHavePrivateConstructor() throws NoSuchMethodException {
        Constructor<BrandConstraints> constructor = BrandConstraints.class.getDeclaredConstructor();
        assertThrows(IllegalAccessException.class, constructor::newInstance);
    }

    @Test
    void BrandConstraints_shouldThrowExceptionWhenInstantiated() throws NoSuchMethodException {
        Constructor<BrandConstraints> constructor = BrandConstraints.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    void CategoryConstraints_shouldHavePrivateConstructor() throws NoSuchMethodException {
        Constructor<CategoryConstraints> constructor = CategoryConstraints.class.getDeclaredConstructor();
        assertThrows(IllegalAccessException.class, constructor::newInstance);
    }

    @Test
    void CategoryConstraints_shouldThrowExceptionWhenInstantiated() throws NoSuchMethodException {
        Constructor<CategoryConstraints> constructor = CategoryConstraints.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    void BrandErrorMessages_shouldHavePrivateConstructor() throws NoSuchMethodException {
        Constructor<BrandErrorMessages> constructor = BrandErrorMessages.class.getDeclaredConstructor();
        assertThrows(IllegalAccessException.class, constructor::newInstance);
    }

    @Test
    void BrandErrorMessages_shouldThrowExceptionWhenInstantiated() throws NoSuchMethodException {
        Constructor<BrandErrorMessages> constructor = BrandErrorMessages.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    void CategoryErrorMessages_shouldHavePrivateConstructor() throws NoSuchMethodException {
        Constructor<CategoryErrorMessages> constructor = CategoryErrorMessages.class.getDeclaredConstructor();
        assertThrows(IllegalAccessException.class, constructor::newInstance);
    }

    @Test
    void CategoryErrorMessages_shouldThrowExceptionWhenInstantiated() throws NoSuchMethodException {
        Constructor<CategoryErrorMessages> constructor = CategoryErrorMessages.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    void PaginatedResponseConstraints_shouldHavePrivateConstructor() throws NoSuchMethodException {
        Constructor<PaginatedResponseConstraints> constructor = PaginatedResponseConstraints.class.getDeclaredConstructor();
        assertThrows(IllegalAccessException.class, constructor::newInstance);
    }

    @Test
    void PaginatedResponseConstraints_shouldThrowExceptionWhenInstantiated() throws NoSuchMethodException {
        Constructor<PaginatedResponseConstraints> constructor = PaginatedResponseConstraints.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

}
