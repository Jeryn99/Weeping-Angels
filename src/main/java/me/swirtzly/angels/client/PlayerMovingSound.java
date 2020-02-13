package me.swirtzly.angels.client;

import net.minecraft.client.audio.TickableSound;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;

import java.util.function.Supplier;

/**
 * Created by Swirtzly
 * on 11/02/2020 @ 21:17
 */
public class PlayerMovingSound extends TickableSound {

    private final Object entity;
    private final Supplier<Boolean> stopCondition;
    private final float backupVolume;
    private boolean donePlaying = false;

    public PlayerMovingSound(Object object, SoundEvent soundIn, SoundCategory categoryIn, boolean repeat, Supplier<Boolean> stopCondition, float volumeSfx) {
        super(soundIn, categoryIn);
        this.entity = object;
        this.stopCondition = stopCondition;
        super.repeat = repeat;
        volume = volumeSfx;
        backupVolume = volumeSfx;
    }


    @Override
    public void tick() {

        if (entity instanceof Entity) {
            Entity entityObject = (Entity) entity;

            if (entityObject.removed) {
                setDonePlaying();
            }

            super.x = (float) entityObject.posX;
            super.y = (float) entityObject.posY;
            super.z = (float) entityObject.posZ;
        }

        if (entity instanceof TileEntity) {
            TileEntity tileObject = (TileEntity) entity;
            BlockPos pos = tileObject.getPos();
            super.x = (float) pos.getX();
            super.y = (float) pos.getY();
            super.z = (float) pos.getZ();
        }

        if (stopCondition.get()) {
            volume = 0;
        } else {
            volume = backupVolume;
        }
    }

    public void setDonePlaying() {
        this.repeat = false;
        this.donePlaying = true;
        this.repeatDelay = 0;
    }

}
