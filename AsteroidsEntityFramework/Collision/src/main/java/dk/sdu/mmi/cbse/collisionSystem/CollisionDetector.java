package dk.sdu.mmi.cbse.collisionSystem;

import dk.sdu.mmi.cbse.asteroid.Asteroid;
import dk.sdu.mmi.cbse.enemysystem.Enemy;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.playersystem.Player;
import main.java.dk.sdu.mmi.cbse.bullet.Bullet;

public class CollisionDetector implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData,World world) {
        // two for loops for all entities in the world
        for (Entity entity : world.getEntities()) {
            for (Entity collisionDetection : world.getEntities()) {
                // get life parts on all entities
                LifePart entityLife = entity.getPart(LifePart.class);

                try {
                    // if the two entities are identical, skip the iteration
                    if (entity.getID().equals(collisionDetection.getID())) {
                        continue;
                        // remove entities with zero in expiration
                    }
                    // CollisionDetection
                    if (this.collides(entity, collisionDetection)) {
                        // if entity has been hit, and should have its life reduced
                        if (entityLife.getLife() > 0) {
                            if(entity.getClass().equals(Player.class) || entity.getClass().equals(Enemy.class)
                                && collisionDetection.getClass().equals(Asteroid.class)){
                                entityLife.setLife(entityLife.getLife() - 1);
                                entityLife.setIsHit(true);
                            }
                            if(entity.getClass().equals(Player.class) && collisionDetection.getClass().equals(Enemy.class)){
                                entityLife.setLife(entityLife.getLife() - 1);
                                entityLife.setIsHit(true);
                            }
                            if(entity.getClass().equals(Bullet.class) && collisionDetection.getClass().equals(Asteroid.class)){
//                                entityLife.setLife(entityLife.getLife()-1);
                                entityLife.setIsHit(true);

                            }
                            if(entity.getClass().equals(Asteroid.class) && collisionDetection.getClass().equals(Bullet.class)){
//                                entityLife.setLife(entityLife.getLife()-1);
                                entityLife.setIsHit(true);

                            }

                            if(entity.getClass().equals(Player.class) && collisionDetection.getClass().equals(Bullet.class)){
                                entityLife.setLife(1);
                                entityLife.setIsHit(false);

                            }
                            // if entity is out of life - remove
                            if (entityLife.getLife() <= 0) {
                                world.removeEntity(entity);
                            }
                        }
                    }
                } catch (NullPointerException ex){
                    ex.getMessage();
                }

            }
        }
    }
    public Boolean collides(Entity entity, Entity entity2) {
        PositionPart entMov = entity.getPart(PositionPart.class);
        PositionPart entMov2 = entity2.getPart(PositionPart.class);
        float dx =  entMov.getX() - entMov2.getX();
        float dy =  entMov.getY() - entMov2.getY();
        float distance = (float) Math.sqrt(Math.pow(dx,2) + Math.pow(dy, 2));
        return distance < (entity.getRadius() + entity2.getRadius());
    }
//    public void process(GameData gameData, World world) {
//        System.out.println("running");
//        PositionPart playerPositionPart = null;
//        if(world.getEntities(Player.class).stream().findAny().isPresent()){
//            System.out.println(Arrays.toString(world.getEntities(Player.class).toArray()));
//            playerPositionPart = world.getEntities(Player.class).stream().findAny().get().getPart(PositionPart.class);
//        }
//        else{
//            System.out.println("doe not exist");
//        }
//        for(Entity entity: world.getEntities()){
//            System.out.println(world.getEntities());
//            try {
//                float playerPositionX = playerPositionPart.getX();
//                float playerPositionY = playerPositionPart.getY();
//                PositionPart entitiesPositionPart = entity.getPart(PositionPart.class);
//                System.out.println(entitiesPositionPart);
//                float entitiesPositionX = entitiesPositionPart.getX();
//                float entitiesPositionY = entitiesPositionPart.getY();
//
//                float dx = entitiesPositionX - playerPositionX;
//                float dy = entitiesPositionY - playerPositionY;
//
//                float distance = (float) Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
//
//                if(distance < entitiesPositionPart.getRadians() + playerPositionPart.getRadians()){
//                    System.out.println("Collision detected");
//                }
//                else{
//                    System.out.println("not detected");
//                }
//            } catch (NullPointerException ex){
//                ex.getMessage();
//            }
//
//        }
//
//    }
}
