package net.tefyer.matteroffice.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.EditBox;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tefyer.matteroffice.MatterOffice;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.math.BigInteger;

public class TransumationStoneScreen extends HandledScreen<TransumationStoneScreenHandler> {
    public static final int imageWidth = 228,
            imageHeight= 196;
    TransumationStoneScreenHandler handler;

    public static final Identifier GUI_TEXTURE =
            Identifier.of(MatterOffice.MOD_ID, "textures/gui/transmute.png");


    public TransumationStoneScreen(TransumationStoneScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.handler = handler;
//        this.titleLabelX = 6;
//        this.titleLabelY = 8;
    }

    @Override
    protected void init() {
        this.x = (this.width - imageWidth) / 2;
        this.y = (this.height - imageHeight) / 2;
    }

    @Override
    public void resize(@NotNull MinecraftClient minecraft, int width, int height) {
        init(minecraft, width, height);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawTexture(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawSlot(DrawContext context, Slot slot) {
        super.drawSlot(context, slot);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        context.drawText(textRenderer,title, titleX, titleY, 0x404040, false);
    }

}
