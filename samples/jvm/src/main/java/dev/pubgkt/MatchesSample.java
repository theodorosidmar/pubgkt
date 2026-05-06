package dev.pubgkt;

import java.util.List;

import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;

import dev.pubgkt.matches.GetMatchByIdKt;
import dev.pubgkt.matches.Match;
import dev.pubgkt.players.GetPlayersByNameKt;
import dev.pubgkt.players.Player;
import dev.pubgkt.players.PlayerMatch;

public class MatchesSample {
    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Usage: MatchesSample <api-key>");
        }

        PubgApi api = new PubgApi(args[0]);

        System.out.println("=== getMatchById ===");
        List<Player> players = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetPlayersByNameKt.getPlayersByNames(api, List.of("sparkingg"), Platform.STEAM, cont)
        );
        List<PlayerMatch> matches = players.get(0).getMatches();
        String matchId = matches.get(0).getId();

        Match match = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetMatchByIdKt.getMatchById(api, matchId, Platform.STEAM, cont)
        );
        System.out.println("Match: " + match.getId());
        System.out.println("Map: " + match.getMapName() + ", Mode: " + match.getGameMode() + ", Duration: " + match.getDuration() + "s");
        System.out.println("Participants: " + match.getParticipants().size() + " teams");
    }
}
