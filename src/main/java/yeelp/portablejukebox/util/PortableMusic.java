package yeelp.portablejukebox.util;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

/**
 * Portable Music
 * @author Yeelp
 *
 */
public class PortableMusic extends MovingSound
{
	private EntityPlayer player;
	/**
	 * Make a Portable Music
	 * @param player the player this music should follow
	 * @param soundIn the sound to play
	 */
	public PortableMusic(EntityPlayer player, SoundEvent soundIn) 
	{
		super(soundIn, SoundCategory.RECORDS);
		this.player = player;
	}
	
	@Override
	public void update() 
	{
		if(this.player.isDead)
		{
			this.donePlaying = true;
		}
		this.xPosF = (float) this.player.posX;
		this.yPosF = (float) this.player.posY;
		this.zPosF = (float) this.player.posZ;
	}
	
}
