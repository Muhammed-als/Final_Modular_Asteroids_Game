package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked")
public class PlayerPluginTest {
    private PlayerPlugin playerPlugin;
    private GameData gameData;
    private World world;

    @Before
    public void setUp() {
        playerPlugin = new PlayerPlugin();
        gameData = new GameData();
        world = new World();
        playerPlugin.start(gameData,world);
    }
    @After
    public void tearDown() {
        playerPlugin.stop(gameData,world);
    }

    @Test
    public void TestIfPlayerPluginAddedToGameDataTest() {
        assertEquals(1, world.getEntities((Player.class)).size());
    }
    @Test
    public void deletePlayerTest() {
        this.playerPlugin.stop(gameData,world);
        assertEquals(0, world.getEntities(Player.class).size());
    }
}