# MvpMockitoWeatherApp
# Mockito Unit test Examples. and MVP pattern with WeatherApp ( JARVIS )
---

## Getting Started
### Prerequisites
1) Install Java
    - [Direct Link](https://www.java.com/en/download/help/mac_install.xml)
                        (or)
    - using homebrew - ```brew cask install java``` 

2) Install Android Studio
    - [Direct Link](https://developer.android.com/studio/index.html)
                        (or)
    - using homebrew - ```brew cask install android-studio```  
    
3) Install Android SDK
    - Install it inside android studio ( Tools -> Android -> SDK Manager )
                        (or)
    - using homebrew - ```brew cask install android-sdk```
    
4) Install gradle
    - [Direct Link](https://gradle.org/install/)
                        (or)
    - ```brew install gradle```

5) Configuring path variable
    ```
    export ANDROID_HOME=$HOME/Library/Android/sdk
    export PATH=$ANDROID_HOME/tools:$PATH 
    export PATH=$ANDROID_HOME/platform-tools:$PATH
    ```
    Add these three to ~/.zshrc file and run ```source ~/.zshrc```
    
### Clone the repository
```git clone https://github.com/vrushaliraut/MvpMockitoWeatherApp.git```

###  Slides you can refer for more understanding and go point by point
``` https://speakerdeck.com/vrushaliraut/unit-test-with-mockito ```

## Build Types, Flavours, Dimensions

Build Types - Environment
1) Debug


Flavours:

Flavours on Country
2) en

```
### Android Requirements
* minSdkVersion = 15
* targetSdkVersion = 26
* compileSdkVersion = 26

