# Awesome

![Minecraft: 1.19.2](https://img.shields.io/badge/minecraft-1.19.2-637f40?style=for-the-badge)
![Mod loader: Fabric](https://img.shields.io/badge/modloader-fabric-926c4d?style=for-the-badge)
![Language: Kotlin](https://img.shields.io/badge/language-kotlin-A97BFF?style=for-the-badge)

_No dependency._

[![build](https://github.com/shkschneider/mc_awesome/actions/workflows/build.yml/badge.svg)](https://github.com/shkschneider/mc_awesome/actions/workflows/build.yml)

- Awesome Commands
  - `/fly`
  - `/sethome` `/home` `/back`
- Awesome Enchantments
  - experience (xp from breaking blocks)
  - ice & poison aspects
  - magnetism
  - vein mining (ores and logs)
- Awesome GameRules
  - keepXp
  - oreXp (xp from mining ores)
  - pvp
  - sleepingHeals
- Awesome Machines
  - "redstone flux" as power
  - power-over-redstone
  - ...
- Awesome Potions
  - experience (1 to 9 levels)
- Awesome WorldGen
  - randomium (random ore & teleports)
- Miscellaneous
  - black background splashscreen
  - bow's inifinity requires no arrow
  - silk touch spawners
  - villagers follow emerald blocks
  - villagers infinite trading

## Configuration

Almost everything is configurable:
- at compilation in `Awesone.Config`
- at runtime in `config/awesome.json`

## Ore Processing

- Base: 1ingot (crushing) 1dust (smelting) 1ingot
- Tier0: 1raw (smelting) 1ingot
- Tier1: 1raw (crushing) 4powder (crushing) 2dust (smelting) 2ingot
- Tier2: 1raw (crushing) 4powder (infusing) 4dust (smelting) 4ingot
- Tier3: 1ore (refining) 16chip (crushing) 8powder (infusing) 8dust (smelting) 8ingot
- Bonus: 64netherrack (crushing) 9redstone
- Bonus: 1coalblock (crushing) 1diamonddust (smelting) 1diamond
- Bonus: 1randomiumore (smelting) 1redstoneflux

## License

This project is licensed under MIT.

- Most ores textures are from GregTech under LGPL3: https://github.com/GregTechCE/GregTech
- Special thanks to Kaupenjoe: https://github.com/Tutorials-By-Kaupenjoe

### Resources

- https://www.iconfinder.com/icons/185599/3d_dirt_icon
- https://pngimg.com/imgs/fantasy/pokeball/
- GregTech 5 & CE
