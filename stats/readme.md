![Coverage](https://img.shields.io/endpoint?url=https://theodorosidmar.github.io/pubgkt/coverage/stats.json)

# pubgkt – stats

Retrieve lifetime, season, and ranked player statistics.

**[API Reference](https://theodorosidmar.github.io/pubgkt/stats/)** | **[PUBG Official Docs](https://documentation.pubg.com/en/lifetime-stats.html)**

## Installation

### Gradle Kotlin DSL

```kotlin
dependencies {
    implementation("dev.pubgkt:stats:1.0.0")
}
```

### Gradle Groovy

```groovy
dependencies {
    implementation 'dev.pubgkt:stats:1.0.0'
}
```

### Maven

```xml
<dependency>
    <groupId>dev.pubgkt</groupId>
    <artifactId>stats-jvm</artifactId>
    <version>1.0.0</version>
</dependency>
```

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
