package gregification.util;

import com.google.common.base.CaseFormat;
import gregtech.api.unification.material.type.Material;
import net.minecraft.util.ResourceLocation;

import java.util.function.Predicate;

public class GFUtility {

    public static ResourceLocation gregificationId(String name) {
        return new ResourceLocation(GFValues.MODID, name);
    }

    public static Predicate<Material> pred(Predicate<Material> in) {
        return in;
    }

    public static String toUpperCamel(String string) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, string);
    }
}
