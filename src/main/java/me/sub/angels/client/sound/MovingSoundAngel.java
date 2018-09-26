package me.sub.angels.client.sound;

import me.sub.angels.common.entities.EntityWeepingAngel;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;

/**
 * Created by Sub
 * on 21/09/2018.
 */
public class MovingSoundAngel extends MovingSound {

    private EntityWeepingAngel angel;
    private float distance = 0.0F;
    private SoundEvent soundCheck;

    public MovingSoundAngel(EntityWeepingAngel angel, SoundEvent soundIn, SoundCategory categoryIn) {
        super(soundIn, categoryIn);
        this.angel = angel;
        this.repeat = false;
        this.repeatDelay = 0;
        soundCheck = soundIn;
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    @Override
    public void update() {

        if (this.angel.isDead) {
            this.donePlaying = true;
        } else {
            this.xPosF = (float) this.angel.posX;
            this.yPosF = (float) this.angel.posY;
            this.zPosF = (float) this.angel.posZ;

            this.distance = MathHelper.clamp(this.distance + 0.0025F, 0.0F, 1.0F);
            this.volume = 1.0F;
        }
    }
}
