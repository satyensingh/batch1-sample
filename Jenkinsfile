pipeline {
	agent any
	stages {
		stage('Build') {
			steps {
				sh 'mvn clean package'
				sh "docker build . -t myapp:${env.BUILD_ID}"
			}
		}
	}
}
