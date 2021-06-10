package me.suff.mc.angels.utils;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.util.JSONUtils;

import java.util.Set;

/**
 * Created by 50ap5ud5 on 13 Feb 2020 @ 11:28:06 am
 */
public class FortuneEnchantBonus extends LootFunction {

    private final RandomValueRange count;
    private final int limit;

    private FortuneEnchantBonus(ILootCondition[] conditions, RandomValueRange countIn, int limitIn) {
        super(conditions);
        this.count = countIn;
        this.limit = limitIn;
    }

    public FortuneEnchantBonus.Builder builder(RandomValueRange p_215915_0_) {
        return new FortuneEnchantBonus.Builder(p_215915_0_);
    }

    public Set<LootParameter<?>> getReferencedContextParams() {
        return ImmutableSet.of(LootParameters.KILLER_ENTITY);
    }

    private boolean hasLimit() {
        return this.limit > 0;
    }

    public ItemStack run(ItemStack stack, LootContext context) {
        Entity entity = context.getParamOrNull(LootParameters.KILLER_ENTITY);
        if (entity instanceof LivingEntity) {
            int i = AngelUtil.getFortuneModifier((LivingEntity) entity);
            if (i == 0) {
                return stack;
            }

            float f = (float) i * this.count.getFloat(context.getRandom());
            stack.grow(Math.round(f));
            if (this.hasLimit() && stack.getCount() > this.limit) {
                stack.setCount(this.limit);
            }
        }

        return stack;
    }

    @Override
    public LootFunctionType getType() {
        return null;
    }

    public static class Builder extends LootFunction.Builder<FortuneEnchantBonus.Builder> {
        private final RandomValueRange count;
        private int limit = 0;

        public Builder(RandomValueRange p_i50932_1_) {
            this.count = p_i50932_1_;
        }

        protected FortuneEnchantBonus.Builder getThis() {
            return this;
        }

        public FortuneEnchantBonus.Builder setLimit(int p_216072_1_) {
            this.limit = p_216072_1_;
            return this;
        }

        public ILootFunction build() {
            return new FortuneEnchantBonus(this.getConditions(), this.count, this.limit);
        }
    }

    public static class Serializer extends LootFunction.Serializer<FortuneEnchantBonus> {
        public Serializer() {
            super();
        }

        //serialize
        @Override
        public void serialize(JsonObject object, FortuneEnchantBonus functionClazz, JsonSerializationContext serializationContext) {
            super.serialize(object, functionClazz, serializationContext);
            object.add("count", serializationContext.serialize(functionClazz.count));
            if (functionClazz.hasLimit()) {
                object.add("limit", serializationContext.serialize(functionClazz.limit));
            }

        }

        public FortuneEnchantBonus deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
            int i = JSONUtils.getAsInt(object, "limit", 0);
            return new FortuneEnchantBonus(conditionsIn, JSONUtils.getAsObject(object, "count", deserializationContext, RandomValueRange.class), i);
        }
    }

}
