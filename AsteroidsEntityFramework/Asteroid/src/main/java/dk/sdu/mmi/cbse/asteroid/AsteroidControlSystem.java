package dk.sdu.mmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.playersystem.Player;

import java.util.Random;

@SuppressWarnings("unchecked")
public class AsteroidControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
            for(Entity entity: world.getEntities(Asteroid.class)) {
                LifePart lifePart = entity.getPart(LifePart.class);
                PositionPart positionPart = entity.getPart(PositionPart.class);
                MovingPart movingPart = entity.getPart(MovingPart.class);
                Random random = new Random();
                float x = positionPart.getX();
                float y =positionPart.getY();
                if(lifePart.getDamageCount() >=3){
                    world.removeEntity(entity);
                }
                if(lifePart.isIsHit()){
                    Entity astroid1 = new Asteroid();
                    Entity asteroid2 = new Asteroid();
                    float speed = movingPart.getMaxSpeed();
                    float radians = positionPart.getRadians();
                    float speed1 = speed * 1.2f;
                    float speed2 = speed * 0.5f;
                    astroid1.setRadius(entity.getRadius()/2);
                    asteroid2.setRadius(entity.getRadius()/2);
                    astroid1.add(new MovingPart(movingPart.getDeceleration(),movingPart.getAcceleration(), speed1,0));
                    asteroid2.add(new MovingPart(movingPart.getDeceleration(),movingPart.getAcceleration(),speed2,0));
                    astroid1.add(new PositionPart(x, y,radians));
                    asteroid2.add(new PositionPart(x, y,radians));
                    astroid1.add(new LifePart(1));
                    asteroid2.add(new LifePart(1));
                    positionPart.setPosition(x,y);
                    movingPart.process(gameData, astroid1);
                    positionPart.process(gameData, astroid1);
                    movingPart.process(gameData, asteroid2);
                    positionPart.process(gameData, asteroid2);
                    world.addEntity(astroid1);
                    world.addEntity(asteroid2);
                    world.removeEntity(entity);
                    LifePart astroid1LifePart = astroid1.getPart(LifePart.class);
                    LifePart astroid2LifePart = asteroid2.getPart(LifePart.class);
                    astroid1LifePart.setDamageCount(lifePart.getDamageCount()+1);
                    astroid2LifePart.setDamageCount(lifePart.getDamageCount()+1);
                    astroid1LifePart.setEntitySize(lifePart.getEntitySize() - 5);
                    astroid2LifePart.setEntitySize(lifePart.getEntitySize() - 5);
                    updateShape(astroid1);
                    updateShape(asteroid2);
                }
                else{
                    if(lifePart.getDamageCount()<1){
                        lifePart.setEntitySize(20);
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

    }


    private void updateShape(Entity entity) {

        float[] shapex = new float[6];
        float[] shapey = new float[6];
        PositionPart positionPart = entity.getPart(PositionPart.class);
        LifePart lifePart = entity.getPart(LifePart.class);

        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        for (int i = 0; i < shapex.length; i++) {
                shapex[i] = (float) (x + Math.cos(radians - i * Math.PI/3) * lifePart.getEntitySize());
                shapey[i] = (float) (y + Math.sin(radians - i * Math.PI/3) * lifePart.getEntitySize());
        }
        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
