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

import gregtech.api.unification.material.IMaterialHandler;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;

import static gregtech.api.unification.material.type.DustMaterial.MatFlags.SMELT_INTO_FLUID;

@IMaterialHandler.RegisterMaterialHandler
@SuppressWarnings("unused")
public class TinkersMaterialHandler implements IMaterialHandler {

    // TODO Why are we doing this?
    @Override
    public void onMaterialsInit() {
        for (Material mat : Material.MATERIAL_REGISTRY) {
            if (mat instanceof DustMaterial) {
                if (mat instanceof IngotMaterial) {
                    IngotMaterial material = (IngotMaterial) mat;
                    if (material.blastFurnaceTemperature > 0)
                        material.setFluidTemperature(material.blastFurnaceTemperature);
                } else if (mat.hasFlag(SMELT_INTO_FLUID)) {
                    ((DustMaterial) mat).setFluidTemperature(500);
                }
            }
        }
    }
}
