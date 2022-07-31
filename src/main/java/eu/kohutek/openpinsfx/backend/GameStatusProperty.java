package eu.kohutek.openpinsfx.backend;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Arrays;

@Data
public class GameStatusProperty {
    private IntegerProperty round;
    private IntegerProperty ballsThrown;
    private IntegerProperty score;
    @JsonIgnore
    private LocalDateTime time;
    private BooleanProperty[] pinState;

    public GameStatusProperty() {
        this.round = new SimpleIntegerProperty(0);
        this.ballsThrown = new SimpleIntegerProperty(0);
        this.score = new SimpleIntegerProperty(0);
        this.time = LocalDateTime.now();
        this.pinState = new BooleanProperty[9];
        for (int i = 0; i< 9; i++) {
            this.pinState[i] = new SimpleBooleanProperty(false);
        }

    }

    public void updateGameStatusProperty(GameStatus gs) {
        System.out.println("updating status property:");
        this.round.set(gs.getRound());
        this.ballsThrown.set(gs.getBallsThrown());
        this.score.set(gs.getScore());
        for (int i = 0; i<9;i++) {
            this.pinState[i] = new SimpleBooleanProperty(gs.getPinState()[i]);
        }
        this.time = gs.getTime();
    }


    @Override
    public String toString() {
        return "GameStatusProperty{" +
                "round=" + round +
                ", ballsThrown=" + ballsThrown +
                ", score=" + score +
                ", time=" + time +
                ", pinState=" + Arrays.toString(pinState) +
                '}';
    }
}

