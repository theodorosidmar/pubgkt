![Coverage](https://img.shields.io/endpoint?url=https://theodorosidmar.github.io/pubgkt/coverage/matches.json)

# pubgkt – matches

Retrieve detailed match data including participants, stats, and telemetry metadata.

**[API Reference](https://theodorosidmar.github.io/pubgkt/matches/)** | **[PUBG Official Docs](https://documentation.pubg.com/en/matches-endpoint.html)**

## Installation

### Gradle Kotlin DSL

```kotlin
dependencies {
    implementation("dev.pubgkt:matches:1.0.1")
}
```

### Gradle Groovy

```groovy
dependencies {
    implementation 'dev.pubgkt:matches:1.0.1'
}
```

### Maven

```xml
<dependency>
    <groupId>dev.pubgkt</groupId>
    <artifactId>matches-jvm</artifactId>
    <version>1.0.1</version>
</dependency>
```

## API Reference

| Function | Description |
|----------|-------------|
| `PubgApi.getMatchById(matchId, platform)` | Get full match details by match ID |

## Usage

### Kotlin

```kotlin
val api = PubgApi("your-api-key")

val match = api.getMatchById("match-id-here")
println("Map: ${match.mapName}, Mode: ${match.gameMode}")
println("Duration: ${match.duration}s, Teams: ${match.participants.size}")

match.participants.first().players.forEach { player ->
    println("${player.name} — ${player.kills} kills, ${player.damageDealt} dmg")
}
```

### Java

```java
PubgApi api = new PubgApi("your-api-key");

Match match = BuildersKt.runBlocking(
        EmptyCoroutineContext.INSTANCE,
        (_, cont) -> GetMatchByIdKt.getMatchById(api, "match-id-here", Platform.STEAM, cont)
);
System.out.println("Map: " + match.getMapName() + ", Mode: " + match.getGameMode());
System.out.println("Duration: " + match.getDuration() + "s");
```
