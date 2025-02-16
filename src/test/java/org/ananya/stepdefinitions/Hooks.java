package org.ananya.stepdefinitions;

import io.cucumber.java.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @BeforeAll
    public static void setUp() {
        logger.info("Started executing the tests======================================");
    }

    @AfterAll
    public static void cleanUp() {
        logger.info("Finished executing the tests======================================");
    }

    @Before
    public void scenarioSetUp(Scenario scenario) {
        logger.info("Started executing the scenario : {} : --------------------------", scenario.getName());
    }

    @After
    public void scenarioCleanUp(Scenario scenario) {
        logger.info("Finished executing the scenario : {}  : --------------------------", scenario.getName());
    }
}
