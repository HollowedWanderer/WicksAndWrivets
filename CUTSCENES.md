# Cutscenes

## Execution Order
![cutscenes.png](cutscenes.png)

The execution order goes:
1. Actual Code
2. First Action
3. It's subgroup
4. Second Action
5. ...

Basically, recursive top-down execution.

## Components

### CloseWindowAction
Closes the currently open windows(including settings), for example the inventory. Also safe when there is no window open.

### FixedCameraAction
Denies control over the camera.
CURRENTLY, WHEN UNBLOCKING, ALL MOVEMENTS THAT WERE MADE WHILE BLOCKED WILL BE EXECUTED.

### ChatAction
Shows a message in the chat.

### WaitAction
Idles for a certain amount of time.

### ConsoleAction
Writes a message to the console(of the executor, and since this is client-side only, the client console).

### UnblockInputAction
Unblocks the input AND THE CAMERA of the player.
YOU SHOULD ALWAYS PUT THIS AT THE END, EVEN IF IT HAPPENS AUTOMATICALLY SOMETIMES.

### BlockInputAction
Blocks the input WITHOUT THE CAMERA of the player.
NOTE: This does not (yet) clear the already pressed keys, and neither does unblock.