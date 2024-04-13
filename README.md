# SoundTestMenu
### A sound test menu plugin, useful for easily testing in-game sounds.
This project would not be possible without [Firestone82](https://github.com/Firestone82/PlaySoundTester). He did all the heavy lifting, I have simply updated the plugin.
<br /><br />

## How to Compile in IntelliJ

1. Import the plugin into IntelliJ.

2. Right-Click `pom.xml` in the Project Explorer and select `Maven -> Download Sources` to ensure you have all sources downloaded.

3. Press `Ctrl+Alt+Shift+S` to open the `Project Structure` window.

4. Select the `Artifacts` tab under the `Project Settings` list, and add a new JAR artifact that is built from modules with dependencies.

5. Be sure to add a copy of `src/main/resources/plugin.yml` to the output file so that the plugin registers commands.

6. You can change the output directory if you desire. This location is where the plugin will be compiled to.

7. Select `Build -> Build Artifacts...` from the toolbar at the top to build the plugin.

Please report any issues, and I will see what I can remedy.
<br /><br />

## Permissions
* ##### `soundtest.menu` allows the player to use `/soundtestmenu` or `/stm`.