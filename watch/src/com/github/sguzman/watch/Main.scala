package com.github.sguzman.watch

import com.github.sguzman.watch.protoc.store.StoreCache
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.ext.Ajax
import org.scalajs.dom.html.{Div, Html}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.typedarray.{ArrayBuffer, Uint8Array}
import scala.util.{Failure, Success}

object Main {
  var store: StoreCache = _

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
    val url = "https://brotli-encode.herokuapp.com/sguzman/GoJS/master/store.msg.br?brotli=true"

    Ajax.get(url, responseType = "arraybuffer").onComplete({
      case Success(v) =>
        val resp = v.response.asInstanceOf[ArrayBuffer]
        val buffer = new Uint8Array(resp).map(a => a.toByte)
        store = StoreCache.parseFrom(buffer.toArray[Byte])

      case Failure(e) => println(e)
    })

    com.thoughtworks.binding.dom.render(org.scalajs.dom.document, render)
  }
}
