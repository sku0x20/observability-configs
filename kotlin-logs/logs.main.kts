#!/usr/bin/env kotlin

@file:DependsOn(
    "org.apache.logging.log4j:log4j-api:2.24.3",
    "org.apache.logging.log4j:log4j-core:2.24.3", // runtime only
    "org.apache.logging.log4j:log4j-layout-template-json:2.24.3", // runtime only
    "com.lmax:disruptor:4.0.0", // runtime only
    "pl.tkowalcz.tjahzi:log4j2-appender-nodep:0.9.17"
)

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

System.setProperty("log4j2.contextSelector", "org.apache.logging.log4j.core.async.BasicAsyncLoggerContextSelector")
System.setProperty("log4j2.configurationFile", "file:///D:/projects/observability-configs/kotlin-logs/log4j2.xml")

val logger: Logger = LogManager.getLogger()

logger.info("plain old message")
logger.error("some error message!!!")
logger.error("exception", RuntimeException("runtime exception"))

