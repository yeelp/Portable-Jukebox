package yeelp.portablejukebox.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import yeelp.portablejukebox.PortableJukebox;

/**
 * Portable Music
 * @author Yeelp
 *
 */
public class PortableMusic extends MovingSound
{
	private EntityPlayer player;
	private boolean hasPlayed = false;
	/**
	 * Make a Portable Music
	 * @param player the player this music should follow
	 * @param soundIn the sound to play
	 */
	public PortableMusic(EntityPlayer player, SoundEvent soundIn) 
	{
		super(soundIn, SoundCategory.RECORDS);
		this.player = player;
		this.donePlaying = false;
	}
	
	@Override
	public void update() 
	{
		if(this.player.isDead)
		{
			this.donePlaying = true;
		}
		else if(hasPlayed && !Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(this))
		{
			this.donePlaying = true;
		}
		else
		{
			this.donePlaying = false;
		}
		this.xPosF = (float) this.player.posX;
		this.yPosF = (float) this.player.posY;
		this.zPosF = (float) this.player.posZ;
	}
	
	/**
	 * Play this music
	 */
	public void play()
	{
		Minecraft.getMinecraft().getSoundHandler().stopSounds();
		Minecraft.getMinecraft().getSoundHandler().playSound(this);
		PortableJukebox.debug("Playing?: "+Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(this));
		hasPlayed = true;
	}
}
