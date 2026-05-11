![Coverage](https://img.shields.io/endpoint?url=https://theodorosidmar.github.io/pubgkt/coverage/clans.json)

# pubgkt – clans

Retrieve clan information by clan ID.

**[API Reference](https://theodorosidmar.github.io/pubgkt/clans/)** | **[PUBG Official Docs](https://documentation.pubg.com/en/clans-endpoint.html)**

## Installation

### Gradle Kotlin DSL

```kotlin
dependencies {
    implementation("dev.pubgkt:clans:1.0.1")
}
```

### Gradle Groovy

```groovy
dependencies {
    implementation 'dev.pubgkt:clans:1.0.1'
}
```

### Maven

```xml
<dependency>
    <groupId>dev.pubgkt</groupId>
    <artifactId>clans-jvm</artifactId>
    <version>1.0.1</version>
</dependency>
```

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
