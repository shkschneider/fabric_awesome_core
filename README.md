# Awesome

> Modularized Vanilla++ & machines mods for Minecraft.

![Minecraft: 1.18.2](https://img.shields.io/badge/minecraft-1.18.2-637f40?style=for-the-badge)
![Mod loader: Fabric](https://img.shields.io/badge/modloader-fabric-926c4d?style=for-the-badge)
![Language: Kotlin](https://img.shields.io/badge/language-kotlin-a97bff?style=for-the-badge)
[![build](https://img.shields.io/github/actions/workflow/status/shkschneider/mc_awesome/build.yml?branch=main&style=for-the-badge)](https://github.com/shkschneider/mc_awesome/actions/workflows/build.yml)
![Tag](https://img.shields.io/github/v/tag/shkschneider/mc_awesome?style=for-the-badge)
[![license](https://img.shields.io/github/license/shkschneider/mc_awesome?style=for-the-badge)](https://github.com/shkschneider/mc_awesome/LICENSE)

Shamelessly took inspirations from the times of *Thermal Expansion*, *Greg Tech*, *Industrial Craft*, *Industrial Foregoing*...

Improved *Vanilla++* gameplay with many additions:
**crystals** that produce ores out of craftable budding blocks;
**experience** easing the gain, storage and retrieval (obelisk) of xp;
**extras** adding many content (like trash slot, rope, crate, elevator, scythe, randomium...);
even **machines**;
and finally **commands** for players, moderators and/or admins.

## Modules

- Awesome **Core** (required!)
  - Flux is a new fuel (from redstone and lapis) twice the power of Coal
  - Splash screen made black
  - Other mixins for various things
- Awesome **Crystals**
  - Same logic as Budding Amethyst but for all ores!
  - Craft budding blocks using amethyst blocks and a block of the resource
  - Grows on all sides at once
- Awesome **Experience**
  - experience enchantment that gives 1 xp per block mined
  - obelisk to store and retrieve xp
  - experience potions
- Awesome **Extras**
  - `/gamerule keepXp` upon death
  - a 2-way trash slot in your inventory that only clears upon closing
  - all-in-one tools
  - baguette: bread but better
  - configurable zenith (mid-day) and nadir (mid-night) times
  - coordinates on death screen
  - early game crates that retain inventory
  - elevators to teleport vertically
  - lily_pad that grows nearby stuff
  - player heads on death (by another player)
  - pvp on/off
  - ropes than unroll and rollup vertically for exploration
  - scythe to clear grass
  - sleeping heals
  - sponges can absorb lava
  - villagers follow emerald blocks
  - villagers infinite trading
  - void block for decoration (like the end portal)
  - worldgen: randomium (random ore & teleports)
- Awesome **Commands**
  - back
  - enderchest [player]
  - fly [player]
  - heal [player]
  - home
  - inventory [player]
  - invulnerable [player]
  - repair [player]
  - sethome
  - spawn
  - top [player]
- Awesome **Enchantments**
  - critical
  - last stand
  - magnetism
  - sixth sense
  - unbreakable (truly unbreaking)
  - vampirism
  - vein mining (ores and logs)
- Awesome **Machines**
  - collector (with efficiency)
  - crafter / recycler
  - cultivator (sapling/seed/fungus to log/food/stem)
  - detectors: weather/entity/time
  - duplicator (with efficiency) using imprisoner
  - item: imprisoner (to capture and release entities)
  - item: prospector (to make ores glow)
  - quarry (with efficiency & fortune)
  - refinery: ore processing x1-x4
- Awesome **Pack**
  - bats drop membranes, ender_dragon drops elytra...
  - infinity bow requires no arrow
  - makes many uncraftables craftable: end_portal_frame, grass, mycelium...
  - more netherite, more tnt, more wither_skeleton_skull, more shulker_shells...
  - scoreboard: deathCount (player list)
  - scoreboard: health (below name)
  - silk touch does spawners and budding_amethyst
  - upgradable tools and armors using smithing table (using blocks)

## Configuration

Almost everything is configurable via `config/awesome.json`.

## Ore Processing

- Refinery processes ores with enchantments: x1-x4
- Netherite Ingot from 1 netherite_scrap + 1 gold_block

## License & Credits

This project is licensed under **MIT**.
Made by **ShkSchneider** with great help from open-sourced codes from the community of modders.

- Special thanks to *Kaupenjoe*: https://github.com/Tutorials-By-Kaupenjoe
- Many textures are from *GregTech* under **LGPL3**: https://github.com/GregTechCE/GregTech
- Many machines frames are from *Industrial Foregoing* under **MIT**: https://github.com/InnovativeOnlineIndustries/Industrial-Foregoing

- Crate based off https://www.iconfinder.com/icons/3338964/business_tools_crate_box_wood_wood_box_icon
- Flux from **Thermal Foundation**'s *Cryotheum Dust* and *Signalum Blend*
- Herobrine was made from Mojang's Steve's
- Imprisoner from https://ftb.fandom.com/wiki/Mob_Imprisonment_Tool
- Logo based off https://www.iconfinder.com/icons/185592/table_crafting_icon
- Obelisk textures from https://github.com/Meridanus/fabric_xp_storage_1.18
- Prospector based off https://www.iconfinder.com/icons/9044628/machine_learning_icon
- Rope based off https://starbounder.org/Rope / Omelette from https://starbounder.org/Omelette
