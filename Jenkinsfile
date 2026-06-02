pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Checkout source code'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Compile Java source and test files'

                sh '''
                    rm -rf classes test-classes sources.txt tests.txt test_results.txt
                    mkdir -p classes test-classes

                    find Practice/src -name "*.java" > sources.txt

                    if [ ! -s sources.txt ]; then
                        echo "No Java source files found in Practice/src"
                        exit 1
                    fi

                    javac -encoding UTF-8 -d classes @sources.txt

                    find Practice/test -name "*.java" > tests.txt

                    if [ ! -s tests.txt ]; then
                        echo "No JUnit test files found in Practice/test"
                        exit 1
                    fi

                    javac -encoding UTF-8 -cp "classes:Practice/lib/junit-platform-console-standalone-1.7.1.jar" -d test-classes @tests.txt
                '''
            }
        }

        stage('Test') {
            steps {
                echo 'Run JUnit 5 tests'

                sh '''
                    java -jar Practice/lib/junit-platform-console-standalone-1.7.1.jar \
                    --class-path classes:test-classes \
                    --scan-class-path \
                    > test_results.txt
                '''
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'test_results.txt', allowEmptyArchive: true
        }

        failure {
            echo 'Build or test failed'
        }

        success {
            echo 'Build and test succeeded'
        }
    }
}