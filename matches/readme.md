![Coverage](https://img.shields.io/endpoint?url=https://theodorosidmar.github.io/pubgkt/coverage/matches.json)

# pubgkt – matches

Retrieve detailed match data including participants, stats, and telemetry metadata.

**[API Reference](https://theodorosidmar.github.io/pubgkt/matches/)** | **[PUBG Official Docs](https://documentation.pubg.com/en/matches-endpoint.html)**

## Installation

<details>
<summary><strong>Gradle Kotlin DSL</strong></summary>

```kotlin
dependencies {
    implementation("dev.pubgkt:matches:1.0.1")
}
```

</details>

<details>
<summary><strong>Gradle Groovy</strong></summary>

```groovy
dependencies {
    implementation 'dev.pubgkt:matches:1.0.1'
}
```

</details>

<details>
<summary><strong>Maven</strong></summary>

```xml
<dependency>
    <groupId>dev.pubgkt</groupId>
    <artifactId>matches-jvm</artifactId>
    <version>1.0.1</version>
</dependency>
```

</details>

<details>
<summary><strong>npm</strong></summary>

```bash
npm install @pubgkt/common @pubgkt/matches
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

### Swift

```swift
import PubgKt

let api = PubgApi(apiKey: "your-api-key")

let players = try await api.getPlayersByNames(playerNames: ["sparkingg"], platform: .steam)
if let firstMatch = players.first?.matches.first {
    let match = try await api.getMatchById(matchId: firstMatch.id, platform: .steam)
    print("Map: \(match.mapName), Mode: \(match.gameMode), Duration: \(match.duration)s")
    for team in match.participants.prefix(3) {
        for mp in team.players {
            print("  \(mp.name) — \(mp.kills) kills, \(mp.damageDealt) dmg")
        }
    }
}
```

### TypeScript

```typescript
import { PubgApi, Platform, KtList } from "@pubgkt/common";
import { getPlayersByNames } from "@pubgkt/players";
import { getMatchById } from "@pubgkt/matches";

const api = new PubgApi("your-api-key");

const players = await getPlayersByNames(api, KtList.fromJsArray(["sparkingg"]), Platform.STEAM);
const matchIds = players.asJsReadonlyArrayView()[0].matches.asJsReadonlyArrayView();
if (matchIds.length > 0) {
    const match = await getMatchById(api, matchIds[0].id, Platform.STEAM);
    console.log(`Map: ${match.mapName.name}, Mode: ${match.gameMode.name}, Duration: ${match.duration}s`);
}
```
