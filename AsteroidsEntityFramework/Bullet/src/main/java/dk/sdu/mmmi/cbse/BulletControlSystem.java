package dk.sdu.mmmi.cbse;
//
//import dk.sdu.mmi.cbse;
import dk.sdu.mmi.cbse.common.bullet.Bullet;
import dk.sdu.mmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
//import dk.sdu.mmmi.cbse.playersystem.Player;

import java.awt.*;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {

            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            TimerPart timerPart = bullet.getPart(TimerPart.class);
            LifePart lifePart = bullet.getPart(LifePart.class);
            movingPart.setUp(true);
            if (timerPart.getExpiration() < 0 || lifePart.isHit()) {
                world.removeEntity(bullet);
            }
            timerPart.process(gameData, bullet);
            movingPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);

            setShape(bullet);
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        PositionPart shooterPos = shooter.getPart(PositionPart.class);

        float x = shooterPos.getX();
        float y = shooterPos.getY();
        float radians = shooterPos.getRadians();
        float dt = gameData.getDelta();
        float speed = 350;

        Entity bullet = new Bullet();
        bullet.setRadius(5);

        float bx = (float) cos(radians) * shooter.getRadius() * bullet.getRadius();
        float by = (float) sin(radians) * shooter.getRadius() * bullet.getRadius();

        bullet.add(new PositionPart(bx + x, by + y, radians));
        bullet.add(new LifePart(1));
        bullet.add(new MovingPart(0, 5000000, speed, 5));
        bullet.add(new TimerPart(1));

        bullet.setShapeX(new float[2]);
        bullet.setShapeY(new float[2]);

        return bullet;
    }

    private void setShape(Entity bullet) {
        PositionPart positionPart = bullet.getPart(PositionPart.class);
        float[] shapeX = bullet.getShapeX() ;
        float[] shapeY = bullet.getShapeY();
        float radians = positionPart.getRadians();
        for (int i = 0; i < shapeX.length; i++) {
            shapeX[i] = (float) (positionPart.getX() + bullet.getRadius() * cos(radians + i * 4 * Math.PI/16));
            shapeY[i] = (float) (positionPart.getY() + bullet.getRadius() * sin(radians + i * 4 *  Math.PI/16));
            bullet.setShapeX(shapeX);
            bullet.setShapeY(shapeY);
        }
        bullet.setShapeX(shapeX);
        bullet.setShapeY(shapeY);
    }

}

