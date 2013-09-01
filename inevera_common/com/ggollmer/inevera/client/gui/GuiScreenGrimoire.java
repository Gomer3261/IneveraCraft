package com.ggollmer.inevera.client.gui;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * IneveraCraft
 *
 * GuiScreenGrimoire.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GuiScreenGrimoire extends GuiScreen
{
	private int updateCount;
	private int bookImageWidth = 192;
	private int bookImageHeight = 192;
	private int totalPages = 1;
	private int currPage;
	String BookTitle = "Grimoire";
    private GuiButton buttonDone;
    
    public GuiScreenGrimoire(EntityPlayer player, ItemStack item)
    {
    }
    
    @Override
    public void updateScreen()
    {
        super.updateScreen();
        ++this.updateCount;
    }
    
    /*
    @Override
    public void initGui()
    {
        this.buttonList.clear();
        Keyboard.enableRepeatEvents(true);

        if (this.bookIsUnsigned)
        {
            this.buttonList.add(this.buttonSign = new GuiButton(3, this.width / 2 - 100, 4 + this.bookImageHeight, 98, 20, StatCollector.translateToLocal("book.signButton")));
            this.buttonList.add(this.buttonDone = new GuiButton(0, this.width / 2 + 2, 4 + this.bookImageHeight, 98, 20, StatCollector.translateToLocal("gui.done")));
            this.buttonList.add(this.buttonFinalize = new GuiButton(5, this.width / 2 - 100, 4 + this.bookImageHeight, 98, 20, StatCollector.translateToLocal("book.finalizeButton")));
            this.buttonList.add(this.buttonCancel = new GuiButton(4, this.width / 2 + 2, 4 + this.bookImageHeight, 98, 20, StatCollector.translateToLocal("gui.cancel")));
        }
        else
        {
            this.buttonList.add(this.buttonDone = new GuiButton(0, this.width / 2 - 100, 4 + this.bookImageHeight, 200, 20, StatCollector.translateToLocal("gui.done")));
        }

        int i = (this.width - this.bookImageWidth) / 2;
        byte b0 = 2;
        this.buttonList.add(this.buttonNextPage = new GuiButtonNextPage(1, i + 120, b0 + 154, true));
        this.buttonList.add(this.buttonPreviousPage = new GuiButtonNextPage(2, i + 38, b0 + 154, false));
        this.updateButtons();
    }
    */
}
