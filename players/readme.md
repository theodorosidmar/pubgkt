![Coverage](https://img.shields.io/endpoint?url=https://theodorosidmar.github.io/pubgkt/coverage/players.json)

# pubgkt – players

Retrieve player information by account ID or display name.

**[API Reference](https://theodorosidmar.github.io/pubgkt/players/)** | **[PUBG Official Docs](https://documentation.pubg.com/en/players-endpoint.html)**

## Installation

<details>
<summary><strong>Gradle Kotlin DSL</strong></summary>

```kotlin
dependencies {
    implementation("dev.pubgkt:players:1.0.1")
}
```

</details>

<details>
<summary><strong>Gradle Groovy</strong></summary>

```groovy
dependencies {
    implementation 'dev.pubgkt:players:1.0.1'
}
```

</details>

<details>
<summary><strong>Maven</strong></summary>

```xml
<dependency>
    <groupId>dev.pubgkt</groupId>
    <artifactId>players-jvm</artifactId>
    <version>1.0.1</version>
</dependency>
```

</details>

<details>
<summary><strong>npm</strong></summary>

```bash
npm install @pubgkt/common @pubgkt/players
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
| `PubgApi.getPlayerByAccountId(accountId, platform)` | Get a single player by account ID |
| `PubgApi.getPlayersById(accountIds, platform)` | Get up to 10 players by account IDs |
| `PubgApi.getPlayersByNames(playerNames, platform)` | Get up to 10 players by display names |

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

### Swift

```swift
import PubgKt

let api = PubgApi(apiKey: "your-api-key")

// Multiple players by display names
let players = try await api.getPlayersByNames(playerNames: ["sparkingg", "TGLTN"], platform: .steam)
for player in players {
    print("\(player.name) — clan: \(player.clanId ?? "none")")
}

// Single player by account ID
let player = try await api.getPlayerByAccountId(accountId: players.first!.id, platform: .steam)
```

### TypeScript

```typescript
import { PubgApi, Platform, KtList } from "@pubgkt/common";
import { getPlayersByNames, getPlayerByAccountId } from "@pubgkt/players";

const api = new PubgApi("your-api-key");

// Multiple players by display names
const players = await getPlayersByNames(api, KtList.fromJsArray(["sparkingg", "TGLTN"]), Platform.STEAM);
const list = players.asJsReadonlyArrayView();
list.forEach((p) => console.log(`${p.name} — clan: ${p.clanId}`));

// Single player by account ID
const player = await getPlayerByAccountId(api, list[0].id);
```
