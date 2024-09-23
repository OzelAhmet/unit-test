package com.github.ozelahmet.junit.lifecycle;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ResourceHandlerTest {

    private static ResourceHandler resourceHandler;

    @BeforeAll
    static void setUpBeforeAll() {
        System.out.println("BeforeAll: Setting up before all tests.");
        resourceHandler = new ResourceHandler();
    }

    @BeforeEach
    void setUpBeforeEach() {
        System.out.println("BeforeEach: Opening resource before each test.");
        resourceHandler.openResource();
    }

    @AfterEach
    void tearDownAfterEach() {
        System.out.println("AfterEach: Closing resource after each test.");
        resourceHandler.closeResource();
    }

    @AfterAll
    static void tearDownAfterAll() {
        System.out.println("AfterAll: Cleaning up after all tests.");
        resourceHandler = null;
    }

    @Test
    void resourceIsOpenedAndDataRetrieved() {
        System.out.println("Test 1: Testing retrieving data.");
        assertTrue(resourceHandler.isResourceOpened());
    }

    @Test
    void resourceIsOpenedAndDataWritten() {
        System.out.println("Test 2: Testing writing data.");
        assertTrue(resourceHandler.isResourceOpened());
    }

}