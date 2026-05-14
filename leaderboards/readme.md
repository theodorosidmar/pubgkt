![Coverage](https://img.shields.io/endpoint?url=https://theodorosidmar.github.io/pubgkt/coverage/leaderboards.json)

# pubgkt – leaderboards

Retrieve leaderboard rankings by season, game mode, and platform region.

**[API Reference](https://theodorosidmar.github.io/pubgkt/leaderboards/)** | **[PUBG Official Docs](https://documentation.pubg.com/en/leaderboards-endpoint.html)**

## Installation

<details>
<summary><strong>Gradle Kotlin DSL</strong></summary>

```kotlin
dependencies {
    implementation("dev.pubgkt:leaderboards:1.0.1")
}
```

</details>

<details>
<summary><strong>Gradle Groovy</strong></summary>

```groovy
dependencies {
    implementation 'dev.pubgkt:leaderboards:1.0.1'
}
```

</details>

<details>
<summary><strong>Maven</strong></summary>

```xml
<dependency>
    <groupId>dev.pubgkt</groupId>
    <artifactId>leaderboards-jvm</artifactId>
    <version>1.0.1</version>
</dependency>
```

</details>

<details>
<summary><strong>npm</strong></summary>

```bash
npm install @pubgkt/common @pubgkt/leaderboards
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

### Swift

```swift
import PubgKt

let api = PubgApi(apiKey: "your-api-key")

let seasons = try await api.seasons(platform: .steam)
guard let currentSeason = SeasonKt.currentOrNull(seasons) else { return }

let leaderboard = try await api.getLeaderboard(
    seasonId: currentSeason.id,
    gameMode: .squadFpp,
    platformRegion: .pcNa
)
for p in leaderboard.placements.prefix(10) {
    print("#\(p.rank) \(p.playerName) — \(p.rankPoints) RP, \(p.wins) wins")
}
```

### TypeScript

```typescript
import { PubgApi, Platform, GameMode, getSeasonsByPlatform } from "@pubgkt/common";
import { getLeaderboard } from "@pubgkt/leaderboards";

const api = new PubgApi("your-api-key");

const seasons = await getSeasonsByPlatform(api, Platform.STEAM);
const current = seasons.asJsReadonlyArrayView().find((s) => s.isCurrentSeason);
if (current) {
    const leaderboard = await getLeaderboard(api, current.id, GameMode.SQUAD_FPP);
    leaderboard.placements.asJsReadonlyArrayView().slice(0, 10).forEach((p) =>
        console.log(`#${p.rank} ${p.playerName} — ${p.rankPoints.toFixed(0)} RP, KDA=${p.kda.toFixed(2)}`)
    );
}
```
