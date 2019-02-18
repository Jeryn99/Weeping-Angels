package me.suff.angels.common.entities;

import com.google.common.collect.Lists;
import me.suff.angels.common.WAObjects;
import me.suff.angels.common.misc.WAConstants;
import me.suff.angels.utils.Teleporter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import java.util.ArrayList;

public class EntityAngelPainting extends EntityHanging implements IEntityAdditionalSpawnData {
	
	public EntityAngelPainting.EnumAngelArt art;
	
	public EntityAngelPainting(World worldIn) {
		super(null, worldIn);
	}
	
	public EntityAngelPainting(World worldIn, BlockPos pos, EnumFacing side) {
		this(worldIn);
		ArrayList<EnumAngelArt> ART_LIST = Lists.newArrayList();
		EntityAngelPainting.EnumAngelArt[] ENUM_ART = EntityAngelPainting.EnumAngelArt.values();
		
		for (EnumAngelArt EnumAngelArt : ENUM_ART) {
			art = EnumAngelArt;
			updateFacingWithBoundingBox(side);
			
			if (onValidSurface()) {
				ART_LIST.add(EnumAngelArt);
			}
		}
		
		if (!ART_LIST.isEmpty()) {
			art = ART_LIST.get(rand.nextInt(ART_LIST.size()));
		}
		
		updateFacingWithBoundingBox(side);
	}
	
	@OnlyIn(Dist.CLIENT)
	public EntityAngelPainting(World worldIn, BlockPos pos, EnumFacing side, String name) {
		this(worldIn, pos, side);
		EntityAngelPainting.EnumAngelArt[] ENUM_ART = EntityAngelPainting.EnumAngelArt.values();
		
		for (EnumAngelArt EnumAngelArt : ENUM_ART) {
			if (EnumAngelArt.title.equals(name)) {
				art = EnumAngelArt;
				break;
			}
		}
		
		updateFacingWithBoundingBox(side);
	}
	
	@Override
	public NBTTagCompound serializeNBT() {
		return null;
	}
	
	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		nbt.setString(WAConstants.MOTIVE, art.title);
		super.deserializeNBT(nbt);
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if (ticksExisted % 200 == 0) {
			
			if (art.name().contains("Blank")) {
				return;
			}
			
			if (art.equals(EnumAngelArt.ANGEL_ONE)) {
				spawnAngel(getEntityWorld());
				art = EnumAngelArt.BlankPaintingOne;
			}
			
			if (art.equals(EnumAngelArt.ANGEL_TWO)) {
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
	public void read(NBTTagCompound tagCompund) {
		String s = tagCompund.getString(WAConstants.MOTIVE);
		EntityAngelPainting.EnumAngelArt[] ENUM_ART = EntityAngelPainting.EnumAngelArt.values();
		
		for (EnumAngelArt EnumAngelArt : ENUM_ART) {
			if (EnumAngelArt.title.equals(s)) {
				art = EnumAngelArt;
			}
		}
		
		if (art == null) {
			art = EnumAngelArt.AngelFive;
		}
		
		super.read(tagCompund);
	}
	
	public int getWidthPixels() {
		if (art == null) {
			art = EnumAngelArt.AngelFive;
		}
		return art.sizeX;
	}
	
	public int getHeightPixels() {
		return art.sizeY;
	}
	
	/**
	 * Called when this entity is broken. Entity parameter may be null.
	 */
	@Override
	public void onBroken(Entity entity) {
		if (world.getGameRules().getBoolean("doTileDrops")) {
			if (entity instanceof EntityPlayer) {
				EntityPlayer entityplayer = (EntityPlayer) entity;
				
				if (entityplayer.isCreative()) {
					return;
				}
			}
			
			entityDropItem(new ItemStack(WAObjects.Items.ANGEL_PAINTING), 0.0F);
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
		BlockPos blockpos = new BlockPos(x - posX, y - posY, z - posZ);
		BlockPos hangingPos = hangingPosition.add(blockpos);
		setPosition((double) hangingPos.getX(), (double) hangingPos.getY() - 1, (double) hangingPos.getZ());
	}
	
	@Override
	public void writeAdditional(NBTTagCompound buffer) {
		super.writeAdditional(buffer);
		buffer.setInt("art", art.ordinal());
		buffer.setInt("chunkCoordX", chunkCoordX); // x
		buffer.setInt("chunkCoordY", chunkCoordY); // y
		buffer.setInt("chunkCoordZ", chunkCoordZ); // z
		buffer.setInt("index", getHorizontalFacing().getIndex());
	}
	
	private void spawnAngel(World world) {
		if (!world.isRemote) {
			EntityWeepingAngel angel = new EntityWeepingAngel(world);
			angel.copyLocationAndAnglesFrom(this);
			Teleporter.move(angel, dimension, new BlockPos(posX + 1, posY + 1, posZ + 1));
			world.spawnEntity(angel);
		}
	}
	
	@Override
	public void writeSpawnData(PacketBuffer buffer) {
		buffer.writeInt(art.ordinal());
		buffer.writeInt(chunkCoordX); // x
		buffer.writeInt(chunkCoordY); // y
		buffer.writeInt(chunkCoordZ); // z
		buffer.writeByte(getHorizontalFacing().getIndex());
	}
	
	@Override
	public void readSpawnData(PacketBuffer buffer) {
		EnumAngelArt[] ENUM_ART = EnumAngelArt.values();
		art = ENUM_ART[buffer.readInt()];
		chunkCoordX = buffer.readInt();
		chunkCoordY = buffer.readInt();
		chunkCoordZ = buffer.readInt();
		updateFacingWithBoundingBox(EnumFacing.byHorizontalIndex(buffer.readByte()));
	}
	
	public enum EnumAngelArt {
		ANGEL_ONE("Angel_One", 0, 0), ANGEL_TWO("Angel_Two", 0, 64), BlankPaintingOne("Paint_BlankOne", 16, 64), AngelThree("Angel_Three", 32, 64), BlankPaintingTwo("Paint_BlankTwo", 48, 64), AngelFour("Angel_Four", 64, 64), BlankPaintingThree("Paint_BlankThree", 80, 64), AngelFive("Angel_Five", 96, 64), BlankPaintingFour("Paint_BlankFour", 112, 64), AngelSix("Angel_Six", 128, 64), BlankPaintingFive("Angel_Five", 144, 64), AngelSeven("Angel_Seven", 160, 64), BlankPaintingSix("Paint_BlankSix", 176, 64);
		
		public final String title;
		public final int sizeX;
		public final int sizeY;
		public final int offsetX;
		public final int offsetY;
		
		EnumAngelArt(String name, int offsetX, int offsetY) {
			title = name;
			sizeX = 16;
			sizeY = 32;
			this.offsetX = offsetX;
			this.offsetY = offsetY;
		}
	}
	
}
