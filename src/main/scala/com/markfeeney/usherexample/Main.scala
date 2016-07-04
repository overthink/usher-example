package com.markfeeney.usherexample

import java.util.concurrent.atomic.AtomicInteger

import com.markfeeney.circlet.{Handler, JettyAdapter, JettyOptions, Response}
import com.markfeeney.usher.Usher
import com.markfeeney.usher.Usher.Simple.{GET, POST, PUT}
import com.markfeeney.usher.Usher.{notFound, routes}

import scala.collection.mutable

object Main {

  def main(args: Array[String]): Unit = {

    val counter = new AtomicInteger(2)
    val db = mutable.HashMap[Int, String]()
    db(0) = "Am I a good person? No. But do I try to be better every single day? Also no  - @internethippo"
    db(1) = "Remember, always follow your passion. And if your passion doesn't fit into global capitalism, well, then you are a failure at life. - @existentialcoms"
    db(2) = "A heuristic on whether you have control of your life: can you take naps? - @nntaleb"

    val app: Handler = routes(
      GET("/") { _ => Response(body = "Usher demo\n") },
      GET("/saying/:id") { req =>
        for {
          params <- Usher.get(req)
          id <- params.getInt("id")
          result <- db.get(id)
        } yield Response(body = result + "\n")
      },
      POST("/saying") { req =>
        req.bodyString().map { body =>
          val newId = counter.incrementAndGet()
          db.put(newId, body)
          Response(status = 201).addHeader("Location", f"/saying/$newId%d")
        }
      },
      PUT("/saying/:id") { req =>
        for {
          params <- Usher.get(req)
          id <- params.getInt("id")
          saying <- req.bodyString()
        } yield {
          db.put(id, saying)
          Response(status = 201).addHeader("Location", f"/saying/$id%d")
        }
      },
      notFound("Computer said no.\n")
    )

    val opts = JettyOptions(httpPort = 8888, configFn = _.setStopAtShutdown(true), join = true)
    JettyAdapter.run(app, opts)
    println("bye!")
  }

}
