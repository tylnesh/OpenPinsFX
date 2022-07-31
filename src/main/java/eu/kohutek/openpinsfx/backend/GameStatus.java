package eu.kohutek.openpinsfx.backend;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Arrays;

@Data
public class GameStatus {
    private int round;
    private int ballsThrown;
    private int score;
    @JsonIgnore
    private LocalDateTime time;
    private boolean[] pinState;

    public GameStatus() {
        this.round = 0;
        this.ballsThrown = 0;
        this.score = 0;
        this.time = LocalDateTime.now();
        this.pinState = new boolean[]{false, false, false, false, false, false, false, false, false};
    }

    public void updateGameStatus(GameStatus gs) {
        System.out.println("updating status:");
        System.out.println(gs);
        this.setRound(gs.getRound());
        this.setBallsThrown(gs.getBallsThrown());
        this.setScore(gs.getScore());
        for (int i = 0; i<9;i++) {
            this.pinState[i] = gs.getPinState()[i];
        }
        this.setTime(gs.getTime());
    }


    @Override
    public String toString() {
        return "GameStatus{" +
                "round=" + round +
                ", ballsThrown=" + ballsThrown +
                ", score=" + score +
                ", time=" + time +
                ", pinState=" + Arrays.toString(pinState) +
                '}';
    }
}

