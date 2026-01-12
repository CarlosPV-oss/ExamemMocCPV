pipeline {
    agent any
    
    tools {
        maven 'Maven'
        jdk 'JDK21'
    }
    
    environment {
        VERSION = '1.0.0'
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Descargando código del repositorio...'
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo 'Limpiando y compilando el proyecto...'
                bat 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                echo 'Ejecutando tests...'
                bat 'mvn test'
            }
        }
        
        stage('Package') {
            steps {
                echo 'Empaquetando la aplicación...'
                bat 'mvn package'
            }
        }
        
        stage('Move jar') {
            steps {
                script {
                    echo 'Eliminando directorio versiones....'
                    
                    // Verificar si existe la carpeta versiones y eliminarla
                    bat '''
                        if exist versiones (
                            rmdir /s /q versiones
                        )
                    '''
                }
            }
            post {
                success {
                    script {
                        echo 'Se crea el directorio versiones con la última versión de la api'
                        
                        // Crear carpeta versiones
                        bat 'mkdir versiones'
                        
                        // Copiar el jar (NO el original) a la carpeta versiones
                        bat "copy target\\ExamenMocCPV-${VERSION}.jar versiones\\"
                    }
                }
            }
        }
    }
}
