![Coverage](https://img.shields.io/endpoint?url=https://theodorosidmar.github.io/pubgkt/coverage/common.json)

# pubgkt – common

Core module providing the `PubgApi` client, platform enums, seasons, rate limiting, and retry configuration.

**[API Reference](https://theodorosidmar.github.io/pubgkt/common/)** | **[PUBG Official Docs](https://documentation.pubg.com/en/status-endpoint.html)**

## Installation

<details>
<summary><strong>Gradle Kotlin DSL</strong></summary>

```kotlin
dependencies {
    implementation("dev.pubgkt:common:1.0.1")
}
```

</details>

<details>
<summary><strong>Gradle Groovy</strong></summary>

```groovy
dependencies {
    implementation 'dev.pubgkt:common:1.0.1'
}
```

</details>

<details>
<summary><strong>Maven</strong></summary>

```xml
<dependency>
    <groupId>dev.pubgkt</groupId>
    <artifactId>common-jvm</artifactId>
    <version>1.0.1</version>
</dependency>
```

</details>

<details>
<summary><strong>npm</strong></summary>

```bash
npm install @pubgkt/common
```

</details>

<details>
<summary><strong>Swift Package Manager</strong></summary>

Included in the `pubgkt` XCFramework — no separate import needed.

```swift
import PubgKt
```

</details>

## API Reference

| Function | Description |
|----------|-------------|
| `PubgApi.isUp()` | Check if the PUBG API is up and healthy |
| `PubgApi.seasons(platform)` | Get available seasons by platform |
| `PubgApi.seasons(platformRegion)` | Get available seasons by platform region |
| `List<Season>.currentOrNull()` | Find the current season from a list |

## Usage

### Kotlin

```kotlin
val api = PubgApi(apiKey = "your-api-key")

// Check API status
val isUp = api.isUp()

// Get seasons
val seasons = api.seasons(Platform.STEAM)
val current = seasons.currentOrNull()
```

### Java

```java
PubgApi api = new PubgApi("your-api-key");

Boolean isUp = BuildersKt.runBlocking(
        EmptyCoroutineContext.INSTANCE,
        (_, cont) -> StatusKt.isUp(api, cont)
);

List<Season> seasons = BuildersKt.runBlocking(
        EmptyCoroutineContext.INSTANCE,
        (_, cont) -> SeasonKt.seasons(api, Platform.STEAM, cont)
);
```

### Swift

```swift
import PubgKt

let api = PubgApi(apiKey: "your-api-key")

// Check API status
let isUp = try await api.isUp()

// Get seasons
let seasons = try await api.seasons(platform: .steam)
let current = SeasonKt.currentOrNull(seasons)
```

### TypeScript

```typescript
import { PubgApi, Platform, getSeasonsByPlatform } from "@pubgkt/common";

const api = new PubgApi("your-api-key");

// Get seasons
const seasons = await getSeasonsByPlatform(api, Platform.STEAM);
const current = seasons.asJsReadonlyArrayView().find((s) => s.isCurrentSeason);
```

## Configuration

### Rate Limiting

```kotlin
// Default — automatically delays when rate limit is about to be hit
val api = PubgApi("your-api-key")

// No rate limiting
val api = PubgApi("your-api-key", rateLimiter = RateLimiter.None)

// Concurrent rate limiter (thread-safe)
val api = PubgApi("your-api-key", rateLimiter = ConcurrentDelayRateLimiter())
```

### Retry Policy

```kotlin
// No retry (default)
val api = PubgApi("your-api-key", retry = NoRetry)

// Retry with exponential backoff
val api = PubgApi(
    "your-api-key",
    retry = Retry(
        maxRetries = 5,
        backoff = ExponentialBackoff(
            base = 2.0,
            baseDelayMs = 1_000,
            maxDelayMs = 60_000,
        ),
    ),
)
```
