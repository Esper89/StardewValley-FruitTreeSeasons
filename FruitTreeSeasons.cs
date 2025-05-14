using System.Reflection.Emit;
using StardewModdingAPI;
using HarmonyLib;
using StardewValley;
using StardewValley.TerrainFeatures;

namespace FruitTreeSeasons;

internal sealed class Mod : StardewModdingAPI.Mod
{
    public override void Entry(IModHelper helper)
    {
        Mod.instance = this;
        this.config = helper.ReadConfig<Config>();

        var harmony = new Harmony(this.ModManifest.UniqueID);
        harmony.Patch(
            original: AccessTools.Method(typeof(FruitTree), nameof(FruitTree.GetCosmeticSeason)),
            postfix: new HarmonyMethod(typeof(Mod), nameof(Mod.patch_FruitTree_GetCosmeticSeason))
        );
        harmony.Patch(
            original: AccessTools.Method(typeof(FruitTree), nameof(FruitTree.IsWinterTreeHere)),
            postfix: new HarmonyMethod(typeof(Mod), nameof(Mod.patch_FruitTree_IsWinterTreeHere))
        );
        harmony.Patch(
            original: AccessTools.Method(typeof(FruitTree), nameof(FruitTree.draw)),
            transpiler: new HarmonyMethod(typeof(Mod), nameof(Mod.transpile_FruitTree_draw))
        );
    }

    private static Mod? instance;
    private Config? config;

    private static void patch_FruitTree_GetCosmeticSeason(FruitTree __instance, ref Season __result)
    {
        if (__instance.IgnoresSeasonsHere())
        {
            var config = Mod.instance!.config!;

            if (!__instance.Location.IsOutdoors && !config.Indoors) return;
            if (__instance.Location.IsOutdoors && !config.Outdoors) return;

            var seasons = __instance.GetData()?.Seasons;
            foreach (var season in config.Seasons)
            {
                if (seasons?.Contains(season) ?? false)
                {
                    __result = config.MapSeasons.GetValueOrDefault(season, season);
                    return;
                }
            }
        }
    }

    private static void patch_FruitTree_IsWinterTreeHere(FruitTree __instance, ref bool __result)
        => __result = __instance.GetCosmeticSeason() == Season.Winter;

    private static IEnumerable<CodeInstruction> transpile_FruitTree_draw(
        IEnumerable<CodeInstruction> instructions
    ) => new CodeMatcher(instructions)
        .MatchEndForward([
            new(OpCodes.Call, AccessTools.Method(
                typeof(Game1), nameof(Game1.GetSeasonIndexForLocation)
            ))
        ])
        .ThrowIfNotMatch(
            $"Could not transpile {typeof(FruitTree)}.{nameof(FruitTree.draw)}: method does not " +
            $"call {typeof(Game1)}.{nameof(Game1.GetSeasonIndexForLocation)}"
        )
        .Advance(1)
        .Insert([
            new(OpCodes.Pop),
            new(OpCodes.Ldarg_0),
            new(OpCodes.Call, AccessTools.Method(
                typeof(Mod), nameof(Mod.patch_FruitTree_draw_GetSeasonIndexForLocation)
            ))
        ])
        .InstructionEnumeration();

    private static int patch_FruitTree_draw_GetSeasonIndexForLocation(FruitTree tree)
        => (int)tree.GetCosmeticSeason();
}

internal sealed class Config
{
    public bool Indoors { get; set; } = true;

    public bool Outdoors { get; set; } = false;

    public Season[] Seasons { get; set; } = [
        Season.Summer, Season.Spring, Season.Fall, Season.Winter
    ];

    public Dictionary<Season, Season> MapSeasons { get; set; } = new()
    {
        [Season.Winter] = Season.Spring
    };
}
