package you.modid.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.FlowerFeature;
import net.minecraft.world.gen.feature.FlowerFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlowerFeature.class)
public class FlowerGenerationMixin {

    @Inject(method = "generate", at = @At("HEAD"), cancellable = true)
    private void filterSunflower(FlowerFeatureConfig config, StructureWorldAccess world, net.minecraft.world.gen.chunk.ChunkGenerator generator, Random random, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        // Генерируем состояние цветка на основе конфига
        BlockState flowerState = config.flowerStateProvider.get(random, pos);
        
        // Если это подсолнух (двойной цветок) - отменяем генерацию и выходим
        if (flowerState.isOf(Blocks.SUNFLOWER)) {
            cir.setReturnValue(false);
        }
    }
}