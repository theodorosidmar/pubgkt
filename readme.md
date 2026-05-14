[![Maven Central](https://img.shields.io/maven-central/v/dev.pubgkt/common.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/dev.pubgkt/common)
[![npm](https://img.shields.io/npm/v/@pubgkt/common?label=npm&color=red)](https://www.npmjs.com/org/pubgkt)
![CI](https://github.com/theodorosidmar/pubgkt/actions/workflows/ci.yml/badge.svg)
![Detekt](https://github.com/theodorosidmar/pubgkt/actions/workflows/detekt.yml/badge.svg)
![Coverage](https://img.shields.io/endpoint?url=https://theodorosidmar.github.io/pubgkt/coverage/core.json)
![License](https://img.shields.io/badge/license-MIT-blue)

# pubgkt

Kotlin Multiplatform client for the official [PUBG API](https://documentation.pubg.com/en/introduction.html).

## Installation

### Obtain an API key

Register at the [PUBG Developer Portal](https://developer.pubg.com) to get your API key.

## Getting Started By Platform

<details>
<summary><strong>JVM</strong> (Kotlin and Java)</summary>

### Gradle Kotlin DSL

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    // Using the BOM (recommended)
    implementation(platform("dev.pubgkt:bom:1.0.1"))
    implementation("dev.pubgkt:core") // all modules
    // or pick individual modules
    implementation("dev.pubgkt:players")
    implementation("dev.pubgkt:stats")
}
```

### Gradle Groovy

```groovy
repositories {
    mavenCentral()
}

dependencies {
    // Using the BOM (recommended)
    implementation platform('dev.pubgkt:bom:1.0.1')
    implementation 'dev.pubgkt:core' // all modules
    // or pick individual modules
    implementation 'dev.pubgkt:players'
    implementation 'dev.pubgkt:stats'
}
```

### Maven

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>dev.pubgkt</groupId>
            <artifactId>bom</artifactId>
            <version>1.0.1</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<dependencies>
    <!-- All modules -->
    <dependency>
        <groupId>dev.pubgkt</groupId>
        <artifactId>core-jvm</artifactId>
    </dependency>
    <!-- Or pick individual modules -->
    <dependency>
        <groupId>dev.pubgkt</groupId>
        <artifactId>players-jvm</artifactId>
    </dependency>
</dependencies>
```

> **Note:** Maven artifacts use the `-jvm` suffix (e.g. `players-jvm`). Gradle Kotlin DSL resolves the correct variant automatically.

</details>

<details>
<summary><strong>iOS/watchOS</strong> (Swift)</summary>

### Swift Package Manager

Add the dependency in your `Package.swift`:

```swift
.package(url: "https://github.com/theodorosidmar/pubgkt.git", from: "1.0.1")
```

Then add the product to your target:

```swift
.product(name: "pubgkt", package: "pubgkt")
```

Import in your Swift code:

```swift
import PubgKt
```

> **Note:** The Apple distribution is a single umbrella XCFramework exported from `core` that includes all modules.

</details>

<details>
<summary><strong>Android</strong></summary>

### Gradle Kotlin DSL

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    // Using the BOM (recommended)
    implementation(platform("dev.pubgkt:bom:1.0.1"))
    implementation("dev.pubgkt:core") // all modules
    // or pick individual modules
    implementation("dev.pubgkt:players")
    implementation("dev.pubgkt:stats")
}
```

### Gradle Groovy

```groovy
repositories {
    mavenCentral()
}

dependencies {
    // Using the BOM (recommended)
    implementation platform('dev.pubgkt:bom:1.0.1')
    implementation 'dev.pubgkt:core' // all modules
    // or pick individual modules
    implementation 'dev.pubgkt:players'
    implementation 'dev.pubgkt:stats'
}
```

> **Note:** Gradle resolves the correct `-android` variant automatically via Kotlin Multiplatform metadata. No `-android` suffix needed in the dependency declaration.

</details>

<details>
<summary><strong>Node.js</strong> (JavaScript/TypeScript)</summary>

```bash
npm install @pubgkt/common @pubgkt/players @pubgkt/stats
```

Available packages:

| npm package | Contents |
|-------------|----------|
| `@pubgkt/common` | `PubgApi`, `Platform`, `PlatformRegion`, `GameMode`, seasons |
| `@pubgkt/players` | Player lookup by name or ID |
| `@pubgkt/clans` | Clan information |
| `@pubgkt/matches` | Match details with participants and stats |
| `@pubgkt/leaderboards` | Leaderboard rankings |
| `@pubgkt/mastery` | Weapon and survival mastery |
| `@pubgkt/stats` | Lifetime, season, and ranked stats |

> **Note:** Kotlin `List<T>` is exposed as `KtList` — call `.asJsReadonlyArrayView()` to get a standard `ReadonlyArray<T>`, or use `KtList.fromJsArray()` to convert a JS array into a `KtList`.

</details>

## Quick Start

### Kotlin

```kotlin
import dev.pubgkt.PubgApi
import dev.pubgkt.Platform
import dev.pubgkt.players.getPlayersByNames
import dev.pubgkt.stats.lifetime.getLifetimeStatsByAccountId

val api = PubgApi(apiKey = "your-api-key")

// Find players by display name
val players = api.getPlayersByNames("sparkingg", "TGLTN")

// Get lifetime stats
val stats = api.getLifetimeStatsByAccountId(players.first().id)
println("Squad FPP — ${stats.squadFpp.wins} wins, ${stats.squadFpp.kills} kills")
```

### Java

```java
import dev.pubgkt.PubgApi;
import dev.pubgkt.Platform;
import dev.pubgkt.players.GetPlayersByNameKt;
import dev.pubgkt.players.Player;
import kotlinx.coroutines.BuildersKt;
import kotlin.coroutines.EmptyCoroutineContext;

PubgApi api = new PubgApi("your-api-key");

List<Player> players = BuildersKt.runBlocking(
        EmptyCoroutineContext.INSTANCE,
        (_, cont) -> GetPlayersByNameKt.getPlayersByNames(api, List.of("sparkingg"), Platform.STEAM, cont)
);
```

### Swift

```swift
import PubgKt

let api = PubgApi(apiKey: "your-api-key")

// Find players by display name
let players = try await api.getPlayersByNames(playerNames: ["sparkingg", "TGLTN"], platform: .steam)

// Get lifetime stats
let stats = try await api.getLifetimeStatsByAccountId(accountId: players.first!.id, platform: .steam)
print("Squad FPP — \(stats.squadFpp.wins) wins, \(stats.squadFpp.kills) kills")
```

### Node.js / TypeScript

```typescript
import { PubgApi, Platform, KtList } from "@pubgkt/common";
import { getPlayersByNames } from "@pubgkt/players";
import { getLifetimeStatsByAccountId } from "@pubgkt/stats";

const api = new PubgApi("your-api-key");

// Find players by display name
const players = await getPlayersByNames(api, KtList.fromJsArray(["sparkingg", "TGLTN"]), Platform.STEAM);
const list = players.asJsReadonlyArrayView();

// Get lifetime stats
const stats = await getLifetimeStatsByAccountId(api, list[0].id);
console.log(`Squad FPP — ${stats.squadFpp.wins} wins, ${stats.squadFpp.kills} kills`);
```

> **Note:** All async API functions return `Promise`. Extension functions take the `PubgApi` instance as the first argument.

## Modules

| Module | Description | Docs |
|--------|-------------|------|
| [`common`](common/readme.md) | Core client, platform enums, seasons, rate limiting, retry | [API](https://theodorosidmar.github.io/pubgkt/common/) |
| [`players`](players/readme.md) | Player lookup by ID or name | [API](https://theodorosidmar.github.io/pubgkt/players/) |
| [`clans`](clans/readme.md) | Clan information | [API](https://theodorosidmar.github.io/pubgkt/clans/) |
| [`matches`](matches/readme.md) | Match details with participants and stats | [API](https://theodorosidmar.github.io/pubgkt/matches/) |
| [`leaderboards`](leaderboards/readme.md) | Leaderboard rankings | [API](https://theodorosidmar.github.io/pubgkt/leaderboards/) |
| [`mastery`](mastery/readme.md) | Weapon and survival mastery | [API](https://theodorosidmar.github.io/pubgkt/mastery/) |
| [`stats`](stats/readme.md) | Lifetime, season, and ranked stats | [API](https://theodorosidmar.github.io/pubgkt/stats/) |
| `core` | All modules bundled together | [API](https://theodorosidmar.github.io/pubgkt/) |
| `bom` | Bill of Materials for version alignment | — |

## Rate Limiting

The PUBG API enforces a default limit of **10 requests per minute** per API key. pubgkt handles this automatically via the `RateLimiter` interface.

The default implementation (`DelayRateLimiter`) reads the `X-RateLimit-Remaining` and `X-RateLimit-Reset` response headers and delays subsequent requests when the limit is exhausted.

```kotlin
// Default — proactive delay when the limit is about to be hit
val api = PubgApi("your-api-key")

// No rate limiting — manage it yourself
val api = PubgApi("your-api-key", rateLimiter = RateLimiter.None)

// Thread-safe rate limiter for concurrent usage
val api = PubgApi("your-api-key", rateLimiter = ConcurrentDelayRateLimiter())
```

If the API returns HTTP 429 regardless (e.g. another instance shares your key), `RateLimitExceededException` is thrown with the reset timestamp.

## Retry & Backoff

```kotlin
import dev.pubgkt.Retry
import dev.pubgkt.ExponentialBackoff

val api = PubgApi(
    apiKey = "your-api-key",
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

## API Documentation

Full API reference is available at [theodorosidmar.github.io/pubgkt](https://theodorosidmar.github.io/pubgkt/).

## Samples

### Kotlin / Java

```bash
./gradlew :samples:jvm:runPlayersKotlin -Pargs="<api-key>"
./gradlew :samples:jvm:runStatsKotlin -Pargs="<api-key>"
./gradlew :samples:jvm:runMatchesKotlin -Pargs="<api-key>"

./gradlew :samples:jvm:runPlayersJava -Pargs="<api-key>"
./gradlew :samples:jvm:runStatsJava -Pargs="<api-key>"
./gradlew :samples:jvm:runMatchesJava -Pargs="<api-key>"
```

### Swift

```bash
./gradlew :samples:swift:runPlayersSwift -Pargs="<api-key>"
./gradlew :samples:swift:runStatsSwift -Pargs="<api-key>"
./gradlew :samples:swift:runMatchesSwift -Pargs="<api-key>"
./gradlew :samples:swift:runClansSwift -Pargs="<api-key>"
./gradlew :samples:swift:runLeaderboardsSwift -Pargs="<api-key>"
./gradlew :samples:swift:runMasterySwift -Pargs="<api-key>"
```

### Node.js

```bash
# Build the npm packages first
./gradlew :core:assembleNpmPackages

# Install dependencies
cd samples/nodejs && npm install

# Run any sample
PUBG_API_KEY=your-key npm run players
PUBG_API_KEY=your-key npm run stats
PUBG_API_KEY=your-key npm run matches
```

Available modules: `common`, `players`, `clans`, `matches`, `leaderboards`, `mastery`, `stats`

## Supported Platforms

| Platform | Status |
|----------|--------|
| JVM      | ✅     |
| iOS      | ✅     |
| watchOS  | ✅     |
| Android  | ✅     |
| JS/Node  | ✅     |

## License

[MIT](license)
