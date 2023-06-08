package mc.craig.software.angels.utils;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.Set;

/**
 * Created by 50ap5ud5 on 13 Feb 2020 @ 11:28:06 am
 */
public class FortuneBonusEnchant extends LootItemConditionalFunction {

    private final IntRange count;
    private final int limit;

    private FortuneBonusEnchant(LootItemCondition[] conditions, IntRange countIn, int limitIn) {
        super(conditions);
        this.count = countIn;
        this.limit = limitIn;
    }

    public FortuneBonusEnchant.Builder builder(IntRange p_215915_0_) {
        return new FortuneBonusEnchant.Builder(p_215915_0_);
    }

    public Set<LootContextParam<?>> getReferencedContextParams() {
        return ImmutableSet.of(LootContextParams.KILLER_ENTITY);
    }

    private boolean hasLimit() {
        return this.limit > 0;
    }


    public ItemStack run(ItemStack stack, LootContext context) {
        Entity entity = context.getParamOrNull(LootContextParams.KILLER_ENTITY);
        if (entity instanceof LivingEntity) {
            int i = AngelUtil.getFortuneModifier((LivingEntity) entity);
            if (i == 0) {
                return stack;
            }

            stack.grow(Math.round(context.getRandom().nextInt(5))); //TODO This is not nice
            if (this.hasLimit() && stack.getCount() > this.limit) {
                stack.setCount(this.limit);
            }
        }

        return stack;
    }

    @Override
    public LootItemFunctionType getType() {
        return null;
    }

    public static class Builder extends LootItemConditionalFunction.Builder<FortuneBonusEnchant.Builder> {
        private final IntRange count;
        private int limit = 0;

        public Builder(IntRange p_i50932_1_) {
            this.count = p_i50932_1_;
        }

        protected FortuneBonusEnchant.Builder getThis() {
            return this;
        }

        public FortuneBonusEnchant.Builder setLimit(int p_216072_1_) {
            this.limit = p_216072_1_;
            return this;
        }

        public LootItemFunction build() {
            return new FortuneBonusEnchant(this.getConditions(), this.count, this.limit);
        }
    }

    public static class Serializer extends LootItemConditionalFunction.Serializer<FortuneBonusEnchant> {
        public Serializer() {
            super();
        }

        //serialize
        @Override
        public void serialize(JsonObject object, FortuneBonusEnchant functionClazz, JsonSerializationContext serializationContext) {
            super.serialize(object, functionClazz, serializationContext);
            object.add("count", serializationContext.serialize(functionClazz.count));
            if (functionClazz.hasLimit()) {
                object.add("limit", serializationContext.serialize(functionClazz.limit));
            }

        }

        public FortuneBonusEnchant deserialize(JsonObject object, JsonDeserializationContext deserializationContext, LootItemCondition[] conditionsIn) {
            int i = GsonHelper.getAsInt(object, "limit", 0);
            return new FortuneBonusEnchant(conditionsIn, GsonHelper.getAsObject(object, "count", deserializationContext, IntRange.class), i);
        }
    }

}
