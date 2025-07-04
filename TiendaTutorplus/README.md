🎓 TiendaTutorplus

**Tutor+** es una plataforma web educativa que conecta estudiantes con tutores escolares calificados, diseñada como parte del curso *Desarrollo de Aplicaciones Web y Patrones* de la Universidad Fidélitas en 2025.

---

Justificación del proyecto

En la actualidad, estudiantes y padres enfrentan dificultades al buscar tutores confiables, que ofrezcan flexibilidad de horarios y métodos de enseñanza adaptados a sus necesidades. 
**Tutor+** surge como una solución accesible, escalable y confiable que permite:

- Acceder a una base de tutores verificados.
- Registrar nuevos tutores en línea.
- Explorar contenido educativo, recursos y testimonios.
- Contactar directamente a tutores desde la plataforma.

---

🌐 Objetivos del sistema

- Ofrecer un entorno web amigable y confiable para conectar tutores y estudiantes.
- Integrar múltiples vistas informativas y formularios funcionales.
- Aplicar buenas prácticas de desarrollo web (separación de lógica, uso de plantillas, MVC, responsive design).
- Facilitar el despliegue y mantenimiento del sistema mediante Spring Boot y Thymeleaf.

---

🚀 Funcionalidades destacadas

| Categoría        | Descripción                                                                 |
|------------------|-----------------------------------------------------------------------------|
| 🧑‍🏫 Tutoría        | Registro y búsqueda de tutores disponibles.                                 |
| 💬 Comunicación   | Formulario de contacto y sección de testimonios.                           |
| 📚 Información    | Secciones como “Quiénes somos”, “Preguntas frecuentes”, galería y recursos. |
| 📱 Responsive     | Diseño adaptable a dispositivos móviles usando Bootstrap.                  |
| ✅ Validaciones   | Campos con validación en los formularios usando Thymeleaf y mensajes visuales.|

---

Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3**
- **Thymeleaf**
- **Bootstrap 5**
- **HTML5 / CSS3 / JavaScript**
- **Maven**
- **Git y GitHub**

---

Flujo de navegación del usuario

INICIO
 ├── Buscar tutor        → /buscar
 ├── Tutores Destacados  → /testimonios
 ├── Materias Disponibles → /galeria
 ├── Planes y Costos     → /preguntas
 ├── Contáctanos         → /contacto
 ├── Registrarse         → /registro
 └── Quiénes somos       → /nosotros

---

📂 Estructura del proyecto

src/
├── main/
│   ├── java/com/tutorplus/controller/
│   │   ├── InicioController.java         ← Controla página de inicio
│   │   ├── InfoController.java           ← Controla páginas informativas
│   │   └── FormularioController.java     ← Controla formularios y entrada de datos
│   │
│   └── resources/
│       ├── templates/
│       │   ├── plantilla.html            ← Plantilla común (menú + pie)
│       │   ├── index.html                ← Página de bienvenida
│       │   ├── nosotros.html             ← Quiénes somos
│       │   ├── preguntas.html            ← FAQ
│       │   ├── galeria.html              ← Galería de imágenes
│       │   ├── contacto.html             ← Formulario de contacto
│       │   ├── registro.html             ← Formulario de registro de tutor
│       │   ├── buscar.html               ← Página de búsqueda
│       │   └── testimonios.html          ← Opiniones y recomendaciones
│       │
│       └── static/
│           ├── css/
│           ├── js/
│           └── images/

---

👥 Equipo de desarrollo

| Nombre     | Rol en el proyecto                                                                   |
| ---------- | ------------------------------------------------------------------------------------ |
| Álvaro Ricardo Monge Guzman | Diseño base, página principal, estructura HTML y CSS                                 |
| Jhoel Mauricio Quesada Masis | Desarrollo de secciones informativas (nosotros, preguntas, galería)                  |
| Andy Josue Rodriguez Rodriguez | Formularios: registro, búsqueda, contacto, testimonios                               |
| Bernal Jesús Herrera Arias | Integración de vistas, pruebas funcionales, validación, documentación y presentación |

---

Verificación funcional
Durante la fase de integración, se realizó una validación exhaustiva:
✔ Navegación entre secciones sin errores (rutas válidas)
✔ Formularios funcionales con validación visual
✔ Carga correcta de th:replace con plantilla.html
✔ Visualización óptima en escritorio y móviles
✔ Sin errores en consola del navegador ni del backend

---

🔧 Cómo ejecutar el proyecto
Requisitos:
Java 17
Maven 3+
IDE compatible con Spring Boot (NetBeans, IntelliJ, VS Code)

Instrucciones:
git clone https://github.com/tuusuario/tutortienda.git
cd tutortienda
mvn spring-boot:run

Abre en el navegador:
http://localhost:8080

🛠️ Participación en GitHub
Cada miembro del equipo realizó pulls significativos de código relacionados a sus tareas asignadas. 
El control de versiones se manejó mediante GitHub, usando ramas de desarrollo (opcional) y commits explicativos por cada avance.

Ejemplo de commits válidos:

feat: agregar sección de preguntas frecuentes
fix: corregir validación en formulario de contacto
style: mejorar diseño responsivo en plantilla.html

Posibles mejoras futuras
- Conexión a base de datos y almacenamiento de tutores.
- Filtrado de búsqueda por materia o zona.
- Panel de administrador para aprobar o revisar tutores.
- Integración con Google Maps o Zoom/Microsoft Teams.
- Repositorio de recursos educativos descargables.

🧑‍💻 ¿Cómo contribuir?
Este repositorio fue creado para uso académico. Si deseas reutilizar o expandir este proyecto:
1. Haz un fork del repositorio
2. Clona tu fork localmente
3. Crea una rama con tus cambios: git checkout -b mejora-x
4. Realiza tus cambios y commit
5. Sube tus cambios y haz un Pull Request

---

📩 Contacto
Este proyecto fue creado como entrega final del curso Desarrollo de Aplicaciones Web y Patrones. Para contacto académico:

Universidad Fidélitas – Ingeniería en Sistemas
Año: 2025

📄 Licencia
Este proyecto se distribuye con fines académicos y no comerciales. Derechos reservados © 2025 por el equipo de Tutor+.