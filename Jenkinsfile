//  Bu, bir Jenkins pipeline'ını tanımlar. Tüm iş akışını (pipeline) kapsar. İçinde agent, stages gibi anahtar kelimeler bulunur.
pipeline {
	// fadesi, pipeline'ın hangi ajan (worker) üzerinde çalışacağını belirtir. any, herhangi bir uygun ajanda çalışacağı anlamına gelir. Bu, Jenkins’in herhangi bir çalışan node'u kullanarak pipeline'ı çalıştırmasını sağlar.
	// agent any : farklı agentlarda kullanabiliriz
	//stages bloğu, pipeline'ın farklı aşamalarını (stages) içerir. Her bir aşama, sırasıyla çalıştırılacak bir dizi adımı tanımlar. Burada her aşama, stage ile tanımlanır.

   	agent {
		docker{
			image 'nginx:latest'
		}
	}
    stages {
		//  pipeline'ınızın ilk adımıdır ve genellikle uygulamanızın derlenmesi veya yapılandırılması için kullanılır.
		stage('Build') {
			steps {
				sh "nginx --version"
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

}