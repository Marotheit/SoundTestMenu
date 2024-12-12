# SoundTestMenu
![GitHub Latest Release](https://img.shields.io/github/v/release/Marotheit/SoundTestMenu)
### A sound test menu plugin, useful for easily testing in-game sounds.
This project would not be possible without [Firestone82](https://github.com/Firestone82/PlaySoundTester). He did all the heavy lifting, I have simply updated the plugin for my personal needs.

This plugin adds a Sound Test Menu with a functioning GUI to test sounds in-game, which can be used to quickly hear sounds instead of manually using the `/playsound` to test sounds one by one.
<br /><br />

## How to Compile

```bash
# Test gradle to ensure your environment is acceptable.
./gradlew test
```
```bash
# Build plugin jar file; The destination directory for the plugin file will be `<Project Location>\build\libs\`.
./gradlew build
```

This plugin was designed to be reliable and lightweight. It does not use any non-public APIs.
Please report any issues, and I will see what I can remedy.
<br /><br />

## Permissions
* ##### `soundtest.menu` allows the player to use `/soundtestmenu` or `/stm`.