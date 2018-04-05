package com.github.sguzman.watch

import com.github.sguzman.watch.protoc.store.{AnimeUser, StoreCache}
import com.thoughtworks.binding.Binding.{Var, Vars}
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.Element
import org.scalajs.dom.ext.Ajax
import org.scalajs.dom.html.{Button, Div, Html, Select}
import org.scalajs.dom.raw.Event

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.typedarray.{ArrayBuffer, Uint8Array}
import scala.util.{Failure, Success}

object Main {
  sealed abstract class ViewType
  final class ListView extends ViewType
  final class ImageListView extends ViewType
  final class AlphabetView extends ViewType

  final case class Model(store: Vars[AnimeUser], view: Var[ViewType], listPageSize: Var[Int], listIdx: Var[Int])
  val model = Model(Vars(), Var(new ListView), Var(25), Var(0))

  implicit final class StrWrap(str: String) {
    def id[A] = org.scalajs.dom.document.getElementById(str).asInstanceOf[A]
  }

  sealed abstract class Message[+A](a: A) {
    def print(): Unit = println(a)
  }

  final case class TabClick(view: ViewType) extends Message(view)
  final case class SelectChange(size: Int) extends Message(size)

  def update[A](msg: Message[A]): Unit = {
    msg match {
      case TabClick(v) => model.view.value = v
      case SelectChange(v) =>
        model.listPageSize.value = Array(5, 25, 50, 100, 500, 1000)(v)
        model.listIdx.value = 0
    }
  }

  def emit[A <: Element](e: Event): Unit = {
    val target = e.currentTarget.asInstanceOf[A]
    val kind = e.`type`

    val tup = (target.id, kind)
    println(s"Emitted $tup")

    tup match {
      case ("tab-list", "click")  => update(TabClick(new ListView))
      case ("tab-image", "click") => update(TabClick(new ImageListView))
      case ("tab-alpha", "click") => update(TabClick(new AlphabetView))
      case ("select-list-page-size", "change") => update(SelectChange("select-list-page-size".id[Select].selectedIndex))
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
      {selectList.bind}
      <ul>
        {
          for (i <- if (model.store.bind.nonEmpty) model.store.bind.grouped(model.listPageSize.bind).map(a => Vars(a: _*)).toList(model.listIdx.bind) else model.store) yield {
            <li>{i.getAnime.getSummary.title}</li>
          }
        }
      </ul>
    </div>
  }

  @dom def selectList: Binding[Select] = {
    <select id="select-list-page-size" onchange={emit[Select] _}>
      <option value="5">5</option>
      <option value="25">25</option>
      <option value="50">50</option>
      <option value="100">100</option>
      <option value="500">500</option>
      <option value="1000">1000</option>
    </select>
  }

  @dom def body: Binding[Div] = {
    <div>
      {(model.view.bind match {
      case _: ListView => listView
      case _: ImageListView => imageView
      case _: AlphabetView => alphaView
    }).bind}
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
        val store = StoreCache.parseFrom(buffer.toArray[Byte]).cache.values.toList.sortBy(_.getAnime.getSummary.title)
        model.store.value ++= store

      case Failure(e) => println(e)
    })

    com.thoughtworks.binding.dom.render(org.scalajs.dom.document, render)
  }
}
