![Coverage](https://img.shields.io/endpoint?url=https://theodorosidmar.github.io/pubgkt/coverage/stats.json)

# pubgkt – stats

Retrieve lifetime, season, and ranked player statistics.

**[API Reference](https://theodorosidmar.github.io/pubgkt/stats/)** | **[PUBG Official Docs](https://documentation.pubg.com/en/lifetime-stats.html)**

## Installation

<details>
<summary><strong>Gradle Kotlin DSL</strong></summary>

```kotlin
dependencies {
    implementation("dev.pubgkt:stats:1.0.1")
}
```

</details>

<details>
<summary><strong>Gradle Groovy</strong></summary>

```groovy
dependencies {
    implementation 'dev.pubgkt:stats:1.0.1'
}
```

</details>

<details>
<summary><strong>Maven</strong></summary>

```xml
<dependency>
    <groupId>dev.pubgkt</groupId>
    <artifactId>stats-jvm</artifactId>
    <version>1.0.1</version>
</dependency>
```

</details>

<details>
<summary><strong>npm</strong></summary>

```bash
npm install @pubgkt/common @pubgkt/stats
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
| `PubgApi.getLifetimeStatsByAccountId(accountId, platform)` | Get lifetime stats for a player |
| `PubgApi.getLifetimeStatsByGameModeAndPlayers(gameMode, accountIds, platform)` | Get lifetime stats for up to 10 players in a game mode |
| `PubgApi.getSeasonStatsByAccountId(accountId, seasonId, platform)` | Get season stats for a player |
| `PubgApi.getSeasonStatsByGameModeAndPlayers(seasonId, gameMode, accountIds, platform)` | Get season stats for up to 10 players in a game mode |
| `PubgApi.getRankedStatsByAccountIdAndSeasonId(accountId, seasonId, platform)` | Get ranked stats for a player in a season |

## Usage

### Kotlin

```kotlin
val api = PubgApi("your-api-key")

// Lifetime stats
val lifetime = api.getLifetimeStatsByAccountId("account.abc123")
println("Squad FPP — ${lifetime.squadFpp.wins} wins, ${lifetime.squadFpp.kills} kills")

// Lifetime stats for multiple players
val stats = api.getLifetimeStatsByGameModeAndPlayers(
    gameMode = GameMode.SQUAD_FPP,
    accountIds = listOf("account.abc123", "account.def456"),
)

// Season stats
val season = api.getSeasonStatsByAccountId("account.abc123", seasonId = "division.bro.official.pc-2018-41")
println("Season ${season.seasonId} — ${season.squadFpp.wins} wins")

// Ranked stats
val ranked = api.getRankedStatsByAccountIdAndSeasonId("account.abc123", seasonId = "division.bro.official.pc-2018-41")
println("Tier: ${ranked.currentTier}, RP: ${ranked.currentRankPoint}, KDA: ${ranked.kda}")
```

### Java

```java
PubgApi api = new PubgApi("your-api-key");

// Lifetime stats
LifetimePlayerStats lifetime = BuildersKt.runBlocking(
        EmptyCoroutineContext.INSTANCE,
        (_, cont) -> GetLifetimeStatsKt.getLifetimeStatsByAccountId(api, "account.abc123", Platform.STEAM, cont)
);
System.out.println("Wins: " + lifetime.getSquadFpp().getWins());

// Ranked stats
RankedStats ranked = BuildersKt.runBlocking(
        EmptyCoroutineContext.INSTANCE,
        (_, cont) -> GetRankedStatsKt.getRankedStatsByAccountIdAndSeasonId(api, "account.abc123", "season-id", Platform.STEAM, cont)
);
System.out.println("Tier: " + ranked.getCurrentTier() + ", KDA: " + ranked.getKda());
```

### Swift

```swift
import PubgKt

let api = PubgApi(apiKey: "your-api-key")

let seasons = try await api.seasons(platform: .steam)
guard let currentSeason = SeasonKt.currentOrNull(seasons) else { return }

// Lifetime stats
let lifetime = try await api.getLifetimeStatsByAccountId(accountId: "account.abc123", platform: .steam)
print("Squad FPP — \(lifetime.squadFpp.wins) wins, \(lifetime.squadFpp.kills) kills")

// Season stats
let seasonStats = try await api.getSeasonStatsByAccountId(
    accountId: "account.abc123",
    seasonId: currentSeason.id,
    platform: .steam
)
print("Season: \(seasonStats.seasonId) — \(seasonStats.squadFpp.wins) wins")

// Ranked stats
let ranked = try await api.getRankedStatsByAccountIdAndSeasonId(
    accountId: "account.abc123",
    seasonId: currentSeason.id,
    platform: .steam
)
print("Tier: \(ranked.currentTier), RP: \(ranked.currentRankPoint), KDA: \(ranked.kda)")
```

### TypeScript

```typescript
import { PubgApi, Platform, getSeasonsByPlatform } from "@pubgkt/common";
import { getLifetimeStatsByAccountId, getSeasonStatsByAccountId, getRankedStatsByAccountIdAndSeasonId } from "@pubgkt/stats";

const api = new PubgApi("your-api-key");

// Lifetime stats
const lifetime = await getLifetimeStatsByAccountId(api, "account.abc123");
console.log(`Squad FPP — ${lifetime.squadFpp.wins} wins, ${lifetime.squadFpp.kills} kills`);

// Season stats
const seasons = await getSeasonsByPlatform(api, Platform.STEAM);
const current = seasons.asJsReadonlyArrayView().find((s) => s.isCurrentSeason);
if (current) {
    const seasonStats = await getSeasonStatsByAccountId(api, "account.abc123", current.id, Platform.STEAM);
    console.log(`Season — ${seasonStats.squadFpp.wins} wins`);

    // Ranked stats
    const ranked = await getRankedStatsByAccountIdAndSeasonId(api, "account.abc123", current.id, Platform.STEAM);
    console.log(`Tier: ${ranked.currentTier.tier}, KDA: ${ranked.kda}`);
}
```
