package dk.sdu.mmi.cbse.enemysystem;

import com.badlogic.gdx.graphics.Color;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.EntityType;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class EnemyPlugin implements IGamePluginService {
    private Entity enemy;
    private Random random;
    public EnemyPlugin() {

    }
    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);

    }
    private Entity createEnemyShip(GameData gameData) {
        random = new Random();
        float maxSpeed = 300;
        float x = random.nextFloat() * gameData.getDisplayWidth();
        float y = random.nextFloat() * (gameData.getDisplayHeight() - 20);
        float radians = 3.1415f / 2;

        Entity enemyShip = new Enemy();
        enemyShip.setRadius(8);
        enemyShip.add(new MovingPart(0, 0, maxSpeed, 0));
        enemyShip.add(new PositionPart(x, y, radians));
        enemyShip.add(new LifePart(1));
        enemyShip.setColor(Color.RED);
        enemyShip.setType(EntityType.ENEMY);

        return enemyShip;
    }
}
