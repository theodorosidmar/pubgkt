package pubgkt.stats.ranked

internal const val RANKED_RESPONSE_JSON = """
{
  "data": {
    "type": "rankedplayerstats",
    "attributes": {
      "rankedGameModeStats": {
        "squad": {
          "currentTier": {
            "tier": "Crystal",
            "subTier": "4"
          },
          "currentRankPoint": 2647,
          "bestTier": {
            "tier": "Crystal",
            "subTier": "3"
          },
          "bestRankPoint": 2647,
          "roundsPlayed": 17,
          "avgRank": 5.9411764,
          "avgSurvivalTime": 0,
          "top10Ratio": 0.7647059,
          "winRatio": 0.47058824,
          "assists": 66,
          "wins": 8,
          "kda": 0,
          "kdr": 0,
          "avgKill": 4.8235292,
          "kills": 82,
          "deaths": 12,
          "roundMostKills": 0,
          "longestKill": 0,
          "headshotKills": 0,
          "headshotKillRatio": 0,
          "damageDealt": 12376.169,
          "dBNOs": 73,
          "reviveRatio": 0,
          "revives": 0,
          "heals": 0,
          "boosts": 0,
          "weaponsAcquired": 0,
          "teamKills": 0,
          "playTime": 0,
          "killStreak": 0
        }
      }
    },
    "relationships": {
      "player": {
        "data": {
          "type": "player",
          "id": "account.82bad0072f31455d8d9f8d834da2f2f3"
        }
      },
      "season": {
        "data": {
          "type": "season",
          "id": "division.bro.official.pc-2018-41"
        }
      }
    }
  },
  "links": {
    "self": "https://api.pubg.com/shards/steam/players/account.82bad0072f31455d8d9f8d834da2f2f3/seasons/division.bro.official.pc-2018-41/ranked"
  },
  "meta": {}
}
"""
