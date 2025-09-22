package dev.jeryn.angels.util;

import dev.jeryn.angels.common.WASounds;
import dev.jeryn.angels.common.items.WAItems;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;

public class DetectorTickableSound extends AbstractTickableSoundInstance {
    private final Player playerEntity;
    private float pitch = 0.0F;

    public DetectorTickableSound(Player playerEntity) {
        super(WASounds.PROJECTOR.get(), SoundSource.PLAYERS, RandomSource.create());
        this.playerEntity = playerEntity;
        this.looping = true;
        this.delay = 0;
        this.volume = 0.0F;
        this.x = (float) playerEntity.getX();
        this.y = (float) playerEntity.getY();
        this.z = (float) playerEntity.getZ();
    }

    @Override
    public boolean canPlaySound() {
        return !this.playerEntity.isSilent();
    }

    @Override
    public boolean canStartSilent() {
        return true;
    }

    @Override
    public void tick() {
        if (!this.playerEntity.isAlive()) {
            this.stop();
            return;
        }

        this.x = (float) this.playerEntity.getX();
        this.y = (float) this.playerEntity.getY();
        this.z = (float) this.playerEntity.getZ();

        boolean isHolding = HandUtil.isInEitherHand(playerEntity, WAItems.TIMEY_WIMEY_DETECTOR.get());
        this.pitch = isHolding ? Mth.clamp(this.pitch + 0.0025F, 0.0F, 1.0F) : 0F;
        this.volume = isHolding ? Mth.lerp(Mth.clamp(0.3F, 0.0F, 0.5F), 0.0F, 0.7F) : 0F;

    }
}
