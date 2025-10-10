# 💻 Taller N°2 - Programación Orientada a Objetos  
**II Semestre - 2025 | ITI - ICCI**

---

## 👨‍🏫 Docentes
- Alejandro Paolini Godoy  
- Cristhian Rabi Reyes  

## 👨‍💻 Ayudantes
- Daniel Durán García  
- Nicolás Rojas Bustos  

---

## 👥 Integrantes
- Ninibeth Pérez Cortés - 21.787.686-9 - ICCI - usuario GitHub: papaFritaUwU  
- Rodrigo Reyes Alfaro - 22.123.808-7 - ICCI - usuario GitHub: rodrigoreyes-bit  

---

## 🧠 Descripción del Taller
Este proyecto simula una **herramienta de análisis y monitoreo de red** para la empresa ficticia *SecureNet Ltda.*, desarrollada por *EclipSec* como medida preventiva ante intentos de acceso no autorizados.

El sistema fue implementado en **Java con Programación Orientada a Objetos (POO)** y permite:
- Gestionar PCs y sus puertos asociados.
- Analizar vulnerabilidades.
- Simular escaneos de red.
- Generar reportes de auditoría en un archivo `.txt`.
- Ofrecer menús diferenciados para **Administrador** y **Usuario**.

---

## 📂 Archivos incluidos

- `pcs.txt` → Lista de PCs de la red (ID, IP, Sistema Operativo).  
- `puertos.txt` → Estado de los puertos (ID, número, estado).  
- `usuarios.txt` → Usuarios del sistema con contraseñas hasheadas (SHA-256 + Base64).  
- `vulnerabilidades.txt` → Vulnerabilidades asociadas a cada puerto.  
- `reportes.txt` → Archivo generado con los resultados de los escaneos.
- `Diagrama de clase Taller2.drawio.pdf` → Diagrama de clases del código.
- `Diagrama de dominio Taller 2.drawio.pdf` → Diagrama de dominio del código.
- Carpeta `src/` → Código fuente Java del sistema.

---

## 🧩 Clases principales

| Clase | Descripción |
|-------|--------------|
| `Pc` | Representa un equipo dentro de la red con sus puertos. |
| `Puerto` | Almacena el número, estado y vulnerabilidades asociadas. |
| `Vulnerabilidad` | Describe una vulnerabilidad vinculada a un puerto. |
| `Usuario` | Representa a los usuarios del sistema (Admin o User). |
| `Main` | Clase principal que gestiona la lectura de archivos, login y menús. |

---

## ✅ Notas finales
- Proyecto implementado completamente en **Java con POO**.  
- Código comentado y estructurado en clases y métodos según el modelo de dominio.  
- Cumple con todos los requerimientos especificados en el enunciado del taller.
