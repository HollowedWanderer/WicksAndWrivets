Wicks And Wrivets
=================

This is a Minecraft mod created for Modfest 2024.


## Development Setup
1. Ideally, put Axiom or whatever you like for building into `run/mods`.
2. Create the symlink, for example(run this in cmd, now powershell): `mklink /D "D:\Code\Minecraft\WicksAndWrivets\src\main\resources\dimension_data" "D:\Code\Minecraft\WicksAndWrivets\run\saves\DevWorld\dimensions\wikwriv"`
3. Make some changes in the dev world
4. Make sure that they actually get commited. Maybe this can help: `git config core.symlinks true`

How to get into the dimension:
```
/execute in wikwriv:wikwriv run tp 0 1 0
```