package dev.pubgkt

suspend fun main(args: Array<String>) {
    val apiKey = args.getOrNull(0) ?: error("Usage: RetrySample <api-key>")

    println("=== No retry (default) ===")
    run {
        val api = PubgApi(apiKey, retry = NoRetry)
        println("API is up: ${api.isUp()}")
    }

    println("\n=== Retry with NoBackoff (immediate retries) ===")
    run {
        val api = PubgApi(apiKey, retry = Retry(maxRetries = 3, backoff = NoBackoff))
        println("API is up: ${api.isUp()}")
    }

    println("\n=== Retry with ExponentialBackoff (default settings) ===")
    run {
        val api = PubgApi(apiKey, retry = Retry(maxRetries = 3, backoff = ExponentialBackoff()))
        println("API is up: ${api.isUp()}")
    }

    println("\n=== Retry with ExponentialBackoff (custom settings) ===")
    run {
        val api =
            PubgApi(
                apiKey,
                retry =
                    Retry(
                        maxRetries = 5,
                        backoff =
                            ExponentialBackoff(
                                base = 2.0,
                                baseDelayMs = 500,
                                maxDelayMs = 30_000,
                                randomizationMs = 200,
                            ),
                    ),
            )
        println("API is up: ${api.isUp()}")
        println("Seasons: ${api.seasons(Platform.STEAM).size}")
    }
}
