# Awesome

> Modularized Vanilla++ & machines mods for Minecraft.

![Minecraft: 1.19.3](https://img.shields.io/badge/minecraft-1.19.3-637f40?style=for-the-badge)
![Mod loader: Fabric](https://img.shields.io/badge/modloader-fabric-926c4d?style=for-the-badge)
![Language: Kotlin](https://img.shields.io/badge/language-kotlin-a97bff?style=for-the-badge)
[![build](https://img.shields.io/github/actions/workflow/status/shkschneider/mc_awesome/build.yml?branch=main&style=for-the-badge)](https://github.com/shkschneider/mc_awesome/actions/workflows/build.yml)
![Tag](https://img.shields.io/github/v/tag/shkschneider/mc_awesome?style=for-the-badge)
[![license](https://img.shields.io/github/license/shkschneider/mc_awesome?style=for-the-badge)](https://github.com/shkschneider/mc_awesome/LICENSE)

Shamelessly took inspirations from the times of *Thermal Expansion*, *Greg Tech*, *Industrial Craft*...

Evolves around Vanilla *copper*, *redstone* and *lapis lazuli* - with ingots for each!
with ore processing involving *powders*, *chips* and *dusts*,
with **Crystals** to produce ores out of craftable budding blocks,
with **Experience** easing the gain of xp,
with **Extras** giving a Vanilla++ feeling,
with **Machines** adding powerful blocks and mechanisms!

- **New!** All-In-One Tool!
- **New!** Vamprism!
- **New!** Sixth Sense that makes surrounding entities glow!
- **New!** Crystals working like Budding Amethyst producing all vanilla ores!
- **New!** Ropes that unroll and rollup, Obelisks to store and retrieve experience, Void block for decoration & more!

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
  - bonus recipes for vanilla items
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
  - `/fly` `/invulnerable` `/heal` `/repair`
  - `/spawn` `/top`
  - `/sethome` `/home`
  - `/back`
- Awesome **Enchantments**
  - ice & poison aspects
  - infinity bow requires no arrow
  - last stand
  - magnetism
  - silk touch spawners
  - unbreakable (truly unbreaking)
  - vampirism
  - vein mining (ores and logs)
- Awesome **Machines**
  - collector (with efficiency)
  - quarry (with efficiency & fortune)
  - crafter / recycler
  - ore processing x2 x4 x8!
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
- Bonus: 1ancient_debris (crushing) 1netherite_scrap+1gold_block (infusing) 1netherite_ingot

## Recipes

- Baguette: better bread!
- More torches using coal chips or dusts
- Redstone + Lapis Lazuli (+ Coal) = Flux
- Vanilla now craftable: bell, cobweb, crying obsidian, elytra, grass, mycelium, name tag, trident...
- Optional: 1 coal block (crushing) 1 diamond dust (smelting) 1diamond
- Optional: 1 cobblestone (crushing) 1 gravel (crushing) 1sand
- Optional: 64 netherrack (crushing) 9 redstone (dust)

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
- Herobrine was made from Mojang's Steve's
