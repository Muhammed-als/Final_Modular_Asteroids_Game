package dk.sdu.mmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.asteroids.TestSplitAsteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

@SuppressWarnings("unchecked")
public class AsteroidProcessor implements IEntityProcessingService {
//    private IAsteroidSplitter asteroidSplitter = new AsteroidSplitterImpl();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Asteroid.class)) {
            LifePart lifePart = entity.getPart(LifePart.class);
            PositionPart positionPart = entity.getPart(PositionPart.class);
            MovingPart movingPart = entity.getPart(MovingPart.class);
            Random random = new Random();
            float x = positionPart.getX();
            float y = positionPart.getY();
            if(lifePart.getLife() <= 0){
                world.removeEntity(entity);
            }
            if (lifePart.isHit()) {
                System.out.println("Asteroid hit");
                Entity astroid1 = new Asteroid();
                Entity asteroid2 = new Asteroid();
                float speed = movingPart.getMaxSpeed();
                float radians = positionPart.getRadians();
                float speed1 = speed * 1.2f;
                float speed2 = speed * 0.5f;
                astroid1.setRadius(entity.getRadius() / 2);
                asteroid2.setRadius(entity.getRadius() / 2);
                astroid1.add(new MovingPart(movingPart.getDeceleration(), movingPart.getAcceleration(), speed1, 0));
                asteroid2.add(new MovingPart(movingPart.getDeceleration(), movingPart.getAcceleration(), speed2, 0));
                astroid1.add(new PositionPart(x, y, radians));
                asteroid2.add(new PositionPart(x, y, radians));
                astroid1.add(new LifePart(lifePart.getLife()));
                asteroid2.add(new LifePart(lifePart.getLife()));
                positionPart.setPosition(x, y);
                movingPart.process(gameData, astroid1);
                positionPart.process(gameData, astroid1);
                movingPart.process(gameData, asteroid2);
                positionPart.process(gameData, asteroid2);
                world.addEntity(astroid1);
                world.addEntity(asteroid2);
                world.removeEntity(entity);
                updateShape(astroid1);
                updateShape(asteroid2);
            }
                float radians = positionPart.getRadians();
                x += Math.cos(radians) * movingPart.getMaxSpeed() * gameData.getDelta();
                y += Math.sin(radians) * movingPart.getMaxSpeed() * gameData.getDelta();
                positionPart.setPosition(x, y);
                movingPart.process(gameData, entity);
                positionPart.process(gameData, entity);
                updateShape(entity);
            }
        }


    private void updateShape(Entity entity) {
        System.out.println(entity.getRadius());
        float[] shapex = new float[6];
        float[] shapey = new float[6];
        PositionPart positionPart = entity.getPart(PositionPart.class);
        LifePart lifePart = entity.getPart(LifePart.class);

        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        for (int i = 0; i < shapex.length; i++) {
            shapex[i] = (float) (x + Math.cos(radians - i * Math.PI / 3) * entity.getRadius());
            shapey[i] = (float) (y + Math.sin(radians - i * Math.PI / 3) * entity.getRadius());
        }
        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
