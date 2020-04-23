package yeelp.portablejukebox.util;

/**
 * Different playing configurations
 * @author Yeelp
 *
 */
public final class PlayConfiguration 
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
}
