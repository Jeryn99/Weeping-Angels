package craig.software.mc.angels.client.sounds;

import craig.software.mc.angels.common.WAObjects;
import craig.software.mc.angels.utils.PlayerUtil;
import net.minecraft.client.audio.TickableSound;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;

public class DetectorTickableSound extends TickableSound {
    private final PlayerEntity playerEntity;
    private float pitch = 0.0F;

    public DetectorTickableSound(PlayerEntity playerEntity) {
        super(WAObjects.Sounds.PROJECTOR.get(), SoundCategory.PLAYERS);
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

        boolean isHolding = PlayerUtil.isInEitherHand(playerEntity, WAObjects.Items.TIMEY_WIMEY_DETECTOR.get());
        this.pitch = isHolding ? MathHelper.clamp(this.pitch + 0.0025F, 0.0F, 1.0F) : 0F;
        this.volume = isHolding ? MathHelper.lerp(MathHelper.clamp(0.3F, 0.0F, 0.5F), 0.0F, 0.7F) : 0F;

    }
}
