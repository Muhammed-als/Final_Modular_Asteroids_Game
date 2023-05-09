package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class PlayerControlSystemTest extends TestCase {
    private PlayerControlSystem playerControlSystem;
    private Player player;
    private GameData gameData;
    private World world;

    @Before
    public void setUp() {
        playerControlSystem = new PlayerControlSystem();
        player = new Player();
        gameData = new GameData();
        world = new World();
        world.addEntity(player);
        player.add(new MovingPart(0,0,0,0));
        player.add(new PositionPart(0,0,0));
        player.add(new LifePart(1));
        gameData.getKeys().setKey(GameKeys.SPACE,true);
    }
    @Test
    public void testProcess() {
        playerControlSystem.process(gameData,world);
        assertEquals(1,world.getEntities(Player.class).size());
    }



}