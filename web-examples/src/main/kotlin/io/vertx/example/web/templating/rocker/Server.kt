package io.vertx.example.web.templating.rocker

import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.TemplateHandler
import io.vertx.ext.web.templ.rocker.RockerTemplateEngine

class Server : io.vertx.core.AbstractVerticle()  {
  override fun start() {

    var router = Router.router(vertx)

    // Populate context with data
    router.route().handler({ ctx ->
      ctx.put("title", "Vert.x Web Example Using Rocker")
      ctx.put("name", "Rocker")
      ctx.next()
    })

    // Render a custom template.
    // Note: you need a compile-time generator for Rocker to work properly
    // See the pom.xml for an example
    router.route().handler(TemplateHandler.create(RockerTemplateEngine.create()))

    vertx.createHttpServer().requestHandler({ router.accept(it) }).listen(8080)
  }
}
