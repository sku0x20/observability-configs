import org.slf4j.LoggerFactory
import java.util.*


// https://opentelemetry.io/docs/specs/otel/logs/
// https://github.com/open-telemetry/opentelemetry-java-examples/tree/main/log-appender
// https://opentelemetry.io/docs/collector/configuration/#receivers
// https://github.com/open-telemetry/opentelemetry-java-instrumentation/tree/main/instrumentation/logback/logback-mdc-1.0/library
// https://github.com/logfellow/logstash-logback-encoder?tab=readme-ov-file#wait-strategy


class Main {

    private val logger = LoggerFactory.getLogger(javaClass)
    private val random = Random()

    fun log() {
        logger.info("some random number {}!", random.nextInt().toString())
        val vThread = Thread.ofVirtual().name("virtual").start {
            logger.info("some random number {}!", random.nextInt().toString())
        }
        vThread.join()
    }
}

fun main(args: Array<String>) {
    initializeOpenTelemetry()
    io.opentelemetry.instrumentation.logback.appender.v1_0.OpenTelemetryAppender.install(
        io.opentelemetry.api.GlobalOpenTelemetry.get()
    )
    val main = Main()
    main.log()
    Thread.sleep(2000)
    main.log()
    Thread.sleep(2000)
}

fun initializeOpenTelemetry() {
    val resource = io.opentelemetry.sdk.resources.Resource.getDefault().toBuilder()
        .put(io.opentelemetry.semconv.ServiceAttributes.SERVICE_NAME, "slf4j-demo")
        .build()
    val logRecordExporter = io.opentelemetry.exporter.otlp.logs.OtlpGrpcLogRecordExporter.builder()
        .setEndpoint("http://localhost:4317")
        .build()
    val logProcessor = io.opentelemetry.sdk.logs.export.BatchLogRecordProcessor
        .builder(logRecordExporter)
        .build()
    val loggerProvider = io.opentelemetry.sdk.logs.SdkLoggerProvider.builder()
        .addResource(resource)
        .addLogRecordProcessor(logProcessor)
        .build()
    val sdk = io.opentelemetry.sdk.OpenTelemetrySdk.builder()
        .setLoggerProvider(loggerProvider)
        .buildAndRegisterGlobal()
}
