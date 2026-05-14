![Coverage](https://img.shields.io/endpoint?url=https://theodorosidmar.github.io/pubgkt/coverage/mastery.json)

# pubgkt – mastery

Retrieve weapon mastery and survival mastery data for players.

**[API Reference](https://theodorosidmar.github.io/pubgkt/mastery/)** | **[PUBG Official Docs](https://documentation.pubg.com/en/mastery-endpoint.html)**

## Installation

<details>
<summary><strong>Gradle Kotlin DSL</strong></summary>

```kotlin
dependencies {
    implementation("dev.pubgkt:mastery:1.0.1")
}
```

</details>

<details>
<summary><strong>Gradle Groovy</strong></summary>

```groovy
dependencies {
    implementation 'dev.pubgkt:mastery:1.0.1'
}
```

</details>

<details>
<summary><strong>Maven</strong></summary>

```xml
<dependency>
    <groupId>dev.pubgkt</groupId>
    <artifactId>mastery-jvm</artifactId>
    <version>1.0.1</version>
</dependency>
```

</details>

<details>
<summary><strong>npm</strong></summary>

```bash
npm install @pubgkt/common @pubgkt/mastery
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

### Swift

```swift
import PubgKt

let api = PubgApi(apiKey: "your-api-key")

// Weapon mastery
let weaponMastery = try await api.getWeaponMasteryByAccountId(accountId: "account.abc123", platform: .steam)
print("Player: \(weaponMastery.playerId)")

// Survival mastery
let survival = try await api.getSurvivalMasteryByAccountId(accountId: "account.abc123", platform: .steam)
print("XP: \(survival.xp), Level: \(survival.level), Tier: \(survival.tier)")
print("Total matches: \(survival.totalMatchesPlayed)")
```

### TypeScript

```typescript
import { PubgApi, Platform } from "@pubgkt/common";
import { getWeaponMasteryByAccountId, getSurvivalMasteryByAccountId } from "@pubgkt/mastery";

const api = new PubgApi("your-api-key");

// Weapon mastery
const weapons = await getWeaponMasteryByAccountId(api, "account.abc123", Platform.STEAM);
const ak47 = weapons.ak47;
if (ak47) {
    console.log(`AK47: level=${ak47.currentLevel}, kills=${ak47.statsTotal.kills}`);
}

// Survival mastery
const survival = await getSurvivalMasteryByAccountId(api, "account.abc123", Platform.STEAM);
console.log(`Level: ${survival.level}, XP: ${survival.xp}, Matches: ${survival.totalMatchesPlayed}`);
```
