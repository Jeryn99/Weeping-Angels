package me.swirtzly.minecraft.angels.client.poses;

import me.swirtzly.minecraft.angels.WeepingAngels;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.Collection;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AngelPoses extends ForgeRegistryEntry<AngelPoses> {

    public static IForgeRegistry<AngelPoses> REGISTRY;

    public static AngelPoses POSE_ANGRY = new AngelPoses(() -> new PoseAngry("angry"));
    public static AngelPoses POSE_ANGRY_TWO = new AngelPoses(() -> new PoseAngryTwo("angry_two"));
    public static AngelPoses POSE_SHY = new AngelPoses(() -> new PoseShy("shy"));
    public static AngelPoses POSE_HIDING_FACE = new AngelPoses(() -> new PoseHidingFace("hiding_face"));
    public static AngelPoses POSE_OPEN_ARMS = new AngelPoses(() -> new PoseOpenArms("open_arms"));
    public static AngelPoses POSE_IDLE = new AngelPoses(() -> new PoseIdle("idle"));

    @SubscribeEvent
    public static void onRegisterNewRegistries(RegistryEvent.NewRegistry e) {
        REGISTRY = new RegistryBuilder<AngelPoses>().setName(new ResourceLocation(WeepingAngels.MODID, "poses")).setType(AngelPoses.class).setIDRange(0, 2048).create();
    }

    public AngelPoses(Supplier<PoseBase> supplier) {
        this.supplier = supplier;
        this.setRegistryName(supplier.get().getRegistryName());
    }

    private Supplier<PoseBase> supplier;

    @SubscribeEvent
    public static void onRegisterTypes(RegistryEvent.Register<AngelPoses> e) {
        e.getRegistry().registerAll(POSE_ANGRY, POSE_ANGRY_TWO, POSE_HIDING_FACE, POSE_IDLE, POSE_OPEN_ARMS, POSE_SHY);
    }

    public PoseBase create() {
        return this.supplier.get();
    }

    public static PoseBase getRandomPose() {
        Collection<AngelPoses> poses = REGISTRY.getValues();
        return poses.stream().skip((int) (poses.size() * Math.random())).findFirst().get().create();
    }

    /* TODO: Remove in 1.16 versions: It only exists in 1.14.4 to be compatible with older 1.14 builds that used bad handling of the pose system*/
    public static AngelPoses getPoseFromString(String poseName) {
        return REGISTRY.getValue(poseName.startsWith(WeepingAngels.MODID) ? new ResourceLocation(poseName) : new ResourceLocation(WeepingAngels.MODID, poseName.replace("minecraft:", "")));
    }

}
