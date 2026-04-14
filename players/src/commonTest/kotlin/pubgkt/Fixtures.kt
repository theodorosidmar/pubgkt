package pubgkt

internal const val PLAYER_RESPONSE_JSON = """
{
  "data": {
    "type": "player",
    "id": "account.abc123",
    "attributes": {
      "name": "PlayerOne",
      "banType": "Innocent",
      "patchVersion": "14.1",
      "titleId": "bluehole-pubg",
      "shardId": "steam"
    },
    "relationships": {
      "matches": {
        "data": [
          { "type": "match", "id": "match-id-1" },
          { "type": "match", "id": "match-id-2" }
        ]
      },
      "assets": { "data": [] }
    }
  }
}
"""

internal const val PLAYERS_RESPONSE_JSON = """
{
  "data": [
    {
      "type": "player",
      "id": "account.abc123",
      "attributes": {
        "name": "PlayerOne",
        "banType": "Innocent",
        "patchVersion": "14.1",
        "titleId": "bluehole-pubg",
        "shardId": "steam"
      },
      "relationships": {
        "matches": {
          "data": [ { "type": "match", "id": "match-id-1" } ]
        },
        "assets": { "data": [] }
      }
    },
    {
      "type": "player",
      "id": "account.def456",
      "attributes": {
        "name": "PlayerTwo",
        "banType": "Innocent",
        "patchVersion": "14.1",
        "titleId": "bluehole-pubg",
        "shardId": "steam"
      },
      "relationships": {
        "matches": {
          "data": [ { "type": "match", "id": "match-id-2" } ]
        },
        "assets": { "data": [] }
      }
    }
  ]
}
"""

/** Resource object only (no {"data": …} envelope), for direct deserializer testing. */
internal const val PLAYER_RESOURCE_JSON = """
{
  "type": "player",
  "id": "account.abc123",
  "attributes": {
    "name": "PlayerOne",
    "banType": "Innocent",
    "patchVersion": "14.1",
    "titleId": "bluehole-pubg",
    "shardId": "steam",
    "clanId": "clan-42"
  },
  "relationships": {
    "matches": {
      "data": [
        { "type": "match", "id": "match-id-1" },
        { "type": "match", "id": "match-id-2" }
      ]
    },
    "assets": { "data": [] }
  }
}
"""

/** Resource with optional fields absent (clanId, patchVersion missing from JSON). */
internal const val PLAYER_RESOURCE_MISSING_OPTIONALS_JSON = """
{
  "type": "player",
  "id": "account.abc123",
  "attributes": {
    "name": "PlayerOne",
    "banType": "Innocent",
    "titleId": "bluehole-pubg",
    "shardId": "steam"
  },
  "relationships": {
    "matches": {
      "data": []
    }
  }
}
"""

/** Resource with no relationships section at all. */
internal const val PLAYER_RESOURCE_NO_RELATIONSHIPS_JSON = """
{
  "type": "player",
  "id": "account.abc123",
  "attributes": {
    "name": "PlayerOne",
    "banType": "Innocent",
    "titleId": "bluehole-pubg",
    "shardId": "steam"
  }
}
"""

/** Resource with empty matches array. */
internal const val PLAYER_RESOURCE_EMPTY_MATCHES_JSON = """
{
  "type": "player",
  "id": "account.abc123",
  "attributes": {
    "name": "PlayerOne",
    "banType": "Innocent",
    "titleId": "bluehole-pubg",
    "shardId": "steam"
  },
  "relationships": {
    "matches": {
      "data": []
    }
  }
}
"""

/** Resource with an unknown banType value. */
internal const val PLAYER_RESOURCE_UNKNOWN_BAN_TYPE_JSON = """
{
  "type": "player",
  "id": "account.abc123",
  "attributes": {
    "name": "PlayerOne",
    "banType": "SuperBanned",
    "titleId": "bluehole-pubg",
    "shardId": "steam"
  }
}
"""

/** Resource with lowercase banType to test case-insensitivity. */
internal const val PLAYER_RESOURCE_LOWERCASE_BAN_TYPE_JSON = """
{
  "type": "player",
  "id": "account.abc123",
  "attributes": {
    "name": "PlayerOne",
    "banType": "permanentban",
    "titleId": "bluehole-pubg",
    "shardId": "steam"
  }
}
"""
