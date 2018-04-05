package com.github.sguzman.watch

import com.thoughtworks.binding.{Binding, dom}
import fr.hmil.roshttp.HttpRequest
import fr.hmil.roshttp.Method.GET
import fr.hmil.roshttp.Protocol.HTTPS
import fr.hmil.roshttp.response.SimpleHttpResponse
import monix.execution.Scheduler.Implicits.global
import org.scalajs.dom.html.{Div, Html}

import scala.util.{Failure, Success}

object Main {
  @dom def body: Binding[Div] = {
    <div>
      <p>???</p>
    </div>
  }

  @dom def render: Binding[Html] = {
    <html lang="en">
      <head>
        <meta charset="UTF-8" />
        <link rel="shortcut icon" href="./watch/assets/favicon.ico" type="image/x-icon" />
        <link rel="stylesheet" href="./watch/assets/styles.css" />
        <title>Watch Anime</title>
      </head>
      <body>
        <div id="container">
          <header>
            <h1>Anime</h1>
          </header>
          <main>
            {body.bind}
          </main>
        </div>
      </body>
    </html>
  }

  def main(args: Array[String]): Unit = {
    val request =
      HttpRequest()
          .withMethod(GET)
          .withProtocol(HTTPS)
          .withHost("brotli-encode.herokuapp.com")
          .withPath("/sguzman/GoJS/master/store.msg.br")
          .withQueryParameter("brotli", "true")

    request.send().onComplete({
      case res: Success[SimpleHttpResponse] => println(res.get)
      case _: Failure[SimpleHttpResponse] => println("Houston, we got a problem!")
    })

    com.thoughtworks.binding.dom.render(org.scalajs.dom.document, render)
  }
}
