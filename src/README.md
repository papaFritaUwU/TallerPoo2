# 🧭💡 Proyecto TallerPoo2

## 🚀 Comandos básicos para trabajar con Git y GitHub
Antes que todo hay que guardar los cambios en el vsc con el ctrl + s

### 🔹 1️⃣ Ver el estado actual del proyecto
```bash
git status
```
Muestra qué cambió desde el último commit.

###🔹 2️⃣ Agregar todos los cambios nuevos o modificados
```bash
git add .
```
El punto (.) agrega todos los archivos cambiados.

###🔹 3️⃣ Guardar los cambios en un commit
```bash
git commit -m "Describe brevemente lo que hiciste"
```

Ejemplo:
git commit -m "Agregué validaciones en Usuario.java"

###🔹 4️⃣ Subir los cambios a GitHub
```bash
git push
```

Envía tus commits locales al repositorio remoto (origin/main).

###🔹 5️⃣ Traer cambios nuevos desde GitHub (hechos por ti o tu compañero)
```bash
git pull origin main
```

Actualiza tu copia local con la versión más reciente del repositorio remoto.

###🔹 6️⃣ Ver el historial de commits (resumen)
```bash
git log --oneline
```

###🔹 7️⃣ Ver qué remoto está vinculado
```bash
git remote -v
```

🧑‍🤝‍🧑 Si trabajas con otra persona
🪄 Primera vez (solo una vez)

Tu compañero debe clonar el repo:
```bash
git clone https://github.com/papaFritaUwU/TallerPoo2.git
cd TallerPoo2
```

###🔹 Antes de modificar
```bash
git pull origin main
```

###🔹 Después de modificar
```bash
git add .
git commit -m "Cambios hechos por [nombre]"
git push
```


###⚠️ Si aparecen conflictos:
```bash
git pull origin main
```
# (resolver conflictos en VS Code)
```bash
git add .
git commit -m "Resolví conflictos"
git push
```

###💾 5 Comandos mágicos (resumen)
```bash
git status
git add .
git commit -m "mensaje"
git pull origin main
git push
```
💬 Consejitos útiles
💡 Guarda tus archivos antes de hacer git add.
🚫 No subas carpetas pesadas ni archivos sensibles (.env, node_modules, etc.) → usa .gitignore.
📁 Si trabajas en OneDrive y Git se comporta raro, mueve tu carpeta a:

C:\Users\<tuUsuario>\Documents\TallerPoo2

🧠 Usa mensajes de commit claros para identificar los cambios fácilmente.
🌐 Si cambias de compu, solo necesitas clonar el repo y listo.
:3