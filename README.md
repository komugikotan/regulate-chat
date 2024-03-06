# Regulate-Chat

This is a plugin that allows you to limit the maximum amount of characters that can be used in a chat. 

## Requirements

This plugin will works with

・Spigot/Paper 1.18 or newer
・JDK 17 (Tested with Amazon Corretto 17)

## Usage

Put plugin jar into ```plugins``` folder. It will automatically generate config file when server has launched.

If you want to change plugin language to English (the default is Japanese), delete or rename ```config.yml``` and rename ```config-en.yml``` to ```config.yml```.

## Commands

### ```/regulatechat max <number>```

You can change the maximum amount of characters that can be used in a chat.

### ```/regulatechat reload```

Reloads config file.

