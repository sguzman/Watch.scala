package com.github.sguzman.watch

import com.github.sguzman.watch.protoc.store.{AnimeUser, StoreCache}
import com.thoughtworks.binding.Binding.{Var, Vars}
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.ext.Ajax
import org.scalajs.dom.html.{Button, Div, Html}
import org.scalajs.dom.raw.Event

import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.reflectiveCalls
import scala.scalajs.js.typedarray.{ArrayBuffer, Uint8Array}
import scala.util.{Failure, Success}

object Main {
  sealed abstract class ViewType
  final class ListView extends ViewType
  final class ImageListView extends ViewType
  final class AlphabetView extends ViewType

  final case class Model(store: Vars[AnimeUser], view: Var[ViewType])
  val model = Model(Vars(), Var(new ListView))

  implicit final class StrWrap(str: String) {
    def id[A] = org.scalajs.dom.document.getElementById(str).asInstanceOf[A]
  }

  sealed abstract class Message[A](a: A) {
    def print(): Unit = println(a)
  }

  final case class TabClick(view: ViewType) extends Message(view)

  def update[A](msg: Message[A]): Unit = {
    msg match {
      case TabClick(v) => model.view.value = v
    }
  }

  def emit[A <: { def id: String }](e: Event): Unit = {
    val target = e.currentTarget.asInstanceOf[A].id
    val kind = e.`type`

    val tup = (target, kind)

    println(s"Emitted $tup")

    val _  = tup match {
      case ("tab-list", "click") => TabClick(new ListView)
      case ("tab-image", "click") => TabClick(new ImageListView)
      case ("tab-alpha", "click") => TabClick(new AlphabetView)
      case _ => throw new Exception("Bad event")
    }
  }

  @dom def alphaView: Binding[Div] = {
    <div>
      <p>Alphabet view - To come...</p>
    </div>
  }

  @dom def imageView: Binding[Div] = {
    <div>
      <p>Image view - To come...</p>
    </div>
  }

  @dom def listView: Binding[Div] = {
    <div>
      <ul>
        {
          for (i <- model.store) yield {
            <li>{i.getAnime.getSummary.title}</li>
          }
        }
      </ul>
    </div>
  }

  @dom def body: Binding[Div] = {
    {(model.view.bind match {
      case _: ListView => listView
      case _: ImageListView => imageView
      case _: AlphabetView => alphaView
    }).bind}
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
            <div>
              <button onclick={emit[Button] _} id="tab-list">List</button>
              <button onclick={emit[Button] _} id="tab-image">Image</button>
              <button onclick={emit[Button] _} id="tab-alpha">Alpha</button>
            </div>
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
        val store = StoreCache.parseFrom(buffer.toArray[Byte])
        store.cache.values.foreach(a => model.store.value += a)

      case Failure(e) => println(e)
    })

    com.thoughtworks.binding.dom.render(org.scalajs.dom.document, render)
  }
}
