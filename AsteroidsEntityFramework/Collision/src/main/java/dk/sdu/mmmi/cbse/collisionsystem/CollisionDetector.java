package dk.sdu.mmmi.cbse.collisionsystem;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        // two for loops for all entities in the world
        for (Entity entity : world.getEntities()) {
            for (Entity collisionDetection : world.getEntities()) {
                // get life parts on all entities
                LifePart entityLife = entity.getPart(LifePart.class);
                LifePart collisionDetectionLife = collisionDetection.getPart(LifePart.class);
                // if the two entities are identical, skip the iteration
                if (entity.getType().equals(collisionDetection.getType())) {
                    continue;

                    // remove entities with zero in expiration
                }

                // CollisionDetection
                if (this.collides(entity, collisionDetection) ) {
                    // if entity has been hit, and should have its life reduced
                    if (!entityLife.isHit() && entityLife.getLife() > 0) {
                        entityLife.setLife(entityLife.getLife() - 1);
                        entityLife.setIsHit(true);

                    }
                    // if entity is out of life - remove
//                    if (entityLife.getLife()<=0) {
//                        world.removeEntity(entity);
//                    }
                    if (entityLife.isDead()) {
                        world.removeEntity(entity);
                    }

                }
            }
        }
    }

    public Boolean collides(Entity entity, Entity entity2) {
        PositionPart entMov = entity.getPart(PositionPart.class);
        PositionPart entMov2 = entity2.getPart(PositionPart.class);
        float dx =  entMov.getX() - entMov2.getX();
        float dy =  entMov.getY() - entMov2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity.getRadius() + entity2.getRadius());
    }

}
