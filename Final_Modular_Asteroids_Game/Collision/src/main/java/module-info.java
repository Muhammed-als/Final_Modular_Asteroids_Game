module Collision {
    requires Common;
    opens dk.sdu.mmmi.cbse.collisionsystem;

    uses dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

    provides dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService with dk.sdu.mmmi.cbse.collisionsystem.CollisionDetector;

}