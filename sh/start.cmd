echo off
call gradlew.bat clean
IF "%1"=="OBF" (
  echo "Obfuscation"
  call gradlew.bat war_obf
  call docker build -t chess-game . --build-arg OBF_SUFFIX=_obf
) ELSE (
  echo "No obfuscation"
  call gradlew.bat war
  call docker build -t chess-game .
)
call docker-compose up -d
