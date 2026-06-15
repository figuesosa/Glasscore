@echo off
title Glasscore
cd /d "%~dp0"

if not exist "config.properties" (
    echo Falta config.properties en esta carpeta.
    pause
    exit /b 1
)

set JAR=target\Glasscore.jar

if not exist "%JAR%" (
    echo.
    echo No se encontro el archivo compilado.
    echo Abra el proyecto en NetBeans y use Clean and Build.
    echo.
    pause
    exit /b 1
)

java -jar "%JAR%"
if errorlevel 1 (
    echo.
    echo Error al ejecutar. Verifique que Java este instalado.
    pause
)
