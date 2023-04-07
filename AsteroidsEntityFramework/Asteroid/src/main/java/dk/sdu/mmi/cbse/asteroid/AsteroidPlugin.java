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
        int min = 2;
        int max = 10;
        int randomNum = min + (int)(Math.random() * ((max - min) + 1));
        for (int i = 1; i <= randomNum; i++) {
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
        float minRadians = 3.1415f / 5;
        float maxRadians = 3.1415f / 2;
        float radians = minRadians + (maxRadians - minRadians) * random.nextFloat();
        float x = random.nextFloat() * gameData.getDisplayWidth();
        float y = random.nextFloat() * gameData.getDisplayHeight();
        Entity asteroids = new Asteroid();
        asteroids.setRadius(30);
        asteroids.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        asteroids.add(new PositionPart(x, y, radians));
        asteroids.add(new LifePart(1));
        return asteroids;
    }
}