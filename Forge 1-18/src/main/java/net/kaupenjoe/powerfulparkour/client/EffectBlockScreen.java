package net.kaupenjoe.powerfulparkour.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.kaupenjoe.powerfulparkour.block.entity.EffectBlockEntity;
import net.kaupenjoe.powerfulparkour.networking.ModMessages;
import net.kaupenjoe.powerfulparkour.networking.packets.EffectC2SSync;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Inventory;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Optional;

import static java.lang.Character.isDigit;

public class EffectBlockScreen extends AbstractContainerScreen<EffectBlockScreenHandler> {
    private static final String BASE_LOCATION = "textures/mob_effect/";
    private ResourceLocation currentEffectLocation = new ResourceLocation("textures/mob_effect/speed.png");
    private ResourceLocation TEXTURE = new ResourceLocation(ParkourBlockMod.MOD_ID,"textures/gui/effect_block_gui.png");
    private EditBox durationBox;
    private EditBox levelBox;

    private EffectBlockEntity entity;

    private static final Component DURATION_LABEL = new TextComponent("Duration");
    private static final Component LEVEL_LABEL = new TextComponent("Level");


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
        this.inventoryLabelY = -50;
        this.inventoryLabelX = -50;
        this.titleLabelX = this.width / 2 - 30;
        this.titleLabelY = this.height / 2 - 75;

        this.durationBox = new EditBox(this.font,this.width / 2 - 80, 102, 60, 20, new TranslatableComponent("effect_block.duration")) {
            @Override
            public boolean charTyped(char c, int i) {
                return super.charTyped(c, i) && isDigit(c);
            }
        };
        this.durationBox.setMaxLength(15);
        this.durationBox.setValue(String.valueOf(entity.duration));
        this.addRenderableWidget(this.durationBox);

        this.levelBox = new EditBox(this.font,this.width / 2 + 20, 102, 60, 20, new TranslatableComponent("effect_block.effect_level")) {
            @Override
            public boolean charTyped(char c, int i) {
                return super.charTyped(c, i) && isDigit(c);
            }
        };
        this.levelBox.setMaxLength(15);
        this.levelBox.setValue(String.valueOf(entity.effectLevel));
        this.addRenderableWidget(this.levelBox);

        int x_ = (width - imageWidth) / 2;
        int y_ = (height - imageHeight) / 2;

        this.addRenderableWidget(new Button(x_ + 60,y_ + 170,60, 20, new TextComponent("Close"), (button) -> {
            Minecraft.getInstance().setScreen(null);
            updateBlockEntity(MobEffect.byId(entity.id), entity.id);
        }));

        currentEffectLocation = new ResourceLocation(BASE_LOCATION + MobEffect.byId(entity.id).getDescriptionId().replace('.', '_').substring(17) + ".png");
        for(int i = 1; i <= 32; i++) {
            MobEffect currentEffect = MobEffect.byId(i);
            int tempIndex = i;
            int x = i - 1;// i < 16 ? i : i-15;
            int y = 0;
            if(i <= 8) {
                x = i - 7;
                y = 0;
            } else if(i <= 16) {
                x = i - 15;
                y = 20;
            } else if(i <= 24) {
                x = i - 23;
                y = 40;
            } else if(i <= 32) {
                x = i - 31;
                y = 60;
            } else if(i <= 40) {
                x = i - 39;
                y = 80;
            }

            this.addRenderableWidget(new ImageButton(x_ + 128 + (20 * x),y_ + 85 + y, 18, 18, 0, 0, 19,
                    new ResourceLocation(BASE_LOCATION + currentEffect.getDescriptionId().replace('.', '_').substring(17) + ".png"),
                    18, 18, (button) -> {
                updateBlockEntity(currentEffect, tempIndex);
            }, (button, poseStack, i1, j) -> {
                renderTooltip(poseStack, List.of(new TranslatableComponent(currentEffect.getDescriptionId())), Optional.empty(), i1, j);
            }, TextComponent.EMPTY));
        }

        this.setInitialFocus(this.durationBox);
    }

    private void updateBlockEntity(MobEffect currentEffect, int tempIndex) {
        if(!NumberUtils.isParsable(durationBox.getValue())) {
            return;
        }

        if(!NumberUtils.isParsable(levelBox.getValue())) {
            return;
        }

        ModMessages.sendToServer(new EffectC2SSync(tempIndex, Integer.parseInt(durationBox.getValue()), Integer.parseInt(levelBox.getValue())));

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

        durationBox.render(pPoseStack, mouseX, mouseY, delta);
        levelBox.render(pPoseStack, mouseX, mouseY, delta);

        super.render(pPoseStack, mouseX, mouseY, delta);

        drawString(pPoseStack, this.font, DURATION_LABEL, this.width / 2 - 80, 90, 16777215);
        drawString(pPoseStack, this.font, LEVEL_LABEL, this.width / 2 + 20, 90, 16777215);

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
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(poseStack, x, y,0, 0, 176, 199);

        RenderSystem.setShaderTexture(0, currentEffectLocation);
        blit(poseStack, x + 79, y + 20, 0, 0, 18, 18, 18, 18);
    }
}
