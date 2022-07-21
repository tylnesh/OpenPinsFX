package eu.kohutek.openpinsfx.backend;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GameStatus {
    private int round;
    private int ballsThrown;
    private int score;
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
}

