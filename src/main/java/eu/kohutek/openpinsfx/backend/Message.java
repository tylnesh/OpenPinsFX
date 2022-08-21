package eu.kohutek.openpinsfx.backend;

import lombok.Data;

@Data
public class Message {
    private long timestamp;
    private Command command;
    private GameStatus gameStatus;


    public Message(Command command) {
        this.command = command;
    }

    public Message(Command command, GameStatus gameStatus) {
        this.command = command;
        this.gameStatus = gameStatus;
    }


}
