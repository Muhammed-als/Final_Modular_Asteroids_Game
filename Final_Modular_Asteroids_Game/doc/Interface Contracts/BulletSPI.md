<table>
<tbody>
<tr>
<td><strong>Service</strong></td>
<td><h2>BulletSPI</h2></td>
</tr>
<tr>
<td><Strong>Operation</strong></td>
    <td>

```java
Entity createBullet(Entity e, GameData gameData);
```
</td>
</tr>
<tr>
<td><Strong>Description</strong></td>
<td>
Creates a bullet for the entity. The method overrides when implementing the BulletSPI interface. 
</td>
</tr>
<tr>
<td><Strong>Parameters</strong></td>
<td>

`Entity e` - The entity that will be used to create the bullet

`GameData gameData` - The gameData of the game


</td>
</tr>
<tr>
<td><Strong>PreConditions</strong></td>
<td>
The system do have the entity that will be used to create the bullet, and the gameData has been initialized. 

</td>
</tr>
<tr>
<td><Strong>PostConditions</strong></td>
<td>
The bullet has been created


</td>
</tr>
</tbody>
</table>







