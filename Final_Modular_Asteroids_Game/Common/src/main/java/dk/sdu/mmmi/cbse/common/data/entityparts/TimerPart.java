
package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class TimerPart
        implements EntityPart {

//    private float expiration;
    private float elapsedTime;
    private float renderTime;

    public TimerPart(float elapsedTime, float renderTime) {
        this.elapsedTime = elapsedTime ;
        this.renderTime = renderTime;
    }

    /**
     * Get the value of elapsedTime in seconds (float). which is the start time
     * @return elapsedTime
     */
    public float getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Get the value of renderTime in seconds (float). which is the end time
     * @return renderTime
     */
    public float getRenderTime() {
        return renderTime;
    }

    /**
     * Set the value of elapsedTime in seconds (float). which is the start time
     */
    public void setElapsedTime(float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
    /**
     * Set the value of renderTime in seconds (float). which is the end time
     */

    public void setRenderTime(float renderTime) {
        this.renderTime = renderTime;
    }


    @Override
    public void process(GameData gameData, Entity entity) {
        /*
        *   This is a timer that will set the life of an entity to 0 after a certain amount of time
         */
        elapsedTime += gameData.getDelta();
        if(elapsedTime > renderTime){
            LifePart lifePart = entity.getPart(LifePart.class);
            lifePart.setLife(0);
        }
    }
}
