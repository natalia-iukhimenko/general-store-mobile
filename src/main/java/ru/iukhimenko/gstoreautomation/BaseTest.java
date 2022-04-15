package ru.iukhimenko.gstoreautomation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    protected static void testSetup() {
        MobileDriver.getInstance().setDriver();
    }

    @AfterAll
    protected static void testTeardown() {
        MobileDriver.getInstance().closeDriver();
    }
}
