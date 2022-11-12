# Machines

## Power

Machines require power to operate.
Power comes from redstone flux placed inside a generator.
Redstone flux is made out of mixed redstone and lapis dust then smelted.

- use vanilla furnace to smelt lapis into dust
- use vanilla crafting table to craft redstone flux dust
- use vanilla furnace to get redstone flux
- craft the generator
- craft more redstone flux to fuel the generator

RedstoneFlux provides twice as much power as Coal.
It ignites for 20t than instantly provides 3200 of power.

## Machines

Get started (with limited 1:1 ratios) using vanilla blocks.
Then ramp up to 1:2, 1:4 and 1:8 ore processing!

- Generator: outputs power from redstone flux
- Refinery: outputs ore chips
- Crusher: outputs ore powders/dusts
- Infuser: outputs redstone flux
- Smelter outputs ingots/gems

## Ore Processing

### 1:1

Get a smelter to process your ores using redstone flux instead of regular vanilla fuel.
The output ratio is the same as vanilla's, however.

```
smelter
```

### 1:2

Get two crushers:
- the first will output 4 ore powders for every raw ore
- the second will output 1 ore dust for every 2 ore powders

```
1 raw ore:
crusher -> 4 ore powders
crusher -> 2 ore dusts
smelter -> 2 ingots/gems
```

### 1:4

Get an infuser to output 1 dust for every powder (double the crusher's ratio).

```
1 raw ore:
crusher -> 4 ore powders
infuser -> 4 ore dusts
smelter -> 4 ingots/gems
```

### 1:8

Get a refinery to output 16 ore chips for every raw ore.
The crusher can then output 1 ore powder for every 2 ore chips.

```
1 raw ore:
refinery -> 16 ore chips
crusher -> 8 ore powders
infuser -> 8 ore dusts
smelter -> 8 ingots/gems
```

### Bonus

Enabled by default, but configurable:
- 64 netherrack -> crusher -> 9 redstone (dust)
- 1 coal block -> crusher -> 1 diamond dust
- 1 randomium ore block (silk touched) -> smelter -> redstone flux
