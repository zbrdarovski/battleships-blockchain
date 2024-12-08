package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Battleship;
import CellActor;
import RestApiClient;
import assets.AssetDescriptors;
import assets.RegionNames;
import common.GameManager;
import common.Player;
import config.GameConfig;

public class SkinScreen extends ScreenAdapter {
    private final Battleship game;
    private final AssetManager assetManager;

    private Viewport viewport;
    private Stage stage;

    private Skin skin;
    private TextureAtlas gameplayAtlas;

    public SkinScreen(Battleship game) {
        this.game = game;
        assetManager = game.getAssetManager();
    }

    @Override
    public void show() {
        viewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        stage = new Stage(viewport, game.getBatch());

        skin = assetManager.get(AssetDescriptors.UI_SKIN);
        gameplayAtlas = assetManager.get(AssetDescriptors.GAMEPLAY);
        assetManager.finishLoading();

        stage.addActor(createGrid());
        stage.addActor(createGrid());
        stage.addActor(createBackButton());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0 / 255f, 191 / 255f, 255 / 255f, 0f);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private Actor createGrid() {
        final Table table = new Table();

        table.setDebug(false);   // turn on all debug lines (table, cell, and widget)

        final Table grid = new Table();
        grid.defaults().size(90f);   // all cells will be the same size
        grid.setDebug(false);

        final ArrayList<TextureRegion> ships = new ArrayList<>();
        ships.add(gameplayAtlas.findRegion(RegionNames.PIRATE_SHIP));
        ships.add(gameplayAtlas.findRegion(RegionNames.CARGO_WOOD));
        ships.add(gameplayAtlas.findRegion(RegionNames.CARGO_METAL));
        ships.add(gameplayAtlas.findRegion(RegionNames.CARGO_CONTAINER));

        final TextureRegion waterRegion = gameplayAtlas.findRegion(RegionNames.WATER);

        final Array<Player> players = GameManager.INSTANCE.deserialize();

        final Label unlockedShipLabel = new Label("OWNED     ", skin);
        final Label lockedShipLabel = new Label("LOCKED    ", skin);
        final Label fiveHundredShipLabel = new Label("$500          ", skin);
        final Label oneThousandShipLabel = new Label("$1000        ", skin);
        final Label twoThousandShipLabel = new Label("$2000        ", skin);

        Array<Label> priceLabelsPlayerOne;
        Array<Label> priceLabelsPlayerTwo;

        priceLabelsPlayerOne = new Array<>();
        priceLabelsPlayerTwo = new Array<>();

        priceLabelsPlayerOne.add(unlockedShipLabel);

        if(players.get(0).getScore() > 3) {
            if(players.get(0).purchasedShips[1]){
                priceLabelsPlayerOne.add(unlockedShipLabel);
            } else {
                priceLabelsPlayerOne.add(fiveHundredShipLabel);
            }
        } else {
            priceLabelsPlayerOne.add(lockedShipLabel);
        }

        if(players.get(0).getScore() > 6) {
            if(players.get(0).purchasedShips[2]){
                priceLabelsPlayerOne.add(unlockedShipLabel);
            } else {
                priceLabelsPlayerOne.add(oneThousandShipLabel);
            }
        } else {
            priceLabelsPlayerOne.add(lockedShipLabel);
        }

        if(players.get(0).getScore() > 9) {
            if(players.get(0).purchasedShips[3]){
                priceLabelsPlayerOne.add(unlockedShipLabel);
            } else {
                priceLabelsPlayerOne.add(twoThousandShipLabel);
            }
        } else {
            priceLabelsPlayerOne.add(lockedShipLabel);
        }

        priceLabelsPlayerTwo.add(unlockedShipLabel);

        if(players.get(1).getScore() > 3) {
            if(players.get(1).purchasedShips[1]){
                priceLabelsPlayerTwo.add(unlockedShipLabel);
            } else {
                priceLabelsPlayerTwo.add(fiveHundredShipLabel);
            }
        } else {
            priceLabelsPlayerTwo.add(lockedShipLabel);
        }

        if(players.get(1).getScore() > 6) {
            if(players.get(1).purchasedShips[2]){
                priceLabelsPlayerTwo.add(unlockedShipLabel);
            } else {
                priceLabelsPlayerTwo.add(oneThousandShipLabel);
            }
        } else {
            priceLabelsPlayerTwo.add(lockedShipLabel);
        }

        if(players.get(1).getScore() > 9) {
            if(players.get(1).purchasedShips[3]){
                priceLabelsPlayerTwo.add(unlockedShipLabel);
            } else {
                priceLabelsPlayerTwo.add(twoThousandShipLabel);
            }
        } else {
            priceLabelsPlayerTwo.add(lockedShipLabel);
        }

        for (int row = 0; row < 6; row++) {
            if (row == 0) {
                final Label playerLabel = new Label(players.get(0).getName() + " " + players.get(0).getBalance() + "$", skin);
                grid.add(playerLabel).row();
            } else if (row == 1) {
                for (int column = 0; column < 4; column++) {
                    switch (column) {
                        case 0: {
                            final CellActor cell;
                            cell = new CellActor(ships.get(column));
                            cell.addListener(new ClickListener() {
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    players.get(0).setShipNumber(0);
                                    GameManager.INSTANCE.serialize(players.get(0), players.get(1));
                                    game.setScreen(new SkinScreen(game));
                                }
                            });
                            grid.add(cell);
                            break;
                        }
                        case 1: {
                            final CellActor cell;
                            if(players.get(0).getScore() > 3){
                                cell = new CellActor(ships.get(column));
                                if(players.get(0).purchasedShips[1]) {
                                    cell.addListener(new ClickListener() {
                                        @Override
                                        public void clicked(InputEvent event, float x, float y) {
                                            players.get(0).setShipNumber(1);
                                            GameManager.INSTANCE.serialize(players.get(0), players.get(1));
                                            game.setScreen(new SkinScreen(game));
                                        }
                                    });
                                } else if(players.get(0).getBalance() >= 500) {
                                    cell.addListener(new ClickListener() {
                                        @Override
                                        public void clicked(InputEvent event, float x, float y) {
                                            players.get(0).setBalance(players.get(0).getBalance() - 500);
                                            players.get(0).purchasedShips[1] = true;
                                            players.get(0).setShipNumber(1);
                                            try {
                                                RestApiClient.buyPlayerOneShipTwo();
                                                RestApiClient.mineTransactions();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            GameManager.INSTANCE.serialize(players.get(0), players.get(1));
                                            game.setScreen(new SkinScreen(game));
                                        }
                                    });
                                }
                            } else {
                                cell = new CellActor(waterRegion);
                            }
                            grid.add(cell);
                            break;
                        }
                        case 2: {
                            final CellActor cell;
                            if(players.get(0).getScore() > 6){
                                cell = new CellActor(ships.get(column));
                                if(players.get(0).purchasedShips[2]) {
                                    cell.addListener(new ClickListener() {
                                        @Override
                                        public void clicked(InputEvent event, float x, float y) {
                                            players.get(0).setShipNumber(2);
                                            GameManager.INSTANCE.serialize(players.get(0), players.get(1));
                                            game.setScreen(new SkinScreen(game));
                                        }
                                    });
                                } else if(players.get(0).getBalance() >= 1000) {
                                    cell.addListener(new ClickListener() {
                                        @Override
                                        public void clicked(InputEvent event, float x, float y) {
                                            players.get(0).setBalance(players.get(0).getBalance() - 1000);
                                            players.get(0).purchasedShips[2] = true;
                                            players.get(0).setShipNumber(2);
                                            try {
                                                RestApiClient.buyPlayerOneShipThree();
                                                RestApiClient.mineTransactions();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            GameManager.INSTANCE.serialize(players.get(0), players.get(1));
                                            game.setScreen(new SkinScreen(game));
                                        }
                                    });
                                }
                            } else {
                                cell = new CellActor(waterRegion);
                            }
                            grid.add(cell);
                            break;
                        }
                        case 3: {
                            final CellActor cell;
                            if(players.get(0).getScore() > 9){
                                cell = new CellActor(ships.get(column));
                                if(players.get(0).purchasedShips[3]) {
                                    cell.addListener(new ClickListener() {
                                        @Override
                                        public void clicked(InputEvent event, float x, float y) {
                                            players.get(0).setShipNumber(3);
                                            GameManager.INSTANCE.serialize(players.get(0), players.get(1));
                                            game.setScreen(new SkinScreen(game));
                                        }
                                    });
                                } else if(players.get(0).getBalance() >= 2000) {
                                    cell.addListener(new ClickListener() {
                                        @Override
                                        public void clicked(InputEvent event, float x, float y) {
                                            players.get(0).setBalance(players.get(0).getBalance() - 2000);
                                            players.get(0).purchasedShips[3] = true;
                                            players.get(0).setShipNumber(3);
                                            try {
                                                RestApiClient.buyPlayerOneShipFour();
                                                RestApiClient.mineTransactions();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            GameManager.INSTANCE.serialize(players.get(0), players.get(1));
                                            game.setScreen(new SkinScreen(game));
                                        }
                                    });
                                }
                            } else {
                                cell = new CellActor(waterRegion);
                            }
                            grid.add(cell).row();
                            break;
                        }
                    }
                }

            } else if (row == 2) {
                Label tempLabel = new Label( "", skin);
                for(int i = 0; i < 4; i = i + 1){
                    StringBuilder sb = new StringBuilder();
                    sb.append(tempLabel.getText());
                    sb.append(priceLabelsPlayerOne.get(i).getText());
                    tempLabel.setText(sb);
                }
                grid.add(tempLabel).row();
            } else if (row == 3) {
                final Label playerLabel = new Label(players.get(1).getName() + " " + players.get(1).getBalance() + "$", skin);
                grid.add(playerLabel).row();
            } else if (row == 4) {
                for (int column = 0; column < 4; column++) {
                    switch (column) {
                        case 0: {
                            final CellActor cell;
                            cell = new CellActor(ships.get(column));
                            cell.addListener(new ClickListener() {
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    players.get(1).setShipNumber(0);
                                    GameManager.INSTANCE.serialize(players.get(0), players.get(1));
                                    game.setScreen(new SkinScreen(game));
                                }
                            });
                            grid.add(cell);
                            break;
                        }
                        case 1: {
                            final CellActor cell;
                            if(players.get(1).getScore() > 3){
                                cell = new CellActor(ships.get(column));
                                if(players.get(1).purchasedShips[1]) {
                                    cell.addListener(new ClickListener() {
                                        @Override
                                        public void clicked(InputEvent event, float x, float y) {
                                            players.get(1).setShipNumber(1);
                                            GameManager.INSTANCE.serialize(players.get(0), players.get(1));
                                            game.setScreen(new SkinScreen(game));
                                        }
                                    });
                                } else if(players.get(1).getBalance() >= 500) {
                                    cell.addListener(new ClickListener() {
                                        @Override
                                        public void clicked(InputEvent event, float x, float y) {
                                            players.get(1).setBalance(players.get(1).getBalance() - 500);
                                            players.get(1).purchasedShips[1] = true;
                                            players.get(1).setShipNumber(1);
                                            try {
                                                RestApiClient.buyPlayerTwoShipTwo();
                                                RestApiClient.mineTransactions();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            GameManager.INSTANCE.serialize(players.get(0), players.get(1));
                                            game.setScreen(new SkinScreen(game));
                                        }
                                    });
                                }
                            } else {
                                cell = new CellActor(waterRegion);
                            }
                            grid.add(cell);
                            break;
                        }
                        case 2: {
                            final CellActor cell;
                            if(players.get(1).getScore() > 6){
                                cell = new CellActor(ships.get(column));
                                if(players.get(1).purchasedShips[2]) {
                                    cell.addListener(new ClickListener() {
                                        @Override
                                        public void clicked(InputEvent event, float x, float y) {
                                            players.get(1).setShipNumber(2);
                                            GameManager.INSTANCE.serialize(players.get(0), players.get(1));
                                            game.setScreen(new SkinScreen(game));
                                        }
                                    });
                                } else if(players.get(1).getBalance() >= 1000) {
                                    cell.addListener(new ClickListener() {
                                        @Override
                                        public void clicked(InputEvent event, float x, float y) {
                                            players.get(1).setBalance(players.get(1).getBalance() - 1000);
                                            players.get(1).purchasedShips[2] = true;
                                            players.get(1).setShipNumber(2);
                                            try {
                                                RestApiClient.buyPlayerTwoShipThree();
                                                RestApiClient.mineTransactions();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            GameManager.INSTANCE.serialize(players.get(0), players.get(1));
                                            game.setScreen(new SkinScreen(game));
                                        }
                                    });
                                }
                            } else {
                                cell = new CellActor(waterRegion);
                            }
                            grid.add(cell);
                            break;
                        }
                        case 3: {
                            final CellActor cell;
                            if(players.get(1).getScore() > 9){
                                cell = new CellActor(ships.get(column));
                                if(players.get(1).purchasedShips[3]) {
                                    cell.addListener(new ClickListener() {
                                        @Override
                                        public void clicked(InputEvent event, float x, float y) {
                                            players.get(1).setShipNumber(3);
                                            GameManager.INSTANCE.serialize(players.get(0), players.get(1));
                                            game.setScreen(new SkinScreen(game));
                                        }
                                    });
                                } else if(players.get(1).getBalance() >= 2000) {
                                    cell.addListener(new ClickListener() {
                                        @Override
                                        public void clicked(InputEvent event, float x, float y) {
                                            players.get(1).setBalance(players.get(1).getBalance() - 2000);
                                            players.get(1).purchasedShips[3] = true;
                                            players.get(1).setShipNumber(3);
                                            try {
                                                RestApiClient.buyPlayerTwoShipFour();
                                                RestApiClient.mineTransactions();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                            GameManager.INSTANCE.serialize(players.get(0), players.get(1));
                                            game.setScreen(new SkinScreen(game));
                                        }
                                    });
                                }
                            } else {
                                cell = new CellActor(waterRegion);
                            }
                            grid.add(cell).row();
                            break;
                        }
                    }
                }
            } else {
                Label tempLabel = new Label( "", skin);
                for(int i = 0; i < 4; i = i + 1){
                    StringBuilder sb = new StringBuilder();
                    sb.append(tempLabel.getText());
                    sb.append(priceLabelsPlayerTwo.get(i).getText());
                    tempLabel.setText(sb);
                }
                grid.add(tempLabel).row();
            }
        }

        table.add(grid).row();
        table.setPosition(GameConfig.WORLD_WIDTH / 2 - 30f, 40f);
        table.setFillParent(true);
        table.pack();

        return table;
    }

    private Actor createBackButton() {
        final TextButton backButton = new TextButton("x", skin);
        backButton.setWidth(100);
        backButton.setHeight(100);
        backButton.setPosition(GameConfig.HUD_WIDTH / 2f - backButton.getWidth() / 2f, 0f);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });
        return backButton;
    }
}
