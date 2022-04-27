package billing.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args) {
		addInitializers(BeanInitializer())
	}
}

class BeanInitializer : ApplicationContextInitializer<GenericApplicationContext> {

	override fun initialize(applicationContext: GenericApplicationContext) {
		beans().initialize(applicationContext)
	}
}
