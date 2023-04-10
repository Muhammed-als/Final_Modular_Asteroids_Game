package dk.sdu.mmi.cbse.asteroid;
import com.badlogic.gdx.graphics.Color;
import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.EntityType;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;

@SuppressWarnings("unchecked")
public class AsteroidPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        int min = 2;
        int max = 10;
        int randomNum = min + (int)(Math.random() * ((max - min) + 1));
        for (int i = 1; i <= randomNum; i++) {
            Entity asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {
        random = new Random();
        float maxSpeed = 50;
        float minRadians = 3.1415f / 5;
        float maxRadians = 3.1415f;
        float radians = minRadians + (maxRadians - minRadians) * random.nextFloat();
        float x = random.nextFloat() * gameData.getDisplayWidth();
        float y = random.nextFloat() * gameData.getDisplayHeight();
        Entity asteroids = new Asteroid();
        asteroids.setRadius(30);
        asteroids.add(new MovingPart(0, maxSpeed, maxSpeed, 0));
        asteroids.add(new PositionPart(x, y, radians));
        asteroids.add(new LifePart(3));
        asteroids.setColor(Color.WHITE);
        asteroids.setType(EntityType.ASTEROID);
        return asteroids;
    }
}