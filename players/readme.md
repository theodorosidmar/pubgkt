![Coverage](https://img.shields.io/endpoint?url=https://theodorosidmar.github.io/pubgkt/coverage/players.json)

# pubgkt – players

Retrieve player information by account ID or display name.

**[API Reference](https://theodorosidmar.github.io/pubgkt/players/)** | **[PUBG Official Docs](https://documentation.pubg.com/en/players-endpoint.html)**

## Installation

### Gradle Kotlin DSL

```kotlin
dependencies {
    implementation("dev.pubgkt:players:1.0.1")
}
```

### Gradle Groovy

```groovy
dependencies {
    implementation 'dev.pubgkt:players:1.0.1'
}
```

### Maven

```xml
<dependency>
    <groupId>dev.pubgkt</groupId>
    <artifactId>players-jvm</artifactId>
    <version>1.0.1</version>
</dependency>
```

## API Reference

| Function | Description |
|----------|-------------|
| `PubgApi.getPlayerByAccountId(accountId, platform)` | Get a single player by account ID |
| `PubgApi.getPlayersById(accountIds, platform)` | Get up to 10 players by account IDs |
| `PubgApi.getPlayersById(vararg accountIds, platform)` | Get up to 10 players by account IDs (vararg) |
| `PubgApi.getPlayersByNames(playerNames, platform)` | Get up to 10 players by display names |
| `PubgApi.getPlayersByNames(vararg playerNames, platform)` | Get up to 10 players by display names (vararg) |

## Usage

### Kotlin

```kotlin
val api = PubgApi("your-api-key")

// Single player by account ID
val player = api.getPlayerByAccountId("account.abc123")

// Multiple players by IDs
val players = api.getPlayersById("account.abc123", "account.def456")

// Multiple players by display names
val byNames = api.getPlayersByNames("sparkingg", "TGLTN")
byNames.forEach { println("${it.name} — clan: ${it.clanId}") }
```

### Java

```java
PubgApi api = new PubgApi("your-api-key");

// Single player by account ID
Player player = BuildersKt.runBlocking(
        EmptyCoroutineContext.INSTANCE,
        (_, cont) -> GetPlayerByAccountIdKt.getPlayerByAccountId(api, "account.abc123", Platform.STEAM, cont)
);

// Multiple players by names
List<Player> players = BuildersKt.runBlocking(
        EmptyCoroutineContext.INSTANCE,
        (_, cont) -> GetPlayersByNameKt.getPlayersByNames(api, List.of("sparkingg", "TGLTN"), Platform.STEAM, cont)
);
```
