/*
//  Bu, bir Jenkins pipeline'ını tanımlar. Tüm iş akışını (pipeline) kapsar. İçinde agent, stages gibi anahtar kelimeler bulunur.
pipeline {
	// fadesi, pipeline'ın hangi ajan (worker) üzerinde çalışacağını belirtir. any, herhangi bir uygun ajanda çalışacağı anlamına gelir. Bu, Jenkins’in herhangi bir çalışan node'u kullanarak pipeline'ı çalıştırmasını sağlar.
	// agent any : farklı agentlarda kullanabiliriz
	//stages bloğu, pipeline'ın farklı aşamalarını (stages) içerir. Her bir aşama, sırasıyla çalıştırılacak bir dizi adımı tanımlar. Burada her aşama, stage ile tanımlanır.

  */
/* 	agent {
		docker{
			image 'nginx:latest'
		}
	}*//*


	agent any
	environment {
		dockerHome = tool 'docker'
		mavenHome = tool 'maven'
		PATH = "$dockerHome/bin:$mavenHome/bin:$PATH"
	}
    stages {
		//  pipeline'ınızın ilk adımıdır ve genellikle uygulamanızın derlenmesi veya yapılandırılması için kullanılır.
		stage('Build') {
			steps {
				echo "PATH -${PATH}"
				echo "BUILD_NUMBER -${env.BUILD_NUMBER}"
				echo "BUILD_ID -${env.BUILD_ID}"
				echo "JOB_NAME -${env.JOB_NAME}"
				echo "BUILD_TAG -${env.BUILD_TAG}"
				echo "BUILD_URL -${env.BUILD_URL}"
				echo 'Build aşaması çalışıyor...'
            }
        }
        //  uygulamanızın test edilmesini sağlar. Bu aşamada, birim testleri veya entegrasyon testleri çalıştırılabilir.
        stage('Test') {
			steps {
				echo 'Test aşaması çalışıyor...'
            }
        }
        // , uygulamanın farklı modüllerinin veya servislerinin bir arada nasıl çalıştığını test etmek için kullanılır. Entegrasyon testleri genellikle birden fazla bileşenin birlikte çalışıp çalışmadığını kontrol eder.
         stage('Integration Test') {
			steps {
				echo 'Test aşaması çalışıyor...'
            }
        }
    }
    // jenkins pipeline'larında post bloğu, pipeline tamamlandıktan sonra yapılacak işlemleri belirler.
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

}*/
/*pipeline {
	agent any
	environment {
		dockerHome = tool 'docker'
		mavenHome = tool 'maven'
		PATH = "$dockerHome/bin:$mavenHome/bin:$PATH"
	}
    stages {
		stage('Checkout') {
			steps {
				echo "PATH -${PATH}"
				echo "BUILD_NUMBER -${env.BUILD_NUMBER}"
				echo "BUILD_ID -${env.BUILD_ID}"
				echo "JOB_NAME -${env.JOB_NAME}"
				echo "BUILD_TAG -${env.BUILD_TAG}"
				echo "BUILD_URL -${env.BUILD_URL}"
				echo 'Build aşaması çalışıyor...'
            }
        }
        stage('Compile') {
			steps {
				sh "mvn clean compile"
            }
        }
         stage('Test') {
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

}*/

pipeline {
	agent any
	environment {
		dockerHome = tool 'docker'
		mavenHome = tool 'maven'
		PATH = "$dockerHome/bin:$mavenHome/bin:$PATH"
	}
    stages {
		stage('Checkout') {
			steps {
				echo "PATH -${PATH}"
				echo "BUILD_NUMBER -${env.BUILD_NUMBER}"
				echo "BUILD_ID -${env.BUILD_ID}"
				echo "JOB_NAME -${env.JOB_NAME}"
				echo "BUILD_TAG -${env.BUILD_TAG}"
				echo "BUILD_URL -${env.BUILD_URL}"
				echo 'Build aşaması çalışıyor...'
            }
        }
        stage('Compile') {
			steps {
				sh "mvn clean compile"
            }
        }
         stage('Test') {
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
