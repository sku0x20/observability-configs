#!/usr/bin/env kotlin

@file:DependsOn(
    "org.apache.logging.log4j:log4j-api:2.24.3",
    "org.apache.logging.log4j:log4j-core:2.24.3",
    "org.apache.logging.log4j:log4j-layout-template-json:2.24.3"
)

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

System.setProperty("log4j2.configurationFile", "kotlin-logs/log4j2.xml")

val logger: Logger = LogManager.getLogger()

logger.info("test message")
