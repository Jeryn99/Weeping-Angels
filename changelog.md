  <img width="80%" src="https://raw.githubusercontent.com/WhoCraft/Weeping-Angels/1.16/src/main/resources/logo.png" alt="Logo">

**Version 1.21-1.0.5**

**Fixes:**
- Fixed a server crash (`IllegalAccessError` in `BlockReactions`) when angels break blocks on NeoForge - the access widener is now converted to an access transformer (#433)
- Fixed a crash (`ConcurrentModificationException`) when the donator list refreshed while players were rendering (#432)
- Donator lookup no longer runs on the render thread, so a slow or offline server can't freeze the game (#417)
- Fixed the donator list duplicating itself on every refresh, which slowly dropped FPS the longer the game ran (#420)
- Fixed angels never teleporting players - they now find a safe spot, and attack instead of doing nothing if none is found (#421)
- Pickaxes from mods like Tinkers' Construct, Tetra and Silent Gear can now hurt angels (#427)
- Fixed dirt angels being impossible to kill with a pickaxe (#429)
- Fixed quartz, mossy, basalt and ore angels turning into stone angels after the first natural spawn
- Fixed angels never spawning naturally on NeoForge
- Music discs and angel sounds now get quieter with distance instead of playing at full volume everywhere (#411)
- Fabric: the mod now lists its dependencies, so a missing Forge Config API Port shows a clear message instead of crashing (#423)

**New config options:**
- `max_nearby` / `nearby_radius` - stops natural spawning once too many angels are close together (default 6 within 48 blocks) (#431, #415, #387)
- `spawn_dimension_blacklist` - dimensions angels will never spawn in naturally (#431)
- `snow_angels` - turn off Snow Angel generation (#424)
- `angel_theft` - turn off angels stealing items (#422)
- `donator_lookup` (client) - turn off the online donator lookup (#417)