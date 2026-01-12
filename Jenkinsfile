pipeline {
    agent any
    
    tools {
        maven 'Maven'
        jdk 'JDK21'
    }
    
    environment {
        VERSION_PROYECTO = '1.0.0'
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
                    echo "Eliminando directorio versiones...."
                    
                    bat '''
                        if exist versiones (
                            rmdir /s /q versiones
                        )
                    '''
                    
                    echo "Se crea el directorio versiones con la última versión de la api"
                    
                    bat 'mkdir versiones'
                    
                    bat "copy target\\ExamenMocCPV-${VERSION_PROYECTO}.jar versiones\\productos-api-${VERSION_PROYECTO}.jar"
                }
            }
        }
    }
    
    post {
        success {
            echo 'Pipeline completado con éxito!'
        }
        failure {
            echo 'El pipeline ha fallado.'
        }
    }
}
