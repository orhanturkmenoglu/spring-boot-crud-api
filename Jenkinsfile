pipeline {
	agent any
    stages {
		stage('Build') {
			steps {
				echo 'Build aşaması çalışıyor...'
            }
        }
        stage('Test') {
			steps {
				echo 'Test aşaması çalışıyor...'
            }
        }
         stage('Integration Test') {
			steps {
				echo 'Test aşaması çalışıyor...'
            }
        }
    }
     post {
		success {
			echo 'Pipeline başarıyla tamamlandı!'
        }
        failure {
			echo 'Pipeline başarısız oldu!'
        }
        always {
			echo 'Bu adım her zaman çalışacak.'
        }
    }

}