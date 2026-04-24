package pubgkt

internal const val MATCH_TDM_RESPONSE_JSON = """
{
  "data": {
    "type": "match",
    "id": "a131e486-5bcf-4c2e-aa5a-515489ee57aa",
    "attributes": {
      "titleId": "bluehole-pubg",
      "shardId": "steam",
      "tags": null,
      "isCustomMatch": false,
      "matchType": "arcade",
      "stats": null,
      "gameMode": "tdm",
      "mapName": "Tiger_Main",
      "seasonState": "progress",
      "createdAt": "2026-04-07T22:26:23Z",
      "duration": 376
    },
    "relationships": {
      "rosters": {
        "data": [
          {
            "type": "roster",
            "id": "4f2ea7bf-87df-4e21-a574-283ed630f0b8"
          },
          {
            "type": "roster",
            "id": "6afe1f05-a32c-4790-8027-f93c88d19138"
          }
        ]
      },
      "assets": {
        "data": [
          {
            "type": "asset",
            "id": "e32e676f-32d1-11f1-82f4-f20ef6404fbd"
          }
        ]
      }
    },
    "links": {
      "self": "https://api.pubg.com/shards/steam/matches/a131e486-5bcf-4c2e-aa5a-515489ee57aa",
      "schema": ""
    }
  },
  "included": [
    {
      "type": "participant",
      "id": "7c3686b2-eeb6-459b-a895-59dd23f77f60",
      "attributes": {
        "stats": {
          "DBNOs": 0,
          "assists": 0,
          "boosts": 0,
          "damageDealt": 1728.1492,
          "deathType": "byplayer",
          "headshotKills": 6,
          "heals": 0,
          "killPlace": 3,
          "killStreaks": 4,
          "kills": 15,
          "longestKill": 67.94285,
          "name": "Lea99",
          "playerId": "account.b5d709445b6f4f2dab8bc643d53d745c",
          "revives": 0,
          "rideDistance": 0,
          "roadKills": 0,
          "swimDistance": 0,
          "teamKills": 0,
          "timeSurvived": 376,
          "vehicleDestroys": 0,
          "walkDistance": 1224.6572,
          "weaponsAcquired": 0,
          "winPlace": 1
        },
        "actor": "",
        "shardId": "steam"
      }
    },
    {
      "type": "participant",
      "id": "3b2bdcf4-c228-4c82-9b82-4b19d7464ffa",
      "attributes": {
        "stats": {
          "DBNOs": 0,
          "assists": 0,
          "boosts": 0,
          "damageDealt": 1379.5834,
          "deathType": "byplayer",
          "headshotKills": 4,
          "heals": 0,
          "killPlace": 4,
          "killStreaks": 4,
          "kills": 15,
          "longestKill": 85.474945,
          "name": "mOOii_",
          "playerId": "account.2f43d6cb679d42c7b806d380f8ad0f47",
          "revives": 0,
          "rideDistance": 0,
          "roadKills": 0,
          "swimDistance": 0,
          "teamKills": 0,
          "timeSurvived": 376,
          "vehicleDestroys": 0,
          "walkDistance": 1779.7885,
          "weaponsAcquired": 0,
          "winPlace": 1
        },
        "actor": "",
        "shardId": "steam"
      }
    },
    {
      "type": "participant",
      "id": "f35e9b1c-4724-4ef8-aab2-6f6c68a42c98",
      "attributes": {
        "stats": {
          "DBNOs": 0,
          "assists": 0,
          "boosts": 0,
          "damageDealt": 1218.1741,
          "deathType": "byplayer",
          "headshotKills": 2,
          "heals": 0,
          "killPlace": 6,
          "killStreaks": 4,
          "kills": 10,
          "longestKill": 79.01409,
          "name": "Larc-Pesi",
          "playerId": "account.719e3b47cac742cb8165d92ba07b2c5d",
          "revives": 0,
          "rideDistance": 0,
          "roadKills": 0,
          "swimDistance": 0,
          "teamKills": 0,
          "timeSurvived": 376,
          "vehicleDestroys": 0,
          "walkDistance": 1455.0079,
          "weaponsAcquired": 0,
          "winPlace": 1
        },
        "actor": "",
        "shardId": "steam"
      }
    },
    {
      "type": "participant",
      "id": "eac30ef9-a58a-40a2-a904-8784fc043630",
      "attributes": {
        "stats": {
          "DBNOs": 0,
          "assists": 0,
          "boosts": 0,
          "damageDealt": 3042.0586,
          "deathType": "byplayer",
          "headshotKills": 13,
          "heals": 0,
          "killPlace": 1,
          "killStreaks": 9,
          "kills": 27,
          "longestKill": 81.12098,
          "name": "sparkingg",
          "playerId": "account.91186dcca3cb4ad198fac1e4ab1d5b80",
          "revives": 0,
          "rideDistance": 0,
          "roadKills": 0,
          "swimDistance": 0,
          "teamKills": 0,
          "timeSurvived": 376,
          "vehicleDestroys": 0,
          "walkDistance": 1489.1478,
          "weaponsAcquired": 1,
          "winPlace": 1
        },
        "actor": "",
        "shardId": "steam"
      }
    },
    {
      "type": "participant",
      "id": "9226b850-c36d-4e6b-aaba-3d973d4d7c46",
      "attributes": {
        "stats": {
          "DBNOs": 0,
          "assists": 0,
          "boosts": 0,
          "damageDealt": 104.647896,
          "deathType": "byplayer",
          "headshotKills": 0,
          "heals": 0,
          "killPlace": 20,
          "killStreaks": 0,
          "kills": 0,
          "longestKill": 0,
          "name": "calabreasa31",
          "playerId": "account.75d190ec866143348713d0eccc190dea",
          "revives": 0,
          "rideDistance": 0,
          "roadKills": 0,
          "swimDistance": 0,
          "teamKills": 0,
          "timeSurvived": 376,
          "vehicleDestroys": 0,
          "walkDistance": 1501.9762,
          "weaponsAcquired": 0,
          "winPlace": 2
        },
        "actor": "",
        "shardId": "steam"
      }
    },
    {
      "type": "participant",
      "id": "154f1c24-0b42-4a6d-aa84-891f7672d265",
      "attributes": {
        "stats": {
          "DBNOs": 0,
          "assists": 0,
          "boosts": 0,
          "damageDealt": 553.71655,
          "deathType": "byplayer",
          "headshotKills": 1,
          "heals": 0,
          "killPlace": 16,
          "killStreaks": 1,
          "kills": 2,
          "longestKill": 54.938847,
          "name": "Dimas_fps",
          "playerId": "account.4377e147f6ca4c9eb06433e47632bb84",
          "revives": 0,
          "rideDistance": 0,
          "roadKills": 0,
          "swimDistance": 0,
          "teamKills": 0,
          "timeSurvived": 368,
          "vehicleDestroys": 0,
          "walkDistance": 1115.3616,
          "weaponsAcquired": 0,
          "winPlace": 2
        },
        "actor": "",
        "shardId": "steam"
      }
    },
    {
      "type": "roster",
      "id": "4f2ea7bf-87df-4e21-a574-283ed630f0b8",
      "attributes": {
        "won": "true",
        "shardId": "steam",
        "stats": {
          "rank": 1,
          "teamId": 1
        }
      },
      "relationships": {
        "team": {
          "data": null
        },
        "participants": {
          "data": [
            {
              "type": "participant",
              "id": "c05a2dc5-8199-49e0-bb58-e36ef6e1a9d7"
            },
            {
              "type": "participant",
              "id": "eb47c287-f53f-4aac-a4ad-8b2bede05a1c"
            },
            {
              "type": "participant",
              "id": "87ea51fd-5eec-4046-b7aa-e866b9bc83fa"
            },
            {
              "type": "participant",
              "id": "7c3686b2-eeb6-459b-a895-59dd23f77f60"
            },
            {
              "type": "participant",
              "id": "3b2bdcf4-c228-4c82-9b82-4b19d7464ffa"
            },
            {
              "type": "participant",
              "id": "f35e9b1c-4724-4ef8-aab2-6f6c68a42c98"
            },
            {
              "type": "participant",
              "id": "4c402ac6-8c07-4812-b9e0-0f9f12fac42b"
            },
            {
              "type": "participant",
              "id": "eac30ef9-a58a-40a2-a904-8784fc043630"
            }
          ]
        }
      }
    },
    {
      "type": "participant",
      "id": "c05a2dc5-8199-49e0-bb58-e36ef6e1a9d7",
      "attributes": {
        "stats": {
          "DBNOs": 0,
          "assists": 0,
          "boosts": 0,
          "damageDealt": 1691.0676,
          "deathType": "byplayer",
          "headshotKills": 1,
          "heals": 0,
          "killPlace": 2,
          "killStreaks": 3,
          "kills": 16,
          "longestKill": 86.221954,
          "name": "COSTABEBER_",
          "playerId": "account.5af599a5874c425ca0c8a7c1a43db9f5",
          "revives": 0,
          "rideDistance": 0,
          "roadKills": 0,
          "swimDistance": 0,
          "teamKills": 0,
          "timeSurvived": 376,
          "vehicleDestroys": 0,
          "walkDistance": 1435.6699,
          "weaponsAcquired": 3,
          "winPlace": 1
        },
        "actor": "",
        "shardId": "steam"
      }
    },
    {
      "type": "participant",
      "id": "eb47c287-f53f-4aac-a4ad-8b2bede05a1c",
      "attributes": {
        "shardId": "steam",
        "stats": {
          "DBNOs": 0,
          "assists": 0,
          "boosts": 0,
          "damageDealt": 776.9782,
          "deathType": "byplayer",
          "headshotKills": 2,
          "heals": 0,
          "killPlace": 11,
          "killStreaks": 3,
          "kills": 6,
          "longestKill": 57.952705,
          "name": "mdsDANGER",
          "playerId": "account.b3bb10747285434b928119034f927a62",
          "revives": 0,
          "rideDistance": 0,
          "roadKills": 0,
          "swimDistance": 0,
          "teamKills": 0,
          "timeSurvived": 376,
          "vehicleDestroys": 0,
          "walkDistance": 1297.2461,
          "weaponsAcquired": 3,
          "winPlace": 1
        },
        "actor": ""
      }
    },
    {
      "type": "participant",
      "id": "87ea51fd-5eec-4046-b7aa-e866b9bc83fa",
      "attributes": {
        "stats": {
          "DBNOs": 0,
          "assists": 0,
          "boosts": 0,
          "damageDealt": 507.66998,
          "deathType": "byplayer",
          "headshotKills": 0,
          "heals": 0,
          "killPlace": 15,
          "killStreaks": 1,
          "kills": 2,
          "longestKill": 40.27059,
          "name": "MACACOMORCEGO",
          "playerId": "account.37594b046a4840d5922869b56df3ce1a",
          "revives": 0,
          "rideDistance": 0,
          "roadKills": 0,
          "swimDistance": 0,
          "teamKills": 0,
          "timeSurvived": 376,
          "vehicleDestroys": 0,
          "walkDistance": 1405.0704,
          "weaponsAcquired": 0,
          "winPlace": 1
        },
        "actor": "",
        "shardId": "steam"
      }
    },
    {
      "type": "participant",
      "id": "96c564c9-8cbe-468b-a2d2-731fcb7bc06a",
      "attributes": {
        "stats": {
          "DBNOs": 0,
          "assists": 0,
          "boosts": 0,
          "damageDealt": 594.99994,
          "deathType": "byplayer",
          "headshotKills": 0,
          "heals": 0,
          "killPlace": 12,
          "killStreaks": 1,
          "kills": 4,
          "longestKill": 31.33716,
          "name": "Fortal085",
          "playerId": "account.2183e5f6daa34a20a57a79d332a080a2",
          "revives": 0,
          "rideDistance": 0,
          "roadKills": 0,
          "swimDistance": 0,
          "teamKills": 0,
          "timeSurvived": 372,
          "vehicleDestroys": 0,
          "walkDistance": 1924.3547,
          "weaponsAcquired": 0,
          "winPlace": 2
        },
        "actor": "",
        "shardId": "steam"
      }
    },
    {
      "type": "participant",
      "id": "0575eb38-525d-4252-b871-8d97aceccd95",
      "attributes": {
        "stats": {
          "DBNOs": 0,
          "assists": 0,
          "boosts": 0,
          "damageDealt": 991.39795,
          "deathType": "byplayer",
          "headshotKills": 3,
          "heals": 0,
          "killPlace": 9,
          "killStreaks": 3,
          "kills": 8,
          "longestKill": 40.276363,
          "name": "iTronquito",
          "playerId": "account.38f397d4b8db43e1b49661e565f8be17",
          "revives": 0,
          "rideDistance": 0,
          "roadKills": 0,
          "swimDistance": 0,
          "teamKills": 0,
          "timeSurvived": 178,
          "vehicleDestroys": 0,
          "walkDistance": 786.4929,
          "weaponsAcquired": 0,
          "winPlace": 2
        },
        "actor": "",
        "shardId": "steam"
      }
    },
    {
      "type": "participant",
      "id": "4c402ac6-8c07-4812-b9e0-0f9f12fac42b",
      "attributes": {
        "actor": "",
        "shardId": "steam",
        "stats": {
          "DBNOs": 0,
          "assists": 0,
          "boosts": 0,
          "damageDealt": 1092.0426,
          "deathType": "byplayer",
          "headshotKills": 1,
          "heals": 0,
          "killPlace": 8,
          "killStreaks": 3,
          "kills": 9,
          "longestKill": 33.959015,
          "name": "Bolsomito123",
          "playerId": "account.cc37c81eb43d49feb1e804bcaf745721",
          "revives": 0,
          "rideDistance": 0,
          "roadKills": 0,
          "swimDistance": 0,
          "teamKills": 0,
          "timeSurvived": 372,
          "vehicleDestroys": 0,
          "walkDistance": 1586.0918,
          "weaponsAcquired": 0,
          "winPlace": 1
        }
      }
    },
    {
      "type": "participant",
      "id": "8aa9adf8-10d7-44ec-9262-c3f3b5fc3c01",
      "attributes": {
        "stats": {
          "DBNOs": 0,
          "assists": 0,
          "boosts": 0,
          "damageDealt": 1594.4624,
          "deathType": "byplayer",
          "headshotKills": 4,
          "heals": 0,
          "killPlace": 5,
          "killStreaks": 2,
          "kills": 12,
          "longestKill": 81.72945,
          "name": "NeoNarco",
          "playerId": "account.c49b611e603b40df8db165f3906d81b5",
          "revives": 0,
          "rideDistance": 0,
          "roadKills": 0,
          "swimDistance": 0,
          "teamKills": 0,
          "timeSurvived": 369,
          "vehicleDestroys": 0,
          "walkDistance": 1796.1963,
          "weaponsAcquired": 0,
          "winPlace": 2
        },
        "actor": "",
        "shardId": "steam"
      }
    },
    {
      "type": "roster",
      "id": "6afe1f05-a32c-4790-8027-f93c88d19138",
      "attributes": {
        "stats": {
          "rank": 2,
          "teamId": 2
        },
        "won": "false",
        "shardId": "steam"
      },
      "relationships": {
        "participants": {
          "data": [
            {
              "type": "participant",
              "id": "96c564c9-8cbe-468b-a2d2-731fcb7bc06a"
            },
            {
              "type": "participant",
              "id": "c7af8cc1-f64e-4084-aee7-222429258f1d"
            },
            {
              "type": "participant",
              "id": "b9dfed06-7be6-4bc3-bc22-70b915aa132f"
            },
            {
              "type": "participant",
              "id": "0575eb38-525d-4252-b871-8d97aceccd95"
            },
            {
              "type": "participant",
              "id": "d504613f-8545-4b9f-ac57-b2257b0b90e8"
            },
            {
              "type": "participant",
              "id": "9226b850-c36d-4e6b-aaba-3d973d4d7c46"
            },
            {
              "type": "participant",
              "id": "8aa9adf8-10d7-44ec-9262-c3f3b5fc3c01"
            },
            {
              "type": "participant",
              "id": "154f1c24-0b42-4a6d-aa84-891f7672d265"
            }
          ]
        },
        "team": {
          "data": null
        }
      }
    },
    {
      "type": "asset",
      "id": "e32e676f-32d1-11f1-82f4-f20ef6404fbd",
      "attributes": {
        "name": "telemetry",
        "description": "",
        "createdAt": "2026-04-07T22:34:06Z",
        "URL": "https://telemetry-cdn.pubg.com/bluehole-pubg/steam/2026/04/07/22/34/e32e676f-32d1-11f1-82f4-f20ef6404fbd-telemetry.json"
      }
    },
    {
      "type": "participant",
      "id": "c7af8cc1-f64e-4084-aee7-222429258f1d",
      "attributes": {
        "shardId": "steam",
        "stats": {
          "DBNOs": 0,
          "assists": 0,
          "boosts": 0,
          "damageDealt": 350.40002,
          "deathType": "byplayer",
          "headshotKills": 0,
          "heals": 0,
          "killPlace": 13,
          "killStreaks": 1,
          "kills": 3,
          "longestKill": 26.774153,
          "name": "Leow_1",
          "playerId": "account.4675cb1ba9df42459d1886c5dd6ffac7",
          "revives": 0,
          "rideDistance": 0,
          "roadKills": 0,
          "swimDistance": 0,
          "teamKills": 0,
          "timeSurvived": 200,
          "vehicleDestroys": 0,
          "walkDistance": 852.70184,
          "weaponsAcquired": 0,
          "winPlace": 2
        },
        "actor": ""
      }
    },
    {
      "type": "participant",
      "id": "b9dfed06-7be6-4bc3-bc22-70b915aa132f",
      "attributes": {
        "stats": {
          "DBNOs": 0,
          "assists": 0,
          "boosts": 0,
          "damageDealt": 1255.5361,
          "deathType": "byplayer",
          "headshotKills": 4,
          "heals": 0,
          "killPlace": 7,
          "killStreaks": 3,
          "kills": 10,
          "longestKill": 61.864693,
          "name": "DKakeru",
          "playerId": "account.c5ec2864778d4d8b9c65ecae0f515ec9",
          "revives": 0,
          "rideDistance": 0,
          "roadKills": 0,
          "swimDistance": 0,
          "teamKills": 0,
          "timeSurvived": 376,
          "vehicleDestroys": 0,
          "walkDistance": 1493.0269,
          "weaponsAcquired": 0,
          "winPlace": 2
        },
        "actor": "",
        "shardId": "steam"
      }
    },
    {
      "type": "participant",
      "id": "d504613f-8545-4b9f-ac57-b2257b0b90e8",
      "attributes": {
        "stats": {
          "DBNOs": 0,
          "assists": 0,
          "boosts": 0,
          "damageDealt": 0,
          "deathType": "logout",
          "headshotKills": 0,
          "heals": 0,
          "killPlace": 21,
          "killStreaks": 0,
          "kills": 0,
          "longestKill": 0,
          "name": "Fucoviii",
          "playerId": "account.58d5b80b037c489cb4a95d4b7234d195",
          "revives": 0,
          "rideDistance": 0,
          "roadKills": 0,
          "swimDistance": 0,
          "teamKills": 0,
          "timeSurvived": 19,
          "vehicleDestroys": 0,
          "walkDistance": 0.00062821514,
          "weaponsAcquired": 0,
          "winPlace": 2
        },
        "actor": "",
        "shardId": "steam"
      }
    }
  ],
  "links": {
    "self": "https://api-origin.pubg-odapi.pubg.io/shards/steam/matches/a131e486-5bcf-4c2e-aa5a-515489ee57aa"
  },
  "meta": {}
}
"""
