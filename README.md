# Awesome

> Modularized Vanilla++ & machines mods for Minecraft.

![Minecraft: 1.19.2](https://img.shields.io/badge/minecraft-1.19.2-637f40?style=for-the-badge)
![Mod loader: Fabric](https://img.shields.io/badge/modloader-fabric-926c4d?style=for-the-badge)
![Language: Kotlin](https://img.shields.io/badge/language-kotlin-a97bff?style=for-the-badge)
[![build](https://img.shields.io/github/workflow/status/shkschneider/mc_awesome/build?style=for-the-badge)](https://github.com/shkschneider/mc_awesome/actions/workflows/build.yml)
![Tag](https://img.shields.io/github/v/tag/shkschneider/mc_awesome?style=for-the-badge)
[![license](https://img.shields.io/github/license/shkschneider/mc_awesome?style=for-the-badge)](https://github.com/shkschneider/mc_awesome/LICENSE)

## Modules

- Awesome **Core** (required!)
- Awesome **Extras**
  - `/gamerule keepXp` upon death
  - pvp
  - sleeping heals
  - villagers follow emerald blocks
  - villagers infinite trading
- Awesome **Commands**
  - `/fly` `/invulnerable`
  - `/spawn` `/top`
  - `/sethome` `/home`
  - `/back`
- Awesome **Enchantments**
  - experience (xp from breaking ore blocks)
  - ice & poison aspects
  - infinity bow requires no arrow
  - magnetism
  - silk touch spawners
  - vein mining (ores and logs)
  - potions: experience (1 to 9 levels)
- Awesome **Machines**
  - collector
  - crafter
  - ore processing...
  - worldgen: randomium (random ore & teleports)
  - item: imprisoner (to capture and release entities)
  - item: prospector (to make ores glow)

## Configuration

Almost everything is configurable via `config/awesome.json`.

## Ore Processing

- Base: 1ingot (crushing) 1dust (smelting) 1ingot
- Tier0: 1raw (smelting) 1ingot
- Tier1: 1raw (crushing) 4powder (crushing) 2dust (smelting) 2ingot
- Tier2: 1raw (crushing) 4powder (infusing) 4dust (smelting) 4ingot
- Tier3: 1ore (refining) 16chip (crushing) 8powder (infusing) 8dust (smelting) 8ingot

## Recipes

- 4x more torches using coal dusts
- end portal frame
- Optional: 1 coal block (crushing) 1 diamond dust (smelting) 1diamond
- Optional: 1 cobblestone (crushing) 1 gravel (crushing) 1sand
- Optional: 1 randomium ore (smelting) 1 redstone flux
- Optional: 64 netherrack (crushing) 9 redstone (dust)

## License & Credits

This project is licensed under **MIT**.
Made by ShkSchneider.

- Special thanks to *Kaupenjoe*: https://github.com/Tutorials-By-Kaupenjoe
- Most ores textures are from *GregTech* under **LGPL3**: https://github.com/GregTechCE/GregTech
- Most machines frames are from *Industrial Foregoing* under **MIT**: https://github.com/InnovativeOnlineIndustries/Industrial-Foregoing

- Logo based off https://www.iconfinder.com/icons/185592/table_crafting_icon
- Imprisoner based off https://pngimg.com/imgs/fantasy/pokeball/
- Prospector based off https://www.iconfinder.com/icons/9044628/machine_learning_icon
