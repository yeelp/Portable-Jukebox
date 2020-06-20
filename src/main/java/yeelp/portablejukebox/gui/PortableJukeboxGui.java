package yeelp.portablejukebox.gui;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.util.text.TextComponentTranslation;
import yeelp.portablejukebox.ModConsts;
import yeelp.portablejukebox.inventory.ContainerPortableJukebox;
import yeelp.portablejukebox.util.PlayConfiguration;
import yeelp.portablejukebox.util.PlayConfiguration.PlayStyle;
import yeelp.portablejukebox.util.PlayConfiguration.RepeatStyle;

public class PortableJukeboxGui extends GuiContainer
{
	private static final ResourceLocation  TEXTURE = new ResourceLocation(ModConsts.MODID, "textures/gui/jukeboxinv.png");
	private static final String[] repeatLabels = {new TextComponentTranslation("button.portablejukebox.repeatnone").getFormattedText(), new TextComponentTranslation("button.portablejukebox.repeatall").getFormattedText(), new TextComponentTranslation("button.portablejukebox.repeatone").getFormattedText()};
	private static final String[] shuffleLabels = {new TextComponentTranslation("button.portablejukebox.shuffleoff").getFormattedText(), new TextComponentTranslation("button.portablejukebox.shuffleon").getFormattedText()};
	private final ContainerPortableJukebox container;
	private final IInventory playerInventory;
	private final int inventoryRows;
	private final GuiButton repeatButton = new GuiButton(0, this.width/2 + 250, this.height/2 + 60, 85, 20, new TextComponentTranslation("button.portablejukebox.repeatnone").getFormattedText());
	private final GuiButton playButton = new GuiButton(1, this.width/2 + 170, this.height/2 + 60, 75, 20, new TextComponentTranslation("button.portablejukebox.shuffleon").getFormattedText());
	private static final Map<String, Tuple<RepeatStyle, String>> nextRepeatStyle = new HashMap<String, Tuple<RepeatStyle, String>>();
	private static final Map<String, Tuple<PlayStyle, String>> nextPlayStyle = new HashMap<String, Tuple<PlayStyle, String>>();
	static
	{
		nextRepeatStyle.put(repeatLabels[0], new Tuple(RepeatStyle.ALL, new TextComponentTranslation("button.portablejukebox.repeatall").getFormattedText()));
		nextRepeatStyle.put(repeatLabels[1], new Tuple(RepeatStyle.SINGLE, new TextComponentTranslation("button.portablejukebox.repeatone").getFormattedText()));
		nextRepeatStyle.put(repeatLabels[2], new Tuple(RepeatStyle.NONE, new TextComponentTranslation("button.portablejukebox.repeatnone").getFormattedText()));
		
		nextPlayStyle.put(shuffleLabels[0], new Tuple(PlayStyle.SHUFFLE, new TextComponentTranslation("button.portablejukebox.shuffleon").getFormattedText()));
		nextPlayStyle.put(shuffleLabels[1], new Tuple(PlayStyle.NORMAL, new TextComponentTranslation("button.portablejukebox.shuffleoff").getFormattedText()));
	}
	
	public PortableJukeboxGui(IInventory invIn, @Nonnull ContainerPortableJukebox jukebox)
	{
		super(jukebox);
		this.container = jukebox;
		this.playerInventory = invIn;
		this.inventoryRows = 1;
		this.ySize = 150;
	}
	
	@Override
	public void initGui()
	{
		repeatButton.displayString = repeatLabels[this.container.getSettings().getPlayConfiguration().getRepeatStyle().ordinal()];
		playButton.displayString = shuffleLabels[this.container.getSettings().getPlayConfiguration().getPlayStyle().ordinal()];
		this.buttonList.clear();
		this.buttonList.add(repeatButton);
		this.buttonList.add(playButton);
	}

	@Override 
	public void actionPerformed(GuiButton button)
	{
		switch(button.id)
		{
			case 0:
				Tuple rt = nextRepeatStyle.get(button.displayString);
				button.displayString = (String) rt.getSecond();
				this.container.getSettings().getPlayConfiguration().setRepeatStyle((RepeatStyle) rt.getFirst());
				break;
			case 1:
				Tuple pt = nextPlayStyle.get(button.displayString);
				button.displayString = (String) pt.getSecond();
				this.container.getSettings().getPlayConfiguration().setPlayStyle((PlayStyle) pt.getFirst());
				break;
			default:
				break;
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
	}
}
