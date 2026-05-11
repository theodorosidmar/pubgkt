![Coverage](https://img.shields.io/endpoint?url=https://theodorosidmar.github.io/pubgkt/coverage/mastery.json)

# pubgkt – mastery

Retrieve weapon mastery and survival mastery data for players.

**[API Reference](https://theodorosidmar.github.io/pubgkt/mastery/)** | **[PUBG Official Docs](https://documentation.pubg.com/en/mastery-endpoint.html)**

## Installation

### Gradle Kotlin DSL

```kotlin
dependencies {
    implementation("dev.pubgkt:mastery:1.0.1")
}
```

### Gradle Groovy

```groovy
dependencies {
    implementation 'dev.pubgkt:mastery:1.0.1'
}
```

### Maven

```xml
<dependency>
    <groupId>dev.pubgkt</groupId>
    <artifactId>mastery-jvm</artifactId>
    <version>1.0.1</version>
</dependency>
```

## API Reference

| Function | Description |
|----------|-------------|
| `PubgApi.getWeaponMasteryByAccountId(accountId, platform)` | Get weapon mastery stats for a player |
| `PubgApi.getSurvivalMasteryByAccountId(accountId, platform)` | Get survival mastery stats for a player |

## Usage

### Kotlin

```kotlin
val api = PubgApi("your-api-key")

// Weapon mastery
val weapons = api.getWeaponMasteryByAccountId("account.abc123")
weapons.ak47?.let { ak ->
    println("AK47 — Level ${ak.currentLevel}, ${ak.statsTotal.kills} kills")
}

// Survival mastery
val survival = api.getSurvivalMasteryByAccountId("account.abc123")
println("Level: ${survival.level}, XP: ${survival.xp}")
println("Total matches: ${survival.totalMatchesPlayed}, Top 10s: ${survival.top10}")
```

### Java

```java
PubgApi api = new PubgApi("your-api-key");

WeaponMastery weapons = BuildersKt.runBlocking(
        EmptyCoroutineContext.INSTANCE,
        (_, cont) -> GetWeaponMasteryByAccountIdKt.getWeaponMasteryByAccountId(api, "account.abc123", Platform.STEAM, cont)
);

SurvivalMastery survival = BuildersKt.runBlocking(
        EmptyCoroutineContext.INSTANCE,
        (_, cont) -> GetSurvivalMasteryByAccountIdKt.getSurvivalMasteryByAccountId(api, "account.abc123", Platform.STEAM, cont)
);
System.out.println("Level: " + survival.getLevel() + ", XP: " + survival.getXp());
```
