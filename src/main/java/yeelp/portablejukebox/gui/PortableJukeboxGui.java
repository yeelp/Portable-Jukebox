package yeelp.portablejukebox.gui;

import javax.annotation.Nonnull;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import yeelp.portablejukebox.inventory.ContainerPortableJukebox;

public class PortableJukeboxGui extends GuiContainer
{
	private static final ResourceLocation  TEXTURE = new ResourceLocation("minecraft", "textures/gui/container/generic_54.png");
	private final ContainerPortableJukebox container;
	private final IInventory playerInventory;
	private final int inventoryRows;
	
	public PortableJukeboxGui(IInventory invIn, @Nonnull ContainerPortableJukebox jukebox)
	{
		super(jukebox);
		this.container = jukebox;
		this.playerInventory = invIn;
		this.inventoryRows = 1;
		this.ySize = 114 + this.inventoryRows*18;
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
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
        this.drawTexturedModalRect(i, j + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
	}
}
