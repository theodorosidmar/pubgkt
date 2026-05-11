![Coverage](https://img.shields.io/endpoint?url=https://theodorosidmar.github.io/pubgkt/coverage/leaderboards.json)

# pubgkt – leaderboards

Retrieve leaderboard rankings by season, game mode, and platform region.

**[API Reference](https://theodorosidmar.github.io/pubgkt/leaderboards/)** | **[PUBG Official Docs](https://documentation.pubg.com/en/leaderboards-endpoint.html)**

## Installation

### Gradle Kotlin DSL

```kotlin
dependencies {
    implementation("dev.pubgkt:leaderboards:1.0.1")
}
```

### Gradle Groovy

```groovy
dependencies {
    implementation 'dev.pubgkt:leaderboards:1.0.1'
}
```

### Maven

```xml
<dependency>
    <groupId>dev.pubgkt</groupId>
    <artifactId>leaderboards-jvm</artifactId>
    <version>1.0.1</version>
</dependency>
```

## API Reference

| Function | Description |
|----------|-------------|
| `PubgApi.getLeaderboard(seasonId, gameMode, platformRegion)` | Get leaderboard for a season and game mode |

## Usage

### Kotlin

```kotlin
val api = PubgApi("your-api-key")

val seasons = api.seasons(Platform.STEAM)
val currentSeason = seasons.currentOrNull()!!

val leaderboard = api.getLeaderboard(
    seasonId = currentSeason.id,
    gameMode = GameMode.SQUAD_FPP,
    platformRegion = PlatformRegion.PC_NA,
)

leaderboard.placements.take(10).forEach { p ->
    println("#${p.rank} ${p.playerName} — ${p.rankPoints} RP, ${p.wins} wins")
}
```

### Java

```java
PubgApi api = new PubgApi("your-api-key");

Leaderboard leaderboard = BuildersKt.runBlocking(
        EmptyCoroutineContext.INSTANCE,
        (_, cont) -> GetLeaderboardKt.getLeaderboard(api, "season-id", GameMode.SQUAD_FPP, PlatformRegion.PC_NA, cont)
);
leaderboard.getPlacements().stream().limit(10).forEach(p ->
        System.out.println("#" + p.getRank() + " " + p.getPlayerName() + " — " + p.getRankPoints() + " RP")
);
```
