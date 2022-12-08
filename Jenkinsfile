pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage("Build") {
            steps {
                echo "Building the app ..."
                sh 'mvn test-compile'
            }
        }

        stage("Unit testing") {
            steps {
                echo "Starting unit tests ..."
                sh 'mvn clean -Dtest=ShoppingCartTest test'
                echo "Unit testing is finished"
            }
        }

        stage("API testing") {
                    steps {
                        echo "Starting API tests ..."
                        sh 'mvn clean -Dtest=APITests test'
                        echo "API testing is finished"
                    }
                }

    }
    post {
                always {
                    archiveArtifacts artifacts: 'target/surefire-reports/*.xml', fingerprint: true
                    allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results'], [path: 'other_target/allure-results']]
                        }
                }
}