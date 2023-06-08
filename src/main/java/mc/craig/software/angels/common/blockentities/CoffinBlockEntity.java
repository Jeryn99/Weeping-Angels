package mc.craig.software.angels.common.blockentities;

import mc.craig.software.angels.common.WAObjects;
import mc.craig.software.angels.common.blocks.StatueBlock;
import mc.craig.software.angels.common.entities.WeepingAngel;
import mc.craig.software.angels.utils.AngelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Iterator;

import static mc.craig.software.angels.utils.AngelUtil.RAND;

public class CoffinBlockEntity extends BlockEntity implements BlockEntityTicker<CoffinBlockEntity> {

    private Coffin coffin = Coffin.NEW;
    private boolean isOpen, hasSkeleton = false;
    private float openAmount = 0.0F, alpha = 1;
    private boolean doingSomething = false;
    private int ticks, pulses, knockTime;

    public CoffinBlockEntity(BlockPos pos, BlockState state) {
        super(WAObjects.Tiles.COFFIN.get(), pos, state);
    }

    public Coffin getCoffin() {
        return coffin;
    }

    public void setCoffin(Coffin coffin) {
        this.coffin = coffin;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public float getOpenAmount() {
        return openAmount;
    }

    public void setHasSkeleton(boolean hasSkeleton) {
        this.hasSkeleton = hasSkeleton;
    }

    public boolean hasSkeleton() {
        return hasSkeleton;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(pkt.getTag());
    }

    @Override
    public void onLoad() {
        if (coffin == null) {
            coffin = AngelUtil.randomCoffin();
            setChanged();
        }
    }

    @Override
    public void tick(Level p_155253_, BlockPos p_155254_, BlockState p_155255_, CoffinBlockEntity p_155256_) {

        if (isOpen) {
            this.openAmount += 0.1F;
        } else {
            this.openAmount -= 0.1F;
        }

        if (this.openAmount > 1.0F) {
            this.openAmount = 1.0F;
        }

        if (this.openAmount < 0.0F) {
            this.openAmount = 0.0F;
        }

        if (knockTime <= 0) {
            knockTime = RAND.nextInt(1800 + 10);
        }

        if (knockTime > 0 && level.getGameTime() > 0) {
            if (level.getGameTime() % knockTime == 0 && !coffin.isPoliceBox() && !isOpen() && hasSkeleton()) {
                level.playSound(null, getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), WAObjects.Sounds.KNOCK.get(), SoundSource.BLOCKS, 1.0F * 16, 1.0F);
                knockTime = RAND.nextInt(1800 + 10);
            }
        }

        if (doingSomething && coffin.isPoliceBox()) {
            if (ticks % 60 < 30) {
                if (pulses <= 2)
                    this.alpha -= 0.01;
                else this.alpha -= 0.02;
            } else {
                this.alpha += 0.01;
            }

            if (ticks % 60 == 0)
                ++this.pulses;

            ++ticks;
            if (ticks >= 360) {
                if (!level.isClientSide) {
                    level.removeBlock(worldPosition, false);

                    activateAngels(40);

                    if (ModList.get().isLoaded("tardis")) {
                        level.setBlockAndUpdate(worldPosition.above(), ForgeRegistries.BLOCKS.getValue(new ResourceLocation("tardis", "broken_exterior")).defaultBlockState());
                    }

                    int i = 25;
                    while (i > 0) {
                        int j = ExperienceOrb.getExperienceValue(i);
                        i -= j;
                        this.level.addFreshEntity(new ExperienceOrb(this.level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), j));
                    }
                }
            }
        }

    }

    private void activateAngels(int range) {
        for (Iterator<BlockPos> iterator = BlockPos.withinManhattanStream(worldPosition, range, range, range).iterator(); iterator.hasNext(); ) {
            BlockPos pos = iterator.next();
            ServerLevel serverWorld = (ServerLevel) level;
            if (level.getDifficulty() != Difficulty.PEACEFUL && serverWorld.getBlockEntity(pos) instanceof StatueBlockEntity statueBlockEntity) {
                WeepingAngel angel = new WeepingAngel(level);
                angel.setVarient(statueBlockEntity.getAngelVarients());
                angel.setType(statueBlockEntity.getAngelType());
                angel.moveTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, (float) Math.toRadians(22.5F * statueBlockEntity.getBlockState().getValue(StatueBlock.ROTATION)), 0);
                angel.setPose(statueBlockEntity.getPose());
                level.addFreshEntity(angel);
                level.removeBlock(pos, false);
            }
        }
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }


    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putString("coffin_type", coffin.name());
        compoundTag.putBoolean("isOpen", isOpen);
        compoundTag.putBoolean("hasSkeleton", hasSkeleton);
        compoundTag.putBoolean("doingSomething", doingSomething);
        compoundTag.putFloat("openAmount", openAmount);
        compoundTag.putFloat("alpha", alpha);
        return compoundTag;
    }

    public void sendUpdates() {
        if (level != null && getBlockState() != null && getBlockState().getBlock() != null) {
            level.updateNeighbourForOutputSignal(worldPosition, getBlockState().getBlock());
            level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 3);
        }
        setChanged();
    }

    @Override
    public void load(CompoundTag nbt) {
        coffin = getCorrectCoffin(nbt.getString("coffin_type"));
        isOpen = nbt.getBoolean("isOpen");
        hasSkeleton = nbt.getBoolean("hasSkeleton");
        openAmount = nbt.getFloat("openAmount");
        alpha = nbt.getFloat("alpha");
        doingSomething = nbt.getBoolean("doingSomething");
        super.load(nbt);
    }

    @Override
    protected void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        if (coffin == null) {
            coffin = AngelUtil.randomCoffin();
        }
        compound.putString("coffin_type", coffin.name());
        compound.putBoolean("isOpen", isOpen);
        compound.putBoolean("hasSkeleton", hasSkeleton);
        compound.putBoolean("doingSomething", doingSomething);
        compound.putFloat("openAmount", openAmount);
        compound.putFloat("alpha", alpha);
    }

    public boolean isDoingSomething() {
        return doingSomething;
    }

    public void setDoingSomething(boolean doingSomething) {
        this.doingSomething = doingSomething;
        if (doingSomething) {
            level.playSound(null, worldPosition, WAObjects.Sounds.TARDIS_TAKEOFF.get(), SoundSource.BLOCKS, 1, 1);
        }
    }

    public float getAlpha() {
        return alpha;
    }

    public Coffin getCorrectCoffin(String coffin) {
        for (Coffin value : Coffin.values()) {
            if (value.name().equals(coffin)) {
                return value;
            }
        }

        return Coffin.HEAVILY_WEATHERED;
    }

    @Override
    public AABB getRenderBoundingBox() {
        return super.getRenderBoundingBox().inflate(8);
    }

    public enum Coffin {
        NEW, WEATHERED, SLIGHTLY_WEATHERED, HEAVILY_WEATHERED, PTB(true), PTB_2(true), PTB_3(true), PTB_4(true), PTB_5(true);

        private final boolean isPoliceBox;

        Coffin() {
            this.isPoliceBox = false;
        }

        Coffin(boolean isPoliceBox) {
            this.isPoliceBox = isPoliceBox;
        }

        public static Coffin next(Coffin type) {
            int index = type.ordinal();
            int nextIndex = index + 1;
            Coffin[] angels = Coffin.values();
            nextIndex %= angels.length;
            return angels[nextIndex];
        }

        public boolean isPoliceBox() {
            return isPoliceBox;
        }
    }
}
