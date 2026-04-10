package pubgkt.fixtures

internal const val PLAYER_RESPONSE_JSON = """
{
  "data": {
    "type": "player",
    "id": "account.abc123",
    "attributes": {
      "name": "PlayerOne",
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