//public class BulletControlSystem implements IEntityProcessingService, IBulletCreator {
//    @Override
//    public void process(GameData gameData, World world) {
//        System.out.println(true);
//
//        for (Entity bullet : world.getEntities(Bullet.class)) {
//
//            PositionPart positionPart = bullet.getPart(PositionPart.class);
//            MovingPart movingPart = bullet.getPart(MovingPart.class);
//            TimerPart timerPart = bullet.getPart(TimerPart.class);
//            movingPart.setUp(true);
//            if (timerPart.getExpiration() < 0) {
//                world.removeEntity(bullet);
//            }
//            timerPart.process(gameData, bullet);
//            movingPart.process(gameData, bullet);
//            positionPart.process(gameData, bullet);
//
//            setShape(bullet);
//            world.addEntity(bullet);
//        }
//    }
//
//    @Override
//    public Entity createBullet(Entity shooter, GameData gameData) {
//        PositionPart shooterPos = shooter.getPart(PositionPart.class);
//        MovingPart shooterMov = shooter.getPart(MovingPart.class);
//
//        float x = 50;
//        float y = 50;
//        float radians = shooterPos.getRadians();
//        float dt = gameData.getDelta();
//        float speed = 350 + shooterMov.getMaxSpeed();
//
//        Entity bullet = new Bullet();
//        bullet.setRadius(2);
//
//        float bx = (float) cos(radians) * shooter.getRadius() * bullet.getRadius();
//        float by = (float) sin(radians) * shooter.getRadius() * bullet.getRadius();
//
//        bullet.add(new PositionPart(bx + x, by + y, radians));
//        bullet.add(new LifePart(1));
//        bullet.add(new MovingPart(0, 5000000, speed, 5));
//        bullet.add(new TimerPart(1));
//
//        bullet.setShapeX(new float[2]);
//        bullet.setShapeY(new float[2]);
//
//
//        return bullet;
//    }
//
//    private void setShape(Entity entity) {
//        System.out.println(true);
//        float[] shapex = entity.getShapeX();
//        float[] shapey = entity.getShapeY();
//        PositionPart positionPart = entity.getPart(PositionPart.class);
//        float x = positionPart.getX();
//        float y = positionPart.getY();
//        float radians = positionPart.getRadians();
//
//        shapex[0] = x;
//        shapey[0] = y;
//
//        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5));
//        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5));
//
//        entity.setShapeX(shapex);
//        entity.setShapeY(shapey);
//    }
//
////    @Override
////    public Entity create(Entity shooter, GameData gameData) {
////        return null;
////    }
////    private boolean showBullet;
////    int bulletDistanceTraveled = 0;
////
////    @Override
////    public void process(GameData gameData, World world) {
////        System.out.println(true);
////        for (Entity bullet : world.getEntities(Bullet.class)) {
////
////            PositionPart positionPart = bullet.getPart(PositionPart.class);
////            MovingPart movingPart = bullet.getPart(MovingPart.class);
////            TimerPart timerPart = bullet.getPart(TimerPart.class);
////            movingPart.setUp(true);
////            if (timerPart.getExpiration() < 0) {
////                world.removeEntity(bullet);
////            }
////            timerPart.process(gameData, bullet);
////            movingPart.process(gameData, bullet);
////            positionPart.process(gameData, bullet);
////
////            updateShape(bullet);
////        }
//////        Optional<Entity> player = world.getEntities(Player.class).stream().findFirst();
//////        Optional<Entity> bullet = world.getEntities(Bullet.class).stream().findFirst();
//////        LifePart bulletLifePart = bullet.get().getPart(LifePart.class);
//////
//////        if(player.isPresent()) {
//////            PositionPart bulletPosition = bullet.get().getPart(PositionPart.class);
//////            MovingPart bulletmovingPart = bullet.get().getPart(MovingPart.class);
//////            PositionPart playerPosition = player.get().getPart(PositionPart.class);
//////            float x = playerPosition.getX();
//////            float y = playerPosition.getY();
//////            if(bulletLifePart.isIsHit()){
//////                showBullet = false;
//////                bulletLifePart.setIsHit(false);
//////                bulletDistanceTraveled = 0;
//////            }
//////            if((gameData.getKeys().isPressed(GameKeys.SPACE) && bulletDistanceTraveled == 0)){
//////                showBullet = true;
//////
//////
//////                bulletPosition.setRadians(playerPosition.getRadians());
//////
//////            }
//////
//////            if (showBullet) {
//////                x = bulletPosition.getX();
//////                y = bulletPosition.getY();
//////                x += (float) (3 * cos(bulletPosition.getRadians()));
//////                y += (float) (3 * sin(bulletPosition.getRadians()));
//////                bulletDistanceTraveled += 3;
//////            }
//////            if (bulletDistanceTraveled >= 200) {
//////                showBullet = false;
//////                bulletDistanceTraveled = 0;
//////                x = playerPosition.getX();
//////                y = playerPosition.getY();
//////            }
//////            bulletPosition.setPosition(x, y);
//////            bulletmovingPart.process(gameData, bullet.get());
//////            bulletPosition.process(gameData, bullet.get());
//////            updateShape(bullet.get());
//////        }
//////        else{
//////            showBullet = false;
//////            bullet.ifPresent(this::updateShape);
//////        }
////    }
////
////    private void updateShape(Entity entity) {
////        System.out.println(true);
////        float[] shapex = entity.getShapeX();
////        float[] shapey = entity.getShapeY();
////        PositionPart positionPart = entity.getPart(PositionPart.class);
////        float x = positionPart.getX();
////        float y = positionPart.getY();
////        float radians = positionPart.getRadians();
////
////        shapex[0] = x;
////        shapey[0] = y;
////
////        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5));
////        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5));
////
////        entity.setShapeX(shapex);
////        entity.setShapeY(shapey);
//////        float[] shapeX = entity.getShapeX();
//////        float[] shapeY = entity.getShapeY();
//////        PositionPart positionPart = entity.getPart(PositionPart.class);
//////        float radians = positionPart.getRadians();
//////        for (int i = 0; i < shapeX.length; i++) {
//////            shapeX[i] = (float) (positionPart.getX() + entity.getRadius() * cos(radians + i * 4 * Math.PI / 16));
//////            shapeY[i] = (float) (positionPart.getY() + entity.getRadius() * sin(radians + i * 4 * Math.PI / 16));
//////        }
//////        entity.setShapeX(shapeX);
//////        entity.setShapeY(shapeY);
//////        if(!showBullet){
//////            entity.setShapeX(new float[0]);
//////            entity.setShapeY(new float[0]);
//////        }
////
////    }
////
////    @Override
////    public Entity createBullet(Entity shooter, GameData gameData) {
////        System.out.println(true);
////        PositionPart shooterPos = shooter.getPart(PositionPart.class);
////
////        float x = shooterPos.getX();
////        float y = shooterPos.getY();
////        float radians = shooterPos.getRadians();
////        float dt = gameData.getDelta();
////        float speed = 350;
////
////        Entity bullet = new Bullet();
////        bullet.setRadius(2);
////
////        float bx = (float) cos(radians) * shooter.getRadius() * bullet.getRadius();
////        float by = (float) sin(radians) * shooter.getRadius() * bullet.getRadius();
////
////        bullet.add(new PositionPart(bx + x, by + y, radians));
////        bullet.add(new LifePart(1));
////        bullet.add(new MovingPart(0, 5000000, speed, 5));
////        bullet.add(new TimerPart(1));
////
////        bullet.setShapeX(new float[2]);
////        bullet.setShapeY(new float[2]);
////
////        return bullet;
////    }
//}
