package me.fril.angels.client;

import me.fril.angels.WeepingAngels;
import me.fril.angels.common.WAObjects;
import me.fril.angels.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.Sys;

import java.util.Random;

public class SeenSoundHandler {
	
	private boolean isPlaying = false;
	private static SoundEvent[] possibleSounds = new SoundEvent[] { WAObjects.Sounds.ANGEL_SEEN_1, WAObjects.Sounds.ANGEL_SEEN_2, WAObjects.Sounds.ANGEL_SEEN_3, WAObjects.Sounds.ANGEL_SEEN_4, WAObjects.Sounds.ANGEL_SEEN_5, WAObjects.Sounds.ANGEL_SEEN_6, WAObjects.Sounds.ANGEL_SEEN_7, WAObjects.Sounds.ANGEL_SEEN_8 };
	private static Random random = new Random();
	public static MovingSound sound = null;
	
	public void setPlaying(boolean playing) {
		isPlaying = playing;
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	@SideOnly(Side.CLIENT)
	public void playSound(){
		WeepingAngels.LOGGER.warn("trying to play");
		System.out.println(isPlaying);
		//if(ClientProxy.seenSoundHandler.isPlaying()) return;
		sound = new MovingSound(possibleSounds[random.nextInt(possibleSounds.length)], SoundCategory.HOSTILE) {
			/**
			 * Like the old updateEntity(), except more generic.
			 */
			@Override
			public void update() {
				System.out.println(getSoundLocation().toString());
				repeat = true;
			//	ClientProxy.seenSoundHandler.setPlaying(!isDonePlaying());
				WeepingAngels.LOGGER.warn("hippity");
			}
		};
		
		Minecraft.getMinecraft().getSoundHandler().playSound(sound);
	}
	
}
