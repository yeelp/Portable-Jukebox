package yeelp.portablejukebox.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import yeelp.portablejukebox.PortableJukebox;

/**
 * Different playing configurations
 * @author Yeelp
 *
 */
public final class PlayConfiguration implements INBTSerializable<NBTTagCompound>
{
	/**
	 * The different play styles of the portable jukebox. Can be NORMAL or SHUFFLE
	 * @author Yeelp
	 *
	 */
	public enum PlayStyle
	{
		NORMAL,
		SHUFFLE;
		/**
		 * Convert an ordinal to a PlayStyle
		 * @param b a byte which represents the ordinal
		 * @return PlayStyle.SHUFFLE if b == 1, PlayStyle.NORMAL otherwise
		 */
		public static PlayStyle fromOrdinal(byte b)
		{
			switch(b)
			{
				case 0:
					return PlayStyle.NORMAL;
				case 1:
					return PlayStyle.SHUFFLE;
				default:
					PortableJukebox.warn(b + " isn't a valid PlayStyle! Setting to NORMAL.");
					return PlayStyle.NORMAL;
			}
		}
	}
	
	/**
	 * The Different Repeat styles of the portable jukebox. Can be NONE, ALL, or SINGLE
	 * @author Yeelp
	 *
	 */
	public enum RepeatStyle
	{
		NONE,
		ALL,
		SINGLE;
		/**
		 * Convert an ordinal to a RepeatStyle
		 * @param b a byte which represents the ordinal
		 * @return RepeatStyle.ALL if b == 1, RepeatStyle.SINGLE if b == 2, RepeatStyle.NONE otherwise.
		 */
		public static RepeatStyle fromOrdinal(byte b)
		{
			switch(b)
			{
				case 0:
					return RepeatStyle.NONE;
				case 1:
					return RepeatStyle.ALL;
				case 2:
					return RepeatStyle.SINGLE;
				default:
					PortableJukebox.warn(b + " isn't a valid ordinal for RepeatStyle! Setting to NONE.");
					return RepeatStyle.NONE;
			}
		}
	}
	
	private PlayStyle playStyle;
	private RepeatStyle repeatStyle;
	
	/**
	 * Make a new Play Configuration
	 * @param playStyle The play style. NORMAl or SHUFFLE
	 * @param repeatStyle The repeat style. NONE, ALL, or SINGLE
	 */
	public PlayConfiguration(PlayStyle playStyle, RepeatStyle repeatStyle)
	{
		this.playStyle = playStyle;
		this.repeatStyle = repeatStyle;
	}
	
	/**
	 * Get the PlayStyle
	 * @return the PlayStyle
	 */
	public PlayStyle getPlayStyle()
	{
		return this.playStyle;
	}

	/**
	 * Set the PlayStyle
	 * @param style the style. NORMAL or SHUFFLE
	 */
	public void setPlayStyle(PlayStyle style)
	{
		this.playStyle = style;
	}
	
	/**
	 * Get the RepeatStyle
	 * @return the RepeatStyle
	 */
	public RepeatStyle getRepeatStyle()
	{
		return this.repeatStyle;
	}
	
	/**
	 * Set the RepeatStyle
	 * @param style the style NONE, ALL, or SINGLE 
	 */
	public void setRepeatStyle(RepeatStyle style)
	{
		this.repeatStyle = style;
	}

	@Override
	public NBTTagCompound serializeNBT() 
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setByte("playStyle", Integer.valueOf(this.playStyle.ordinal()).byteValue());
		tag.setByte("repeatStyle", Integer.valueOf(this.repeatStyle.ordinal()).byteValue());
		return tag;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) 
	{
		this.playStyle = PlayStyle.fromOrdinal(nbt.getByte("playStyle"));
		this.repeatStyle = RepeatStyle.fromOrdinal(nbt.getByte("repeatStyle"));
	}
}
