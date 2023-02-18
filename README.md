# Awesome

> Modularized Vanilla++ & machines mods for Minecraft.

![Minecraft: 1.19.3](https://img.shields.io/badge/minecraft-1.19.3-637f40?style=for-the-badge)
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
  - all-in-one tool
  - baguette: bread but better
  - configurable zenith (mid-day) and nadir (mid-night) times
  - coordinates on death screen
  - early game crates that retain inventory
  - elevators to teleport vertically
  - extract enchantments using the grindstone
  - hero... herobrine?!
  - player heads on death (by another player)
  - pvp
  - ropes than unroll and rollup vertically for exploration
  - scythe to clear grass
  - sleeping heals
  - sponges can absorb lava
  - upgradable tools and armors using smithing table (using blocks)
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
  - ice & poison aspects
  - infinity bow requires no arrow
  - last stand
  - magnetism
  - silk touch spawners
  - sixth sense
  - unbreakable (truly unbreaking)
  - vampirism
  - vein mining (ores and logs)
- Awesome **Machines**
  - collector (with efficiency)
  - crafter / recycler
  - quarry (with efficiency & fortune)
  - refinery: ore processing x1-x4
  - item: imprisoner (to capture and release entities)
  - item: prospector (to make ores glow)

## Configuration

Almost everything is configurable via `config/awesome.json`.

## Ore Processing

- Refinery processes ores with enchantments: x1-x4
- Netherite Ingot from 1 Ancient Debris + 1 Gold Block

## License & Credits

This project is licensed under **MIT**.
Made by **ShkSchneider** with great help from open-sourced codes from the community of modders.

- Special thanks to *Kaupenjoe*: https://github.com/Tutorials-By-Kaupenjoe
- Many textures are from *GregTech* under **LGPL3**: https://github.com/GregTechCE/GregTech
- Many machines frames are from *Industrial Foregoing* under **MIT**: https://github.com/InnovativeOnlineIndustries/Industrial-Foregoing

- Logo based off https://www.iconfinder.com/icons/185592/table_crafting_icon
- Flux from **Thermal Foundation**'s *Cryotheum Dust* and *Signalum Blend*
- Imprisoner from https://ftb.fandom.com/wiki/Mob_Imprisonment_Tool
- Prospector based off https://www.iconfinder.com/icons/9044628/machine_learning_icon
- Crate based off https://www.iconfinder.com/icons/3338964/business_tools_crate_box_wood_wood_box_icon
- Rope based off https://starbounder.org/Rope
- Herobrine was made from Mojang's Steve's
