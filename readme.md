[![CI/CD Pipeline - HealthTrack App](https://github.com/Jplazadelosreyes/eval_final_4/actions/workflows/test.yml/badge.svg)](https://github.com/Jplazadelosreyes/eval_final_4/actions/workflows/test.yml)

# HealthTrack: Sistema de Gestión de Salud

<div align="center">
  <img src="https://img.shields.io/badge/Java-11-orange" alt="Java 11">
  <img src="https://img.shields.io/badge/Maven-3.8+-blue" alt="Maven">
  <img src="https://img.shields.io/badge/Docker-20.10+-blue" alt="Docker">
  <img src="https://img.shields.io/badge/CI/CD-GitHub_Actions-green" alt="GitHub Actions">
  <img src="https://img.shields.io/badge/Testing-JUnit_5-brightgreen" alt="JUnit 5">
  <img src="https://img.shields.io/badge/Selenium-WebDriver-yellow" alt="Selenium">
  <img src="https://img.shields.io/badge/JMeter-Performance-red" alt="JMeter">
</div>

## 🚀 Visión General

HealthTrack es una aplicación de ejemplo diseñada para demostrar la implementación de una **estrategia robusta de pruebas** y automatización de **CI/CD**. El proyecto ilustra las mejores prácticas en el desarrollo de software moderno, incluyendo pruebas unitarias, funcionales y de rendimiento integradas en un pipeline automatizado.

## 📋 Características Principales

- ✅ **Pruebas Unitarias** con JUnit 5
- ✅ **Pruebas Funcionales** con Selenium WebDriver
- ✅ **Pruebas de Rendimiento** con Apache JMeter
- ✅ **Pipeline CI/CD** automatizado con GitHub Actions
- ✅ **Containerización** con Docker y Docker Compose
- ✅ **Integración con SonarQube** (documentada)

## 📂 Estructura del Proyecto

```
.
├── Dockerfile                          # Dockerfile para pruebas unitarias (Maven)
├── docker-compose.yml                  # Configuración de Docker Compose
├── pom.xml                             # Configuración de Maven
├── README.md                           # Este archivo
├── src/                                # Código fuente Java y tests
│   ├── main/java/com/healthtrack/
│   │   └── Usuario.java                # Clase de modelo de usuario
│   └── test/java/com/healthtrack/
│       ├── UsuarioTest.java            # Pruebas unitarias
│       └── functional/
│           └── HealthTrackFunctionalTest.java # Pruebas funcionales
├── webapp/                             # Interfaz de usuario (HTML estático)
│   ├── index.html
│   └── update-weight.html
├── jmeter/                             # Pruebas de rendimiento
│   ├── healthtrack_performance_test.jmx
│   └── results/                        # Reportes de JMeter
├── .github/                            # Configuración de GitHub Actions
│   └── workflows/
│       └── test.yml                    # Pipeline de CI/CD
└── .dockerignore                       # Exclusiones para Docker
```

## 🧪 Estrategia de Pruebas

### 1. Pruebas Unitarias
- **Propósito**: Verificar el funcionamiento de unidades individuales de código
- **Herramientas**: Maven + JUnit 5
- **Cobertura**: Lógica de cálculo de IMC y registro de peso
- **Resultado esperado**: `BUILD SUCCESS` para todas las pruebas

### 2. Pruebas Funcionales
- **Propósito**: Simular interacciones de usuario real con la aplicación web
- **Herramientas**: Selenium WebDriver + Chromium (headless)
- **Escenario**: Flujo de login y actualización de peso
- **Resultado esperado**: `Tests run: 1, Failures: 0, Errors: 0, Skipped: 0`

### 3. Pruebas de Rendimiento
- **Propósito**: Evaluar comportamiento bajo diferentes cargas de trabajo
- **Herramientas**: Apache JMeter
- **Escenario**: Múltiples usuarios concurrentes
- **Resultado esperado**: Reportes detallados en `jmeter/results/`

> **Nota**: Las solicitudes POST en la webapp estática resultarán en errores HTTP 405 (esperado).

## ⚙️ Pipeline CI/CD (GitHub Actions)

### Configuración
- **Archivo**: `.github/workflows/test.yml`
- **Activadores**: Push o Pull Request a la rama `main`

### Pasos del Pipeline
1. **Checkout Repository** - Clona el código del repositorio
2. **Set up JDK 11** - Configura el entorno Java
3. **Build and Run Unit Tests** - Ejecuta pruebas unitarias
4. **Build Docker Images** - Construye imágenes para todos los servicios
5. **Run Functional Tests** - Ejecuta pruebas con Selenium
6. **Run Performance Tests** - Ejecuta pruebas con JMeter
7. **Clean Up** - Limpia contenedores después de la ejecución

## 📊 Calidad del Código (SonarQube)

### Integración Propuesta
```yaml
- name: Analyze with SonarQube
  env:
    GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
    SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
  run: |
    mvn -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
      -Dsonar.projectKey=healthtrack-app \
      -Dsonar.organization=your-sonar-organization \
      -Dsonar.host.url=https://sonarcloud.io \
      -Dsonar.sources=src/main/java \
      -Dsonar.tests=src/test/java
```

### Configuración Requerida
- Instancia de SonarQube/SonarCloud
- Token de autenticación
- Configuración de secrets en GitHub

## 🛠️ Instalación y Ejecución

### Prerrequisitos
- Docker Desktop
- Java 11+
- Maven 3.8+

### Ejecución Local

1. **Clonar el repositorio**
   ```bash
   git clone <URL_DE_TU_REPOSITORIO>
   cd healthtrack
   ```

2. **Crear estructura de carpetas**
   ```bash
   mkdir -p webapp jmeter jmeter/results .github/workflows
   ```

3. **Ejecutar con Docker Compose**
   ```bash
   docker compose up --build
   ```

4. **Acceder a la webapp**
    - Abre tu navegador en `http://localhost:8080`

### Ejecución de Pruebas Individuales

```bash
# Pruebas unitarias
mvn test

# Construir imagen de pruebas funcionales
docker build -f Dockerfile.functional-tests -t functional-tests .

# Ejecutar pruebas de rendimiento
docker run --rm -v $(pwd)/jmeter/results:/results jmeter-tests
```

## 🔧 Tecnologías Utilizadas

| Categoría | Tecnología |
|-----------|------------|
| **Lenguaje** | Java 11 |
| **Build Tool** | Maven |
| **Testing** | JUnit 5, Selenium WebDriver, JMeter |
| **Containerización** | Docker, Docker Compose |
| **CI/CD** | GitHub Actions |
| **Web Server** | Nginx |
| **Calidad de Código** | SonarQube/SonarCloud |

## 📈 Métricas de Calidad

El proyecto implementa las siguientes métricas de calidad:

- **Cobertura de Código**: A través de JaCoCo
- **Análisis Estático**: SonarQube integration
- **Pruebas Automatizadas**: 100% de pipelines automatizados
- **Quality Gates**: Umbrales de calidad predefinidos

## 🤝 Contribuir

1. Fork el proyecto
2. Crea una rama feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📝 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## 🎯 Conclusión

Este proyecto demuestra una comprensión integral de las metodologías de prueba de software y la implementación de un pipeline de CI/CD, elementos esenciales para el desarrollo de software moderno y de alta calidad.

---

<div align="center">
  <strong>Desarrollado con ❤️ para demostrar las mejores prácticas en testing y CI/CD</strong>
</div>