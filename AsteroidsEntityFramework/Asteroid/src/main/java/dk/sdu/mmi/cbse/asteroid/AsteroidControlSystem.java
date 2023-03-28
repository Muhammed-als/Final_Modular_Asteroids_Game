package dk.sdu.mmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.List;
import java.util.Optional;

import static java.lang.Math.cos;

@SuppressWarnings("unchecked")
public class AsteroidControlSystem implements IEntityProcessingService {
    private int astroidSize = 0;
    private boolean isHit = false;
    @Override
    public void process(GameData gameData, World world) {
        isHit = false;
        for(Entity asteroid: world.getEntities(Asteroid.class)){
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            LifePart lifePart = asteroid.getPart(LifePart.class);
            float x = positionPart.getX();
            float y = positionPart.getY();
            x++;
            y++;
            positionPart.setPosition(x,y);
            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);
            updateShape(asteroid);

            if (lifePart.isIsHit()){
                System.out.println("call");
                isHit = true;
                astroidSize -=5;

                lifePart.setIsHit(false);
                splitShap(asteroid);
            }



        }

    }


    private void updateShape(Entity entity) {

        float[] shapex = new float[6];
        float[] shapey = new float[6];
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        for (int i = 0; i < shapex.length; i++) {
            shapex[i] = (float) (x + Math.cos(radians - i * Math.PI/3) * 20);
            shapey[i] = (float) (y + Math.sin(radians - i * Math.PI/3) * 20);
        }
        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
    float[] shapex = new float[6];
    float[] shapey = new float[6];
    public void splitShap(Entity entity){
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        for (int i = 0; i < shapex.length; i++) {
            shapex[i] = (float) (x + Math.cos(radians - i * Math.PI/3) * astroidSize/2);
            shapey[i] = (float) (y + Math.sin(radians - i * Math.PI/3) * astroidSize/2);
        }
        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
//        if (isHit){
//            System.out.println("yes");
//            astroidSize-=5;
//
//        }





    }
}
