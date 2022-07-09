package craig.software.mc.angels.common;

import com.google.gson.JsonObject;
import craig.software.mc.angels.WeepingAngels;
import java.util.List;
import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/* Created by Craig on 10/03/2021 */
public class WAGlm {
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, WeepingAngels.MODID);

    public static final RegistryObject<DiscLoot.Serializer> DISC_LOOT = GLM.register("loot", DiscLoot.Serializer::new);

    public static ItemStack genDisc(Random random) {
        return new ItemStack(random.nextBoolean() ? WAObjects.Items.SALLY.get() : WAObjects.Items.TIME_PREVAILS.get());
    }

    public static class DiscLoot extends LootModifier {

        private final int chance;

        public DiscLoot(final ILootCondition[] conditionsIn, int chance) {
            super(conditionsIn);
            this.chance = chance;
        }

        @Override
        protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {

            if (context.getRandom().nextInt(100) <= chance) {
                generatedLoot.add(genDisc(context.getRandom()));
            }

            return generatedLoot;
        }

        private static class Serializer extends GlobalLootModifierSerializer<DiscLoot> {
            @Override
            public DiscLoot read(ResourceLocation location, JsonObject object, ILootCondition[] conditions) {
                final int multiplicationFactor = JSONUtils.getAsInt(object, "chance", 2);
                return new DiscLoot(conditions, multiplicationFactor);
            }

            @Override
            public JsonObject write(DiscLoot instance) {
                final JsonObject obj = this.makeConditions(instance.conditions);
                obj.addProperty("chance", instance.chance);
                return obj;
            }
        }
    }

}