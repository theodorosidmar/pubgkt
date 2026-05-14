![Coverage](https://img.shields.io/endpoint?url=https://theodorosidmar.github.io/pubgkt/coverage/clans.json)

# pubgkt – clans

Retrieve clan information by clan ID.

**[API Reference](https://theodorosidmar.github.io/pubgkt/clans/)** | **[PUBG Official Docs](https://documentation.pubg.com/en/clans-endpoint.html)**

## Installation

<details>
<summary><strong>Gradle Kotlin DSL</strong></summary>

```kotlin
dependencies {
    implementation("dev.pubgkt:clans:1.0.1")
}
```

</details>

<details>
<summary><strong>Gradle Groovy</strong></summary>

```groovy
dependencies {
    implementation 'dev.pubgkt:clans:1.0.1'
}
```

</details>

<details>
<summary><strong>Maven</strong></summary>

```xml
<dependency>
    <groupId>dev.pubgkt</groupId>
    <artifactId>clans-jvm</artifactId>
    <version>1.0.1</version>
</dependency>
```

</details>

<details>
<summary><strong>npm</strong></summary>

```bash
npm install @pubgkt/common @pubgkt/clans
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
| `PubgApi.getClanById(clanId, platform)` | Get clan details by clan ID |

## Usage

### Kotlin

```kotlin
val api = PubgApi("your-api-key")

val clan = api.getClanById("clan.d52aad6adcfb4c4783a85eb250f6e822")
println("${clan.name} [${clan.tag}] — Level ${clan.level}, ${clan.memberCount} members")
```

### Java

```java
PubgApi api = new PubgApi("your-api-key");

Clan clan = BuildersKt.runBlocking(
        EmptyCoroutineContext.INSTANCE,
        (_, cont) -> GetClanByIdKt.getClanById(api, "clan.d52aad6adcfb4c4783a85eb250f6e822", Platform.STEAM, cont)
);
System.out.println(clan.getName() + " [" + clan.getTag() + "]");
```

### Swift

```swift
import PubgKt

let api = PubgApi(apiKey: "your-api-key")

let players = try await api.getPlayersByNames(playerNames: ["sparkingg"], platform: .steam)
if let clanId = players.first?.clanId {
    let clan = try await api.getClanById(clanId: clanId, platform: .steam)
    print("\(clan.name) [\(clan.tag)] — Level \(clan.level), \(clan.memberCount) members")
}
```

### TypeScript

```typescript
import { PubgApi, Platform, KtList } from "@pubgkt/common";
import { getPlayersByNames } from "@pubgkt/players";
import { getClanById } from "@pubgkt/clans";

const api = new PubgApi("your-api-key");

const players = await getPlayersByNames(api, KtList.fromJsArray(["sparkingg"]), Platform.STEAM);
const player = players.asJsReadonlyArrayView()[0];
if (player.clanId) {
    const clan = await getClanById(api, player.clanId, Platform.STEAM);
    console.log(`${clan.name} [${clan.tag}] — Level ${clan.level}, ${clan.memberCount} members`);
}
```
