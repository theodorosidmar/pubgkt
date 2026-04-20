# pubgkt

Kotlin Multiplatform library for the official [PUBG API](https://documentation.pubg.com/en/introduction.html).

## Supported Platforms

| Module                        | JVM | Android | iOS | JS/Node |
|-------------------------------|:---:|:-------:|:---:|:-------:|
| `bom`                         | ✓   |         |     |         |
| `clans`                       | ✓   |         |     |         |
| `core` (All modules together) | ✓   |         |     |         |
| `matches`                     | ✓   |         |     |         |
| `mastery`                     | ✓   |         |     |         |
| `players`                     | ✓   |         |     |         |

## Getting Started

### Obtain an API key

Register at the [PUBG Developer Portal](https://developer.pubg.com) to get your API key.

### Add the dependency

**Gradle (Kotlin DSL)**
```kotlin
repositories {
    mavenLocal() // during development
}

dependencies {
    implementation("pubgkt:players:0.1.0-SNAPSHOT")
}
```

**Maven**
```xml
<dependency>
    <groupId>pubgkt</groupId>
    <artifactId>players-jvm</artifactId>
    <version>0.1.0-SNAPSHOT</version>
</dependency>
```

### Usage — Kotlin

```kotlin
val api = PubgApi(apiKey = "your-api-key", platform = Platform.STEAM)

// Single player by account ID
val player = api.getPlayerByAccountId("account.abc123")

// Multiple players by account IDs (up to 10)
val players = api.getPlayersById("account.abc123", "account.def456")

// Multiple players by display names (up to 10)
val named = api.getPlayersByNames("sparkingg", "TGLTN")
```

### Usage — Java

```java
PubgApi api = new PubgApi("your-api-key");
api.setPlatform(Platform.STEAM);

// Bridge suspend functions with runBlocking
Player player = BuildersKt.runBlocking(
    EmptyCoroutineContext.INSTANCE,
    (scope, cont) -> GetPlayerByAccountIdKt.getPlayerByAccountId(api, "account.abc123", cont)
);
```

## Rate Limiting

The PUBG API enforces a default limit of **10 requests per minute** per API key. pubgkt handles
this automatically via the `RateLimiter` interface.

The default implementation is `DelayRateLimiter`, which reads the `X-RateLimit-Remaining` and
`X-RateLimit-Reset` response headers and delays subsequent requests when the limit is exhausted.

```kotlin
// Default — proactive delay when the limit is about to be hit
val api = PubgApi("your-api-key")

// No rate limiting — manage it yourself
val api = PubgApi("your-api-key", rateLimiter = RateLimiter.None)

// Custom implementation
val api = PubgApi("your-api-key", rateLimiter = MyRateLimiter())
```

If the API returns HTTP 429 regardless (e.g. another instance shares your key),
`RateLimitExceededException` is thrown with the reset timestamp.

## API Coverage

| Endpoint group | Status  |
|----------------|---------|
| Players        | ✓ Done  |
| Matches        | ✓ Done  |
| Season stats   | Planned |
| Lifetime stats | Planned |
| Mastery        | ✓ Done  |
| Clans          | ✓ Done  |
| Leaderboards   | Planned |
| Status         | Planned |

## Platform Roadmap

| Platform | Engine  | Status  |
|----------|---------|---------|
| JVM      | CIO     | ✓ Done  |
| Android  | OkHttp  | Planned |
| iOS      | Darwin  | Planned |
| JS/Node  | Js      | Planned |

## Samples

```bash
# Kotlin
./gradlew :samples:run --args="<api-key> [accountId] [playerName]"

# Java
./gradlew :samples:runJava --args="<api-key> [accountId] [playerName]"
```

## License

[MIT](license)
