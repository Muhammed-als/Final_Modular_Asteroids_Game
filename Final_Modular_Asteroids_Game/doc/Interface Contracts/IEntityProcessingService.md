<table>
<tbody>
<tr>
<td><strong>Service</strong></td>
<td><h2>IEntityProcessingService</h2></td>
</tr>
<tr>
<td><Strong>Operation</strong></td>
    <td>

```java
void process(GameData gameData, World world);
```
</td>
</tr>
<tr>
<td><Strong>Description</strong></td>
<td>It is the main processing system, that updates the entities' gameData continuously.
The method overrides when implementing the IEntityProcessingService interface.  </td>
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
The gameData and world has been initialized. To be able to process the system, a complete game loop has been completed, which means that the loop should contains all the entities processing services, so that the whole system will be processed


</td>
</tr>
<tr>
<td><Strong>PostConditions</strong></td>
<td>
The system has been processed


</td>
</tr>
</tbody>
</table>







