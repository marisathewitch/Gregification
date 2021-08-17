package gregification.proxy;

import gregification.config.GFConfig;
import gregification.tinkers.TinkersMaterials;
import gregification.util.GFLog;
import gregification.util.GFValues;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent;

@EventBusSubscriber
public class TiConCommonProxy {

    @Optional.Method(modid = GFValues.MODID_TICON)
    @EventHandler
    public void preInit() {
        if (GFConfig.tinkers.enableTinkers) {
            GFLog.ticonLogger.info("Registering GT Materials through Tinker's Construct");
            TinkersMaterials.register();
        }
    }

    @Optional.Method(modid = GFValues.MODID_TICON)
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        if (GFConfig.tinkers.enableTinkers) {
            GFLog.ticonLogger.info("Registering Tinker's Construct Compat Recipes");
        }
    }

    @Optional.Method(modid = GFValues.MODID_TICON)
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void smeltingRemoval(TinkerRegisterEvent.MeltingRegisterEvent event) {

    }

    @Optional.Method(modid = GFValues.MODID_TICON)
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void alloyRemoval(TinkerRegisterEvent.AlloyRegisterEvent event) {
        if (event.getRecipe().getResult() == Materials.Brass.getFluid(3)) {
            event.setCanceled(true);
        }
    }

    private static boolean matches(RecipeMatch recipe, OrePrefix prefix, Material material) {
        return recipe.matches(NonNullList.withSize(1,
                OreDictUnifier.get(prefix, material))).isPresent();
    }
}
