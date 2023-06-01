<table>
<tbody>
<tr>
<td><strong>Use Case</strong></td>
<td>Asteroid split </td>
</tr>
<tr>
<td><Strong>Description</Strong></td>
<td>
The asteroid splits into two smaller asteroids when hits by a bullet.
</td>
</tr>
<tr>
<td><strong>Actor</strong></td>
<td>
Asteroid, Bullet
</td>
</tr>
<tr>
<td><Strong>PreConditions</Strong></td>
<td>
The asteroid has been hit by a bullet.
</td>
</tr>
<tr>
<td><strong>Flow of events</strong></td>
<td>
<ol>
<li>The asteroid has been hit by a bullet.</li>
<li>The asteroid splits into two smaller asteroids.</li>
<li>The smaller parts has been hit by a bullet again</li>
<li>The smaller parts splits into two even smaller asteroids.</li>
<li>The smaller parts has been hit by a bullet again</li>
<li>The smaller parts are removed from the game </li>
</ol>
</td>
</tr>
<tr>
<td><Strong>PostConditions</Strong></td>
<td>
The asteroid has been split into two smaller asteroids.
</td>
</tr>
<tr>
<td>Alternative flow of events</td>
<td>
<ol>
<li>The asteroid has not been hit by a bullet </li>
<li>The asteroid is not split into two smaller asteroids</li>
</ol>
</td>
</tr>
</tbody>
</table>

###
<table>
<tbody>
<tr>
<td><strong>Use Case</strong></td>
<td>Spaceship movement</td>
</tr>
<tr>
<td><Strong>Description</Strong></td>
<td>
The spaceship can move using the arrow keys.
</td>
</tr>
<tr>
<td><strong>Actor</strong></td>
<td>
Player
</td>
</tr>
<tr>
<td><Strong>PreConditions</Strong></td>
<td>
The spaceship is added to the game.
</td>
</tr>
<tr>
<td><strong>Flow of events</strong></td>
<td>
<ol>
<li>The player presses the up or down keys.</li>
<li>The spaceship moves.</li>
<li>The player presses the left or right keys.</li>
<li>The spaceship rotates left or right.</li>
</ol>
</td>
</tr>
<tr>
<td><Strong>PostConditions</Strong></td>
<td>
The spaceship has moved.
</td>
</tr>
<tr>
<td>Alternative flow of events</td>
<td>
<ol>
<li>The player does not press any keys.</li>
<li>The spaceship does not move.</li>
</ol>
</td>
</tr>
</tbody>
</table>

