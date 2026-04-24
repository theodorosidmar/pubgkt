package pubgkt

internal val zeroedSurvivalStats = SurvivalStats(
    total = 0.0,
    average = 0.0,
    careerBest = 0.0,
    lastMatchValue = 0.0,
)

internal const val SURVIVAL_MASTERY_RESPONSE_JSON: String = """
{
  "data": {
    "type": "survivalMasterySummary",
    "id": "account.82bad0072f31455d8d9f8d834da2f2f3",
    "attributes": {
      "tier": 5,
      "level": 500,
      "lastMatchId": "",
      "totalMatchesPlayed": 27545,
      "stats": {
        "airDropsCalled": {
          "total": 0,
          "average": 0,
          "careerBest": 0,
          "lastMatchValue": 0
        },
        "damageDealt": {
          "total": 0,
          "average": 0,
          "careerBest": 0,
          "lastMatchValue": 0
        },
        "damageTaken": {
          "total": 0,
          "average": 0,
          "careerBest": 0,
          "lastMatchValue": 0
        },
        "distanceBySwimming": {
          "total": 0,
          "average": 0,
          "careerBest": 0,
          "lastMatchValue": 0
        },
        "distanceByVehicle": {
          "total": 0,
          "average": 0,
          "careerBest": 0,
          "lastMatchValue": 0
        },
        "distanceOnFoot": {
          "total": 0,
          "average": 0,
          "careerBest": 0,
          "lastMatchValue": 0
        },
        "distanceTotal": {
          "total": 0,
          "average": 0,
          "careerBest": 0,
          "lastMatchValue": 0
        },
        "healed": {
          "total": 0,
          "average": 0,
          "careerBest": 0,
          "lastMatchValue": 0
        },
        "hotDropLandings": {
          "total": 0
        },
        "enemyCratesLooted": {
          "total": 0,
          "average": 0,
          "careerBest": 0,
          "lastMatchValue": 0
        },
        "uniqueItemsLooted": {
          "total": 0,
          "average": 0,
          "careerBest": 0,
          "lastMatchValue": 0
        },
        "position": {
          "average": 0,
          "careerBest": 0,
          "lastMatchValue": 0
        },
        "revived": {
          "total": 0,
          "average": 0,
          "careerBest": 0,
          "lastMatchValue": 0
        },
        "teammatesRevived": {
          "total": 0,
          "average": 0,
          "careerBest": 0,
          "lastMatchValue": 0
        },
        "timeSurvived": {
          "total": 0,
          "average": 0,
          "careerBest": 0,
          "lastMatchValue": 1197
        },
        "throwablesThrown": {
          "total": 0,
          "average": 0,
          "careerBest": 0,
          "lastMatchValue": 0
        },
        "top10": {
          "total": 0
        }
      },
      "xp": 8579174
    }
  },
  "links": {
    "self": "https://api.pubg.com/shards/steam/players/account.82bad0072f31455d8d9f8d834da2f2f3/survival_mastery"
  },
  "meta": {}
}
"""
