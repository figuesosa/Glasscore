# Glasscore - Sistema de Vidriería

Aplicación de escritorio en **Java Swing** con base de datos **SQL Server** para gestión de empleados, usuarios, herramientas, cotizaciones y planillas.

## Requisitos

- JDK 17+
- NetBeans (recomendado)
- SQL Server Express + SSMS

## Instalación rápida

1. Instalar SQL Server Express y SSMS
2. Habilitar **TCP/IP** en puerto **1433** y **autenticación mixta**
3. En SSMS, ejecutar `SQLQuery1.sql` (conectado a `master`)
4. Abrir el proyecto en NetBeans → **Run**

Guía detallada: ver `INSTALACION.txt`

## Configuración de conexión

Editar `config.properties` en la raíz del proyecto:

```properties
servidor=127.0.0.1
puerto=1433
base_datos=db_glasscore_management
usuario=glasscore_user
contrasena=Glasscore123
```

## Ejecutar

- **NetBeans:** Run (F6)
- **JAR:** `Clean and Build` → doble clic en `INICIAR.bat`

## Módulos

| Módulo | Descripción |
|--------|-------------|
| Empleados | CRUD de plantilla |
| Usuarios | Credenciales vinculadas a empleados |
| Herramientas | Inventario y asignación |
| Cotizaciones | Cálculo de vidrio y aluminio |
| Planillas | Pagos con horas extras y viáticos |

## Base de datos

- Nombre: `db_glasscore_management`
- Usuario: `glasscore_user` / `Glasscore123`

## Compartir el proyecto

Comprimir la carpeta en ZIP (sin `target/`) e incluir `INSTALACION.txt` para tus compañeros.
