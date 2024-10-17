Wicks And Wrivets
=================

This is a Minecraft mod created for Modfest 2024.


## Development Setup
1. Ideally, put Axiom or whatever you like for building into `run/mods`.
2. Run once and create a world *with "dev"* in the name. <br>
   MAKE SURE THAT THE DATA GOT FROM `src/main/resources/dimension_data` COPIED INTO ITS FOLDER!!!. 
3. Delete `src/main/resources/dimension_data`.
4. Create a link between that folder and the `src/main/resources/dimension_data` folder.  <br>
   Sample Windows Command(You need to change the directories): `mklink /J 
   "D:\Code\Minecraft\WicksAndWrivets\src\main\resources\dimension_data"  
   "D:\Code\Minecraft\WicksAndWrivets\run\saves\DevWorld\dimensions\wikwriv"`

How to get into the dimension:
```
/execute in wikwriv:wikwriv run tp 0 1 0
```