<table>
<tbody>
<tr>
<td><strong>Service</strong></td>
<td><h2>IGamePluginService</h2></td>
</tr>
<tr>
<td><Strong>Operation</strong></td>
    <td>

```java
void start(GameData gameData, World world)
void stop(GameData gameData, World world)
```
</td>
</tr>
<tr>
<td><Strong>Description</strong></td>
<td> This is called to Start and Stop the game plugin, where start means add the entity to the world of game and stop means remove the entity from the world of game </td>
</tr>
<tr>
<td><Strong>Parameters</strong></td>
<td>

`GameData gameData` - The gameData of the game

`World world` - The world of the game

</td>
</tr>
<tr>
<td><Strong>PreConditions</strong></td>
<td>
<h3>Start</h3>  
The game has started and the entity plugin has not yet been started/ added to the game


<h3>Stop</h3>

The game plugin is not stopped/removed from the game and the game plugin has been started.

</td>
</tr>
<tr>
<td><Strong>PostConditions</strong></td>
<td>
<h3>Start</h3>  

The plugin has handled the start method and added the entity to the world of game.

<h3>Stop</h3>

The plugin has handled the stop method and removed the entity from the world of game.

</td>
</tr>
</tbody>
</table>







