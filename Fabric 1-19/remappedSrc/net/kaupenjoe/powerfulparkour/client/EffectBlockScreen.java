package net.kaupenjoe.powerfulparkour.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.kaupenjoe.powerfulparkour.block.entity.EffectBlockEntity;
import net.kaupenjoe.powerfulparkour.networking.ModMessages;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Optional;

import static java.lang.Character.isDigit;

public class EffectBlockScreen extends HandledScreen<EffectBlockScreenHandler> {
    private static final String BASE_LOCATION = "textures/mob_effect/";
    private Identifier currentEffectLocation = new Identifier("textures/mob_effect/speed.png");
    private Identifier TEXTURE = new Identifier(ParkourBlockMod.MOD_ID,"textures/gui/effect_block_gui.png");
    private TextFieldWidget durationBox;
    private TextFieldWidget levelBox;

    private EffectBlockEntity entity;

    private static final Text DURATION_LABEL = Text.literal("Duration");
    private static final Text LEVEL_LABEL = Text.literal("Level");


    public EffectBlockScreen(EffectBlockScreenHandler abstractContainerMenu, PlayerInventory inventory, Text component) {
        super(abstractContainerMenu, inventory, component);
        entity = ((EffectBlockEntity) handler.blockEntity);
    }

    @Override
    protected void handledScreenTick() {
        durationBox.tick();
        levelBox.tick();
        super.handledScreenTick();
    }

    @Override
    public void close() {
        updateBlockEntity(StatusEffect.byRawId(entity.id), entity.id);
        super.close();
    }

    @Override
    protected void init() {
        this.playerInventoryTitleY = -50;
        this.playerInventoryTitleX = -50;
        this.titleX = this.width / 2 - 30;
        this.titleY = this.height / 2 - 75;

        this.durationBox = new TextFieldWidget(this.textRenderer,this.width / 2 - 80, 102, 60, 20, Text.translatable("effect_block.duration")) {
            @Override
            public boolean charTyped(char c, int i) {
                return super.charTyped(c, i) && isDigit(c);
            }
        };
        this.durationBox.setMaxLength(15);
        this.durationBox.setText(String.valueOf(entity.duration));
        this.addDrawableChild(this.durationBox);

        this.levelBox = new TextFieldWidget(this.textRenderer,this.width / 2 + 20, 102, 60, 20, Text.translatable("effect_block.effect_level")) {
            @Override
            public boolean charTyped(char c, int i) {
                return super.charTyped(c, i) && isDigit(c);
            }
        };
        this.levelBox.setMaxLength(15);
        this.levelBox.setText(String.valueOf(entity.effectLevel));
        this.addDrawableChild(this.levelBox);

        int x_ = (width - backgroundWidth) / 2;
        int y_ = (height - backgroundHeight) / 2;

        currentEffectLocation = new Identifier(BASE_LOCATION + StatusEffect.byRawId(entity.id).getTranslationKey().replace('.', '_').substring(17) + ".png");
        for(int i = 1; i <= 33; i++) {
            StatusEffect currentEffect = StatusEffect.byRawId(i);
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

            this.addDrawableChild(new TexturedButtonWidget(x_ + 128 + (20 * x),y_ + 85 + y, 18, 18, 0, 0, 19,
                    new Identifier(BASE_LOCATION + currentEffect.getTranslationKey().replace('.', '_').substring(17) + ".png"),
                    18, 18, (button) -> {
                updateBlockEntity(currentEffect, tempIndex);
            }, (button, poseStack, i1, j) -> {
                renderTooltip(poseStack, List.of(Component.translatable(currentEffect.getDescriptionId())), Optional.empty(), i1, j);
            }, ScreenTexts.EMPTY));
        }

        this.addDrawableChild(new ButtonWidget(x_ + 60,y_ + 170,60, 20, Text.literal("Close"), (button) -> {
            MinecraftClient.getInstance().setScreen(null);
            updateBlockEntity(StatusEffect.byRawId(entity.id), entity.id);
        }));

        this.setInitialFocus(this.durationBox);
    }

    private void updateBlockEntity(StatusEffect currentEffect, int tempIndex) {
        if(!NumberUtils.isParsable(durationBox.getText())) {
            return;
        }

        if(!NumberUtils.isParsable(levelBox.getText())) {
            return;
        }

        PacketByteBuf data = PacketByteBufs.create();
        data.writeInt(tempIndex);
        data.writeInt(Integer.parseInt(durationBox.getText()));
        data.writeInt(Integer.parseInt(levelBox.getText()));
        ClientPlayNetworking.send(ModMessages.EFFECT_SYNC_ID, data);

        entity.setEffect(tempIndex);
        entity.duration = Integer.parseInt(durationBox.getText());
        entity.effectLevel = Integer.parseInt(levelBox.getText());
        entity.id = tempIndex;

        currentEffectLocation = new Identifier(BASE_LOCATION + currentEffect.getTranslationKey().replace('.', '_').substring(17) + ".png");
    }

    @Override
    public void resize(MinecraftClient minecraft, int i, int j) {
        String s = durationBox.getText();
        String s1 = levelBox.getText();
        init(minecraft, i, j);
        this.durationBox.setText(s);
        this.levelBox.setText(s1);
    }

    @Override
    public void render(MatrixStack pPoseStack, int mouseX, int mouseY, float delta) {

        renderBackground(pPoseStack);

        durationBox.render(pPoseStack, mouseX, mouseY, delta);
        levelBox.render(pPoseStack, mouseX, mouseY, delta);

        super.render(pPoseStack, mouseX, mouseY, delta);

        drawTextWithShadow(pPoseStack, this.textRenderer, DURATION_LABEL, this.width / 2 - 80, 90, 16777215);
        drawTextWithShadow(pPoseStack, this.textRenderer, LEVEL_LABEL, this.width / 2 + 20, 90, 16777215);

        drawMouseoverTooltip(pPoseStack, mouseX, mouseY);
    }

    public boolean keyPressed(int p_99400_, int p_99401_, int p_99402_) {
        if (super.keyPressed(p_99400_, p_99401_, p_99402_)) {
            return true;
        } else if (p_99400_ != 257 && p_99400_ != 335) {
            return false;
        } else {
            updateBlockEntity(StatusEffect.byRawId(entity.id), entity.id);
            return true;
        }
    }

    @Override
    protected void drawBackground(MatrixStack poseStack, float f, int i, int j) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        RenderSystem.setShaderTexture(0, TEXTURE);
        drawTexture(poseStack, x, y,0, 0, 176, 199);

        RenderSystem.setShaderTexture(0, currentEffectLocation);
        drawTexture(poseStack, x + 79, y + 20, 0, 0, 18, 18, 18, 18);
    }
}
