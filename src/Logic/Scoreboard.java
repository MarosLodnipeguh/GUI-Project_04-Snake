package Logic;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scoreboard {

    private static final int MAX_SCORES = 10;
    private static final int MAX_NAME_LENGTH = 255;
    private static final String FILENAME = "src/Logic/scores.bin";



    public static void addScore(String playerName, int score) {
        List<ScoreEntry> scores = loadScores();
        scores.add(new ScoreEntry(playerName, score));
        Collections.sort(scores);
        if (scores.size() > MAX_SCORES) {
            scores = scores.subList(0, MAX_SCORES);
        }
        saveScores(scores);
    }

    public static List<ScoreEntry> displayScores() {
        List<ScoreEntry> scores = loadScores();
        return scores;

//        for (int i = 0; i < scores.size(); i++) {
//            ScoreEntry entry = scores.get(i);
//            System.out.println((i + 1) + ". " + entry.getPlayerName() + " - " + entry.getScore());
//        }
    }

    private static List<ScoreEntry> loadScores() {

        List<ScoreEntry> scores = new ArrayList<>();

        try (DataInputStream input = new DataInputStream(new FileInputStream(FILENAME))) {
            while (input.available() > 0) {
                int nameLength = input.readByte();
                byte[] nameBytes = new byte[nameLength];
                input.readFully(nameBytes);
                String playerName = new String(nameBytes);
                int score = input.readInt();
                scores.add(new ScoreEntry(playerName, score));
            }


        } catch (IOException e) {
            System.out.println("Error loading scores: " + e.getMessage());
        }
        return scores;
    }

    private static void saveScores(List<ScoreEntry> scores) {
        try (DataOutputStream output = new DataOutputStream(new FileOutputStream(FILENAME))) {
            for (ScoreEntry entry : scores) {

                byte[] nameBytes = new byte[MAX_NAME_LENGTH];
                int nameLength = 0;


                if (entry.getPlayerName() != null) {
                    nameBytes = entry.getPlayerName().getBytes();
                    nameLength = Math.min(nameBytes.length, MAX_NAME_LENGTH);
                }
                else {
                    String nullName = "Unknown";
                    nameBytes = nullName.getBytes();
                    nameLength = Math.min(nameBytes.length, MAX_NAME_LENGTH);
                }


                output.writeByte(nameLength);
                output.write(nameBytes, 0, nameLength);


                output.writeInt(entry.getScore());
            }
        } catch (IOException e) {
            System.out.println("Error saving scores: " + e.getMessage());
        }
    }

    public static class ScoreEntry implements Comparable<ScoreEntry> {
        private String playerName;
        private int score;

        public ScoreEntry(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getScore() {
            return score;
        }

        @Override
        public int compareTo(ScoreEntry other) {
            return Integer.compare(other.score, this.score); // Sort in descending order
        }
    }




}
