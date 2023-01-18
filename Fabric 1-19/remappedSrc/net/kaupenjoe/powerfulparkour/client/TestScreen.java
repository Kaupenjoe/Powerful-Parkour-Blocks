package net.kaupenjoe.powerfulparkour.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class TestScreen extends Screen {
    public TestScreen() {
        super(Text.literal("Test Screen"));
    }
    private static final String BASE_LOCATION = "textures/mob_effect/";
    private Identifier currentEffectLocation = new Identifier("textures/mob_effect/speed.png");
    private TextFieldWidget durationBox;
    private TextFieldWidget levelBox;

    private int duration = 200;
    private int level;

    @Override
    public void tick() {
        durationBox.tick();
        super.tick();
    }

    @Override
    protected void init() {
        int l = this.height / 4 + 48;
        this.durationBox = new TextFieldWidget(this.textRenderer, this.width / 2, 40, 300, 20, Text.translatable("effect_block.duration")) {
            public boolean charTyped(char c, int i) {
                return true;
            }
        };
        this.durationBox.setMaxLength(64);
        this.durationBox.setText(String.valueOf(duration));
        this.addSelectableChild(this.durationBox);

        for(int i = 1; i <= 33; i++) {
            StatusEffect currentEffect = StatusEffect.byRawId(i);
            int tempIndex = i;
            int x = i < 16 ? i : i-15;
            int y = i < 16 ? 0 : 20;

            this.addDrawableChild(new TexturedButtonWidget(120 + (20 * x), l + 10 + y, 18, 18, 0, 0, 19,
                    new Identifier(BASE_LOCATION + currentEffect.getTranslationKey().replace('.', '_').substring(17) + ".png"),
                    18, 18, (button) -> {
                PacketByteBuf data = PacketByteBufs.create();
                data.writeInt(tempIndex);
                // ((EffectBlockEntity) menu.blockEntity).setEffect(tempIndex);
                ParkourBlockMod.LOGGER.info("Duration " + String.valueOf(duration));

                // ClientPlayNetworking.send(ModMessages.EFFECT_SYNC_ID, data);
                // currentEffectLocation = new ResourceLocation(BASE_LOCATION + currentEffect.getDescriptionId().replace('.', '_').substring(17) + ".png");
            }));
        }

        this.setInitialFocus(this.durationBox);
    }

    @Override
    public void resize(MinecraftClient minecraft, int i, int j) {
        String s = durationBox.getText();
        init(minecraft, i, j);
        this.durationBox.setText(s);
    }

    @Override
    public void render(MatrixStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);

        durationBox.render(pPoseStack, mouseX, mouseY, delta);

        int x = width / 2;
        int y = height / 2;
        RenderSystem.setShaderTexture(0, currentEffectLocation);
        drawTexture(pPoseStack, x, y - 64, 0, 0, 18, 18, 18, 18);
    }

    public boolean keyPressed(int p_99400_, int p_99401_, int p_99402_) {
        if (super.keyPressed(p_99400_, p_99401_, p_99402_)) {
            return true;
        } else if (p_99400_ != 257 && p_99400_ != 335) {
            return false;
        } else {
            duration = Integer.parseInt(durationBox.getText());
            return true;
        }
    }

    // @Override
    // protected void renderBg(PoseStack poseStack, float f, int i, int j) {
    //     RenderSystem.setShader(GameRenderer::getPositionTexShader);
    //     RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    //     int x = width / 2;
    //     int y = height / 2;
//
    //     RenderSystem.setShaderTexture(0, currentEffectLocation);
    //     blit(poseStack, x, y - 64, 0, 0, 18, 18, 18, 18);
    // }
}
