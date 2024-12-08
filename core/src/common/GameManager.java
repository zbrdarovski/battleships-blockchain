package common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import CellState;
import Battleship;

public class GameManager {

    public static final GameManager INSTANCE = new GameManager();

    private static final String INIT_MOVE_KEY = "initMove";

    private final Preferences PREFS;
    private CellState initMove;

    FileHandle file1;
    FileHandle file2;

    Json json;

    private GameManager() {
        json = new Json();
        file1 =  Gdx.files.local("player1.json");
        file2 = Gdx.files.local("player2.json");
        PREFS = Gdx.app.getPreferences(Battleship.class.getSimpleName());
        String moveName = PREFS.getString(INIT_MOVE_KEY, CellState.ONE.name());
        initMove = CellState.valueOf(moveName);
    }

    public void serialize(Player playerOne, Player playerTwo) {
        String playerOneString = json.toJson(playerOne);
        String playerTwoString = json.toJson(playerTwo);
        file1.writeString(playerOneString, false);
        file2.writeString(playerTwoString, false);
    }

    public Array<Player> deserialize() {
        Array<Player> players = new Array<>();
        Player player1 =  json.fromJson(Player.class, file1.readString());
        Player player2 = json.fromJson(Player.class, file2.readString());
        players.add(player1);
        players.add(player2);
        return players;
    }

    public CellState getInitMove() {
        return initMove;
    }
}