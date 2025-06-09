Fruit Tree Seasons changes how fruit trees display their seasons.

This mod makes fruit trees in greenhouses (and other indoor areas that lack seasons) take on the appearance of whichever season they bear fruit in, rather than always using their summer appearance. By default, fruit trees on Ginger Island (and other outdoor areas that lack seasons) will still use the tree's summer appearance.

This mod also fixes a bug in the appearance of the leaves that come down from fruit trees when they're shaken; instead of using leaves for the current season (regardless of the tree's appearance) the game now uses the leaf texture for the season the fruit tree is currently displayed as.

This mod should be fully compatible with mods that add custom fruit trees.

If a modded tree bears fruit in multiple seasons, the appearance chosen by default is the first one in the following list: summer, spring, fall, winter. By default, modded fruit trees that bear fruit in winter will use their spring appearance instead.

[img]https://raw.githubusercontent.com/Esper89/StardewValley-FruitTreeSeasons/refs/heads/master/.github/assets/fruit_trees.png[/img]

[size=4][b]Installation[/b][/size]

This mod requires [url=https://nexusmods.com/stardewvalley/mods/2400]SMAPI[/url].

You can download Fruit Tree Seasons from the [url=https://nexusmods.com/stardewvalley/mods/34709?tab=files]files tab[/url]. You can also download it from the [url=https://github.com/Esper89/StardewValley-FruitTreeSeasons/releases/latest]releases page[/url] of the GitHub repository, below the changelog.

To install Fruit Tree Seasons, just extract the zip file and place the [font=Courier New][b]Fruit Tree Seasons[/b][/font] folder into your [font=Courier New][b]Mods[/b][/font] folder.

[size=4][b]Configuration[/b][/size]

Running Stardew Valley with Fruit Tree Seasons installed will generate [font=Courier New][b]config.json[/b][/font] (in [font=Courier New][b]Fruit Tree Seasons[/b][/font]). You can edit this file to configure the mod; make sure to restart the game afterwards for your changes to be applied.

The [font=Courier New][b]Indoors[/b][/font] and [font=Courier New][b]Outdoors[/b][/font] fields can be set to control which locations this mod applies to. The [font=Courier New][b]Indoors[/b][/font] field determines if this mod affects fruit trees in indoor areas that lack seasons (like the greenhouse) and defaults to [font=Courier New][b]true[/b][/font]. The [font=Courier New][b]Outdoors[/b][/font]  field determines if this mod affects fruit trees in outdoor areas that lack seasons (like Ginger Island) and defaults to [font=Courier New][b]false[/b][/font].

The [font=Courier New][b]Seasons[/b][/font] field is the order to apply season appearances; the first season on this list that a fruit tree bears fruit in is the one that will apply to the tree. Fruit trees that do not bear fruit in [i]any[/i] of the listed seasons will be ignored by this mod. This field defaults to [font=Courier New][b]["Summer", "Spring", "Fall", "Winter"][/b][/font], because summer appearances are the game's default and spring appearances tend to look nicer than fall appearances. Removing seasons from this list is allowed but not recommended; it's best to just change the order.

The [font=Courier New][b]MapSeasons[/b][/font] field lets you display the wrong season for fruit trees depending on the season they bear fruit in. For example, if [font=Courier New][b]MapSeasons[/b][/font] were set to [font=Courier New][b]{ "Summer": "Winter" }[/b][/font], then any fruit tree that bears fruit in summer would display their [i]winter[/i] appearances in greenhouses. This field defaults to [font=Courier New][b]{ "Winter": "Spring" }[/b][/font], because trees without leaves don't look very nice in the greenhouse.

If you'd also rather not see fall appearances for trees in your greenhouse, just add [font=Courier New][b]"Fall": "Summer"[/b][/font] to your [font=Courier New][b]MapSeasons[/b][/font].
