package me.sub.angels.common.entities;

import com.google.common.collect.Lists;
import me.sub.angels.common.misc.WAConstants;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class EntityAngelPainting extends EntityHanging {
    public EntityAngelPainting.EnumAngelArt art;

    public EntityAngelPainting(World world) {
        super(world);
    }

    public EntityAngelPainting(World world, BlockPos pos, EnumFacing facing) {
        super(world, pos);
        List<EntityAngelPainting.EnumAngelArt> list = Lists.newArrayList();
        int i = 0;

        for (EntityAngelPainting.EnumAngelArt entitypainting$EnumAngelArt : EntityAngelPainting.EnumAngelArt.values()) {
            this.art = entitypainting$EnumAngelArt;
            this.updateFacingWithBoundingBox(facing);

            if (this.onValidSurface()) {
                list.add(entitypainting$EnumAngelArt);
                int j = entitypainting$EnumAngelArt.sizeX * entitypainting$EnumAngelArt.sizeY;

                if (j > i) {
                    i = j;
                }
            }
        }

        if (!list.isEmpty()) {
            Iterator<EntityAngelPainting.EnumAngelArt> iterator = list.iterator();

            while (iterator.hasNext()) {
                EntityAngelPainting.EnumAngelArt entitypainting$EnumAngelArt1 = iterator.next();

                if (entitypainting$EnumAngelArt1.sizeX * entitypainting$EnumAngelArt1.sizeY < i) {
                    iterator.remove();
                }
            }

            this.art = list.get(this.rand.nextInt(list.size()));
        }

        this.updateFacingWithBoundingBox(facing);
    }

    @SideOnly(Side.CLIENT)
    public EntityAngelPainting(World world, BlockPos pos, EnumFacing facing, String title) {
        this(world, pos, facing);

        for (EntityAngelPainting.EnumAngelArt entitypainting$EnumAngelArt : EntityAngelPainting.EnumAngelArt.values()) {
            if (entitypainting$EnumAngelArt.title.equals(title)) {
                this.art = entitypainting$EnumAngelArt;
                break;
            }
        }

        this.updateFacingWithBoundingBox(facing);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setString(WAConstants.MOTIVE, this.art.title);
        super.writeEntityToNBT(compound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        String s = compound.getString(WAConstants.MOTIVE);

        for (EntityAngelPainting.EnumAngelArt entitypainting$EnumAngelArt : EntityAngelPainting.EnumAngelArt.values()) {
            if (entitypainting$EnumAngelArt.title.equals(s)) {
                this.art = entitypainting$EnumAngelArt;
            }
        }

        if (this.art == null) {
            this.art = EnumAngelArt.AngelFive;
        }

        super.readEntityFromNBT(compound);
    }

    public int getWidthPixels() {
        return this.art.sizeX;
    }

    public int getHeightPixels() {
        return this.art.sizeY;
    }

    /**
     * Called when this entity is broken. Entity parameter may be null.
     */
    @Override
    public void onBroken(@Nullable Entity brokenEntity) {
        if (this.world.getGameRules().getBoolean("doEntityDrops")) {
            this.playSound(SoundEvents.ENTITY_PAINTING_BREAK, 1.0F, 1.0F);

            if (brokenEntity instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer) brokenEntity;

                if (entityplayer.capabilities.isCreativeMode) {
                    return;
                }
            }

            this.entityDropItem(new ItemStack(Items.PAINTING), 0.0F);
        }
    }

    @Override
    public void playPlaceSound() {
        this.playSound(SoundEvents.ENTITY_PAINTING_PLACE, 1.0F, 1.0F);
    }

    /**
     * Sets the location and Yaw/Pitch of an entity in the world
     */
    @Override
    public void setLocationAndAngles(double x, double y, double z, float yaw, float pitch) {
        this.setPosition(x, y, z);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

    }

    /**
     * Set the position and rotation values directly without any clamping.
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        BlockPos blockpos = this.hangingPosition.add(x - this.posX, y - this.posY, z - this.posZ);
        this.setPosition((double) blockpos.getX(), (double) blockpos.getY(), (double) blockpos.getZ());
    }

    public enum EnumAngelArt {
        AngelOne("Angel_One", 16, 32, 0, 0),
        AngelTwo("Angel_Two", 16, 32, 0, 64),
        BlankPaintingOne("Paint_BlankOne", 16, 32, 16, 64),
        AngelThree("Angel_Three", 16, 32, 32, 64),
        BlankPaintingTwo("Paint_BlankTwo", 16, 32, 48, 64),
        AngelFour("Angel_Four", 16, 32, 64, 64),
        BlankPaintingThree("Paint_BlankThree", 16, 32, 80, 64),
        AngelFive("Angel_Five", 16, 32, 96, 64),
        BlankPaintingFour("Paint_BlankFour", 16, 32, 112, 64),
        AngelSix("Angel_Six", 16, 32, 128, 64),
        BlankPaintingFive("Angel_Five", 16, 32, 144, 64),
        AngelSeven("Angel_Seven", 16, 32, 160, 64),
        BlankPaintingSix("Paint_BlankSix", 16, 32, 176, 64);

        public final String title;
        public final int sizeX;
        public final int sizeY;
        public final int offsetX;
        public final int offsetY;

        EnumAngelArt(String name, int sizeX, int sizeY, int offsetX, int offsetY) {
            this.title = name;
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            this.offsetX = offsetX;
            this.offsetY = offsetY;
        }
    }
}