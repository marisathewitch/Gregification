/*
    Copyright 2019, TheLimePixel, dan
    Greg's Construct

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package gregification.tinkers;

import com.google.common.collect.ImmutableList;
import gregification.config.GFConfig;
import gregification.util.GFLog;
import gregification.util.GFUtility;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.GemMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.common.config.Config;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static gregtech.api.unification.material.type.DustMaterial.MatFlags.GENERATE_ORE;

public class TinkersMaterials {

    private static final Map<Material, IngotMaterial> ingotMaterials = new HashMap<>();
    private static final Map<Material, GemMaterial>     gemMaterials = new HashMap<>();

    private static final List<gregtech.api.unification.material.type.Material> blackListedMaterials = ImmutableList.of(
            Materials.Iron, Materials.Cobalt, Materials.Copper, Materials.Bronze, Materials.Lead,
            Materials.Electrum, Materials.Silver, Materials.Steel, Materials.PigIron);

    private static final List<OrePrefix> orePrefixes = ImmutableList.of(
            OrePrefix.oreSand, OrePrefix.oreRedgranite, OrePrefix.oreNetherrack, OrePrefix.oreMarble,
            OrePrefix.oreEndstone, OrePrefix.oreEndstone, OrePrefix.oreBlackgranite, OrePrefix.oreBasalt);

    public static void register() {
        OrePrefix.ingot.addProcessingHandler(IngotMaterial.class, TinkersMaterials::processIngotMaterial);
        OrePrefix.gem.addProcessingHandler(GemMaterial.class, TinkersMaterials::processGemMaterial);
        OrePrefix.dust.addProcessingHandler(DustMaterial.class, TinkersMaterials::processDustMaterial);




        for (gregtech.api.unification.material.type.Material material : gregtech.api.unification.material.type.Material.MATERIAL_REGISTRY) {


            if (material instanceof GemMaterial) {
                GemMaterial gemMaterial = (GemMaterial) material;
                if (gemMaterial.toolDurability > 0) {
                    gemMaterials.put(
                            new Material(material.toString(), material.materialRGB)
                                    .setCastable(false)
                                    .setCraftable(true),
                            gemMaterial
                    );
                }
            }
        }
    }

    private static void processIngotMaterial(OrePrefix prefix, IngotMaterial material) {
        if (!blackListedMaterials.contains(material)) {
            if (material.toolDurability > 0) {
                ingotMaterials.put(
                        new Material(material.toString(), material.materialRGB)
                                .setCastable(true)
                                .setCraftable(false),
                        material
                );
            } else {
                TinkerRegistry.integrate(material.getMaterialFluid(), GFUtility.toUpperCamel(material.toString()));
            }

            if (GFConfig.tinkers.tinkersMaterialsSmelting) {
                if (material.blastFurnaceTemperature <= 0 && material.hasFlag(GENERATE_ORE)) {
                    registerMelting(material);
                }
            }
        }
    }

    private static void processGemMaterial(OrePrefix prefix, GemMaterial gemMaterial) {

    }

    private static void processDustMaterial(OrePrefix prefix, DustMaterial dustMaterial) {

    }

    private static void registerMelting(IngotMaterial material) {
        for (OrePrefix prefix : orePrefixes) {
            UnificationEntry entry = new UnificationEntry(prefix, material);
            if (material.getMaterialFluid() != null && !OreDictUnifier.get(entry).isEmpty()) {
                TinkerRegistry.registerMelting(entry.toString(), material.getMaterialFluid(), (int) (144 * material.oreMultiplier * Config.oreToIngotRatio));
            }
        }
    }

    // TODO Handle errors better
    @SuppressWarnings("ConstantConditions")
    public static void registerTinkerAlloys() {
        FluidStack material = null;
        try {
            TinkerRegistry.registerAlloy(material = Materials.Brass.getFluid(4), Materials.Copper.getFluid(3), Materials.Zinc.getFluid(1));
            TinkerRegistry.registerAlloy(material = Materials.Cupronickel.getFluid(2), Materials.Copper.getFluid(1), Materials.Nickel.getFluid(1));
            TinkerRegistry.registerAlloy(material = Materials.RedAlloy.getFluid(1), Materials.Copper.getFluid(1), Materials.Redstone.getFluid(4));
            TinkerRegistry.registerAlloy(material = Materials.Brass.getFluid(4), Materials.AnnealedCopper.getFluid(3), Materials.Zinc.getFluid(1));
            TinkerRegistry.registerAlloy(material = Materials.Cupronickel.getFluid(2), Materials.AnnealedCopper.getFluid(1), Materials.Nickel.getFluid(1));
            TinkerRegistry.registerAlloy(material = Materials.RedAlloy.getFluid(1), Materials.AnnealedCopper.getFluid(1), Materials.Redstone.getFluid(4));
            TinkerRegistry.registerAlloy(material = Materials.TinAlloy.getFluid(2), Materials.Iron.getFluid(1), Materials.Tin.getFluid(1));
            TinkerRegistry.registerAlloy(material = Materials.Invar.getFluid(3), Materials.Iron.getFluid(2), Materials.Nickel.getFluid(1));
            TinkerRegistry.registerAlloy(material = Materials.TinAlloy.getFluid(2), Materials.WroughtIron.getFluid(1), Materials.Tin.getFluid(1));
            TinkerRegistry.registerAlloy(material = Materials.Invar.getFluid(3), Materials.WroughtIron.getFluid(2), Materials.Nickel.getFluid(1));
            TinkerRegistry.registerAlloy(material = Materials.BatteryAlloy.getFluid(5), Materials.Lead.getFluid(4), Materials.Antimony.getFluid(1));
            TinkerRegistry.registerAlloy(material = Materials.SolderingAlloy.getFluid(10), Materials.Tin.getFluid(9), Materials.Antimony.getFluid(1));
            TinkerRegistry.registerAlloy(material = Materials.Magnalium.getFluid(3), Materials.Aluminium.getFluid(2), Materials.Magnesium.getFluid(1));
            TinkerRegistry.registerAlloy(material = Materials.CobaltBrass.getFluid(9), Materials.Brass.getFluid(7), Materials.Aluminium.getFluid(1), Materials.Cobalt.getFluid(1));

        } catch (NullPointerException ignored) {
            String name;
            if (material == null) name = Materials.Brass.toString().split("\\.")[1];
            else name = material.getFluid().getUnlocalizedName().split("\\.")[1];

            GFLog.ticonLogger.error("Failed to register alloy recipe for material {}", name);
        }
    }
}
