import java.util.Arrays;

public class FantasyLeagueAutoDraftRankingEngine {

    static class Player implements Comparable<Player> {

        private String name;
        private int matchesPlayed;
        private double battingAverage;
        private boolean injured;

        public Player(String name,
                      int matchesPlayed,
                      double battingAverage,
                      boolean injured) {

            this.name = name;
            this.matchesPlayed = matchesPlayed;
            this.battingAverage = battingAverage;
            this.injured = injured;
        }

        // Rule 1: Experienced players
        static boolean isDraftable(int matchesPlayed) {

            return matchesPlayed >= 10;
        }

        // Rule 2: Newer but fit players
        static boolean isDraftable(int matchesPlayed,
                                   boolean injured) {

            return matchesPlayed >= 5 && !injured;
        }

        // Getter for name
        public String getName() {
            return name;
        }

        // Compare players by batting average
        // in descending order
        @Override
        public int compareTo(Player other) {

            return Double.compare(
                    other.battingAverage,
                    this.battingAverage
            );
        }
    }

    static String draftAndRank(Player[] players) {

        Player[] draftable =
                new Player[players.length];

        int count = 0;

        // Find draftable players
        for (int i = 0; i < players.length; i++) {

            Player player = players[i];

            if (Player.isDraftable(
                    player.matchesPlayed)) {

                draftable[count] = player;
                count++;

            } else if (Player.isDraftable(
                    player.matchesPlayed,
                    player.injured)) {

                draftable[count] = player;
                count++;
            }
        }

        // Create array containing only draftable players
        Player[] finalDraftable =
                Arrays.copyOf(draftable, count);

        // Sort using compareTo()
        Arrays.sort(finalDraftable);

        // Build result
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < finalDraftable.length; i++) {

            result.append(i + 1)
                  .append(". ")
                  .append(finalDraftable[i].getName());

            if (i < finalDraftable.length - 1) {
                result.append(" | ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        Player[] players = {

            new Player(
                "Virat",
                15,
                48.0,
                false
            ),

            new Player(
                "Rahul",
                7,
                55.0,
                false
            ),

            new Player(
                "Sameer",
                3,
                60.0,
                false
            ),

            new Player(
                "Dev",
                12,
                20.0,
                true
            )
        };

        String result = draftAndRank(players);

        System.out.println(result);
    }
}