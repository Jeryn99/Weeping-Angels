package me.sub.angels.common.entities;

import com.google.common.collect.Lists;
import io.netty.buffer.ByteBuf;
import me.sub.angels.common.WAObjects;
import me.sub.angels.common.misc.WAConstants;
import me.sub.angels.utils.WATeleporter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

public class EntityAngelPainting extends EntityHanging implements IEntityAdditionalSpawnData {

	public EntityAngelPainting.EnumAngelArt art;

	public EntityAngelPainting(World worldIn) {
		super(worldIn);
	}

	public EntityAngelPainting(World worldIn, BlockPos pos, EnumFacing side) {
		super(worldIn, pos);
		ArrayList arraylist = Lists.newArrayList();
		EntityAngelPainting.EnumAngelArt[] ENUM_ART = EntityAngelPainting.EnumAngelArt.values();

		for (EnumAngelArt EnumAngelArt : ENUM_ART) {
			this.art = EnumAngelArt;
			this.updateFacingWithBoundingBox(side);

			if (this.onValidSurface()) {
				arraylist.add(EnumAngelArt);
			}
		}

		if (!arraylist.isEmpty()) {
			this.art = (EntityAngelPainting.EnumAngelArt) arraylist.get(this.rand.nextInt(arraylist.size()));
		}

		this.updateFacingWithBoundingBox(side);
	}

	@SideOnly(Side.CLIENT)
	public EntityAngelPainting(World worldIn, BlockPos pos, EnumFacing side, String name) {
		this(worldIn, pos, side);
		EntityAngelPainting.EnumAngelArt[] ENUM_ART = EntityAngelPainting.EnumAngelArt.values();

		for (EnumAngelArt EnumAngelArt : ENUM_ART) {
			if (EnumAngelArt.title.equals(name)) {
				this.art = EnumAngelArt;
				break;
			}
		}

		this.updateFacingWithBoundingBox(side);
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound) {
		tagCompound.setString(WAConstants.MOTIVE, this.art.title);
		super.writeEntityToNBT(tagCompound);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (ticksExisted % 200 == 0) {

			if (art.name().contains("Blank")) {
				return;
			}

			if (art.equals(EnumAngelArt.AngelOne)) {
				spawnAngel(getEntityWorld());
				art = EnumAngelArt.BlankPaintingOne;
			}

			if (art.equals(EnumAngelArt.AngelTwo)) {
				spawnAngel(getEntityWorld());
				art = EnumAngelArt.BlankPaintingTwo;
			}

			if (art.equals(EnumAngelArt.AngelThree)) {
				spawnAngel(getEntityWorld());
				art = EnumAngelArt.BlankPaintingThree;
			}

			if (art.equals(EnumAngelArt.AngelFour)) {
				spawnAngel(getEntityWorld());
				art = EnumAngelArt.BlankPaintingFour;
			}

			if (art.equals(EnumAngelArt.AngelFive)) {
				spawnAngel(getEntityWorld());
				art = EnumAngelArt.BlankPaintingFive;
			}

			if (art.equals(EnumAngelArt.AngelSix)) {
				spawnAngel(getEntityWorld());
				art = EnumAngelArt.BlankPaintingSix;
			}
		}
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompund) {
		String s = tagCompund.getString(WAConstants.MOTIVE);
		EntityAngelPainting.EnumAngelArt[] ENUM_ART = EntityAngelPainting.EnumAngelArt.values();

		for (EnumAngelArt EnumAngelArt : ENUM_ART) {
			if (EnumAngelArt.title.equals(s)) {
				this.art = EnumAngelArt;
			}
		}

		if (this.art == null) {
			this.art = EnumAngelArt.AngelFive;
		}

		super.readEntityFromNBT(tagCompund);
	}

	public int getWidthPixels() {
		if (art == null) {
			art = EnumAngelArt.AngelFive;
		}
		return this.art.sizeX;
	}

	public int getHeightPixels() {
		return this.art.sizeY;
	}

	/**
	 * Called when this entity is broken. Entity parameter may be null.
	 */
	@Override
	public void onBroken(Entity entity) {
		if (this.world.getGameRules().getBoolean("doTileDrops")) {
			if (entity instanceof EntityPlayer) {
				EntityPlayer entityplayer = (EntityPlayer) entity;

				if (entityplayer.capabilities.isCreativeMode) {
					return;
				}
			}

			this.entityDropItem(new ItemStack(WAObjects.Items.ANGEL_PAINTING), 0.0F);
		}
	}

	@Override
	public void playPlaceSound() {
		playSound(SoundEvents.ENTITY_PAINTING_PLACE, 1.0F, 1.0F);
	}

	/**
	 * Sets the location and Yaw/Pitch of an entity in the world
	 */
	@Override
	public void setLocationAndAngles(double x, double y, double z, float yaw, float pitch) {
		BlockPos blockpos = new BlockPos(x - this.posX, y - this.posY, z - this.posZ);
		BlockPos blockpos1 = this.hangingPosition.add(blockpos);
		this.setPosition((double) blockpos1.getX(), (double) blockpos1.getY() - 1, (double) blockpos1.getZ());
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeInt(this.art.ordinal());
		buffer.writeInt(this.chunkCoordX); // x
		buffer.writeInt(this.chunkCoordY); // y
		buffer.writeInt(this.chunkCoordZ); // z
		buffer.writeByte(this.getHorizontalFacing().getIndex());
	}

	@Override
	public void readSpawnData(ByteBuf buffer) {
		EnumAngelArt[] ENUM_ART = EnumAngelArt.values();
		this.art = ENUM_ART[buffer.readInt()];
		this.chunkCoordX = buffer.readInt();
		this.chunkCoordY = buffer.readInt();
		this.chunkCoordZ = buffer.readInt();
		this.updateFacingWithBoundingBox(EnumFacing.getFront((buffer.readByte())));
	}
	
	private void spawnAngel(World world) {
		if (!world.isRemote) {
			EntityWeepingAngel angel = new EntityWeepingAngel(world);
			angel.copyLocationAndAnglesFrom(this);
			WATeleporter.move(angel, dimension, new BlockPos(this.posX + 1, this.posY + 1, this.posZ + 1));
			world.spawnEntity(angel);
		}
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
