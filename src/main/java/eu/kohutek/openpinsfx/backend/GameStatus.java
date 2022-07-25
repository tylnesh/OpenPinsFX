package eu.kohutek.openpinsfx.backend;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Arrays;

@Getter
@Setter
public class GameStatus {
    private int round;
    private int ballsThrown;
    private int score;
    @JsonIgnore
    private LocalDateTime time;
    private boolean[] pinState;




    public GameStatus() {
        round = 0;
        ballsThrown = 0;
        score = 0;
        time = LocalDateTime.now();
        pinState = new boolean[]{false, false, false, false, false, false, false, false, false};
    }
    public void updateGameStatus(GameStatus gs) {
        this.setRound(gs.getRound());
        this.setBallsThrown(gs.getBallsThrown());
        this.setScore(gs.getScore());
        this.setPinState(gs.getPinState());
        this.time = LocalDateTime.now();
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

