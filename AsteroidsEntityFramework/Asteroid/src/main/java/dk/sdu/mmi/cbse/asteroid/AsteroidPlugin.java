package dk.sdu.mmi.cbse.asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.playersystem.Player;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {
    private Entity asteroid;
    private Random random;
    float maxSpeed;
    public AsteroidPlugin(){
        random = new Random();
    }
    @Override
    public void start(GameData gameData, World world) {

        for (int i = 1; i <= random.nextInt(2,10); i++) {
            asteroid = createAsteroid(gameData);
            for(Entity entity : world.getEntities()){
                PositionPart entityPositionPart = entity.getPart(PositionPart.class);
                PositionPart astroidPositionPart = asteroid.getPart(PositionPart.class);
                if(((int)astroidPositionPart.getX() < (int)entityPositionPart.getX())||
                    (int)astroidPositionPart.getX() > (int)entityPositionPart.getX() &&
                    (int)astroidPositionPart.getY() < (int) entityPositionPart.getY()||
                    (int) astroidPositionPart.getY() > (int) entityPositionPart.getY()){
                    world.addEntity(asteroid);
                }
            }

        }





    }

    @Override
    public void stop(GameData gameData, World world) {

    }
    private Entity createAsteroid(GameData gameData) {
        random = new Random();
        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 100;
        float rotationSpeed = 5;
        float x = random.nextFloat() * gameData.getDisplayWidth();
        float y = random.nextFloat() * gameData.getDisplayHeight();
        float radians = random.nextFloat(3.1415f /5,3.1415f / 2);
        Entity asteroids = new Asteroid();
        asteroids.setRadius(30);
        asteroids.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        asteroids.add(new PositionPart(x, y, radians));
        asteroids.add(new LifePart(1));
        return asteroids;
    }
}
