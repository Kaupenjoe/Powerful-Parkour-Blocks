package net.kaupenjoe.powerfulparkour.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.kaupenjoe.powerfulparkour.block.entity.EffectBlockEntity;
import net.kaupenjoe.powerfulparkour.networking.ModMessages;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Inventory;

public class EffectBlockScreen extends AbstractContainerScreen<EffectBlockScreenHandler> {
    private static final String BASE_LOCATION = "textures/mob_effect/";
    private ResourceLocation currentEffectLocation = new ResourceLocation("textures/mob_effect/speed.png");
    private EditBox durationBox;
    private EditBox levelBox;

    private EffectBlockEntity entity;

    private static final Component DURATION_LABEL = Component.literal("Duration");
    private static final Component LEVEL_LABEL = Component.literal("Level");


    public EffectBlockScreen(EffectBlockScreenHandler abstractContainerMenu, Inventory inventory, Component component) {
        super(abstractContainerMenu, inventory, component);
        entity = ((EffectBlockEntity) menu.blockEntity);
    }

    @Override
    protected void containerTick() {
        durationBox.tick();
        levelBox.tick();
        super.containerTick();
    }

    @Override
    public void onClose() {
        updateBlockEntity(MobEffect.byId(entity.id), entity.id);
        super.onClose();
    }

    @Override
    protected void init() {
        int l = this.height / 4 + 48;
        this.durationBox = new EditBox(this.font, this.width / 2 - 100, 40, 300, 20, Component.translatable("effect_block.duration"));
        this.durationBox.setMaxLength(15);
        this.durationBox.setValue(String.valueOf(entity.duration));
        this.addWidget(this.durationBox);

        this.levelBox = new EditBox(this.font, this.width / 2 - 100, 80, 300, 20, Component.translatable("effect_block.effect_level"));
        this.levelBox.setMaxLength(15);
        this.levelBox.setValue(String.valueOf(entity.effectLevel));
        this.addWidget(this.levelBox);

        currentEffectLocation = new ResourceLocation(BASE_LOCATION + MobEffect.byId(entity.id).getDescriptionId().replace('.', '_').substring(17) + ".png");
        for(int i = 1; i <= 33; i++) {
            MobEffect currentEffect = MobEffect.byId(i);
            int tempIndex = i;
            int x = i < 16 ? i : i-15;
            int y = i < 16 ? 0 : 20;

            this.addRenderableWidget(new ImageButton(120 + (20 * x), l + 10 + y, 18, 18, 0, 0, 19,
                    new ResourceLocation(BASE_LOCATION + currentEffect.getDescriptionId().replace('.', '_').substring(17) + ".png"),
                    18, 18, (button) -> {
                updateBlockEntity(currentEffect, tempIndex);
            }));
        }

        this.setInitialFocus(this.durationBox);
    }

    private void updateBlockEntity(MobEffect currentEffect, int tempIndex) {
        FriendlyByteBuf data = PacketByteBufs.create();
        data.writeInt(tempIndex);
        data.writeInt(Integer.parseInt(durationBox.getValue()));
        data.writeInt(Integer.parseInt(levelBox.getValue()));
        ClientPlayNetworking.send(ModMessages.EFFECT_SYNC_ID, data);

        entity.setEffect(tempIndex);
        entity.duration = Integer.parseInt(durationBox.getValue());
        entity.effectLevel = Integer.parseInt(levelBox.getValue());
        entity.id = tempIndex;

        currentEffectLocation = new ResourceLocation(BASE_LOCATION + currentEffect.getDescriptionId().replace('.', '_').substring(17) + ".png");
    }

    @Override
    public void resize(Minecraft minecraft, int i, int j) {
        String s = durationBox.getValue();
        String s1 = levelBox.getValue();
        init(minecraft, i, j);
        this.durationBox.setValue(s);
        this.levelBox.setValue(s1);
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);

        drawString(pPoseStack, this.font, DURATION_LABEL, this.width / 2 - 150, 45, 10526880);
        durationBox.render(pPoseStack, mouseX, mouseY, delta);

        drawString(pPoseStack, this.font, LEVEL_LABEL, this.width / 2 - 150, 85, 10526880);
        levelBox.render(pPoseStack, mouseX, mouseY, delta);

        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }

    public boolean keyPressed(int p_99400_, int p_99401_, int p_99402_) {
        if (super.keyPressed(p_99400_, p_99401_, p_99402_)) {
            return true;
        } else if (p_99400_ != 257 && p_99400_ != 335) {
            return false;
        } else {
            updateBlockEntity(MobEffect.byId(entity.id), entity.id);
            return true;
        }
    }

    @Override
    protected void renderBg(PoseStack poseStack, float f, int i, int j) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        int x = width / 2;
        int y = height / 2;

        RenderSystem.setShaderTexture(0, currentEffectLocation);
        blit(poseStack, x, y - 64, 0, 0, 18, 18, 18, 18);
    }
}
