package gregification.config;

import net.minecraftforge.common.config.Config;

public class TiConCfg {

    @Config.Comment("Enable Tinker's Construct integration. Default: true")
    @Config.RequiresMcRestart
    public boolean enableTinkers = true;

    @Config.Comment("Add Tools with GT Metals to Tinkers. Default: true")
    @Config.RequiresMcRestart
    public boolean tinkersMetalTools = true;

    @Config.Comment("Add Tools with GT Gems to Tinkers. Default: true")
    @Config.RequiresMcRestart
    public boolean tinkersGemTools = true;

    @Config.Comment("Add Smelting for GT Materials to Tinkers Smeltery. Default: true")
    @Config.RequiresMcRestart
    public boolean tinkersMaterialsSmelting = true;

    @Config.Comment("Add Alloying of GT Materials to Tinkers Smeltery. Default: true")
    @Config.RequiresMcRestart
    public boolean tinkersMaterialAlloying = true;

    @Config.Comment("Enable Glass recipe changes. Default: true")
    @Config.Name("Greg's Construct glass processing") // TODO!!
    @Config.RequiresMcRestart
    public boolean GregsConstructGlassProcessing = true;

    @Config.Comment("Whether or not to register fluid solidification recipes for parts. Default: true")
    @Config.RequiresMcRestart
    public boolean castingRecipes = true;
}
