package pubgkt

import kotlinx.serialization.Serializable

import kotlinx.serialization.json.Json

// Internal JSON:API response models. These are never exposed in the public API.
// Public callers only see the [Player] domain class.

// Separate from PubgApi's client configuration to keep deserialization self-contained
// within this module and avoid runtime serializer-lookup issues with internal classes.
internal val playersJson = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

@Serializable
internal data class PlayersResponse(val data: List<PlayerResource>)

@Serializable
internal data class PlayerResponse(val data: PlayerResource)

@Serializable
internal data class PlayerResource(
    val id: String,
    val type: String,
    val attributes: PlayerAttributes,
    val relationships: PlayerRelationships,
)

@Serializable
internal data class PlayerAttributes(
    val name: String,
    val patchVersion: String? = null,
    val titleId: String? = null,
    val shardId: String? = null,
)

@Serializable
internal data class PlayerRelationships(
    val matches: RelationshipData,
    val assets: RelationshipData,
)

@Serializable
internal data class RelationshipData(
    val data: List<ResourceIdentifier>,
)

@Serializable
internal data class ResourceIdentifier(
    val id: String,
    val type: String,
)

internal fun PlayerResource.toPlayer() = Player(
    accountId = id,
    name = attributes.name,
    patchVersion = attributes.patchVersion,
    titleId = attributes.titleId,
    matchIds = relationships.matches.data.map { it.id },
)
