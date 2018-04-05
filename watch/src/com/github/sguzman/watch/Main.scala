package com.github.sguzman.watch

import com.github.sguzman.watch.protoc.store.{AnimeUser, StoreCache}
import com.thoughtworks.binding.Binding.{Var, Vars}
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.Element
import org.scalajs.dom.ext.Ajax
import org.scalajs.dom.html._
import org.scalajs.dom.raw.Event

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.typedarray.{ArrayBuffer, Uint8Array}
import scala.util.{Failure, Success}

object Main {
  sealed abstract class ViewType
  final class ListView extends ViewType
  final class ImageListView extends ViewType
  final class AlphabetView extends ViewType

  final case class Model(store: Vars[AnimeUser], view: Var[ViewType], listPageSize: Var[Int], listIdx: Var[Int], imagePageSize: Var[Int], imageIdx: Var[Int])
  val model = Model(Vars(), Var(new ListView), Var(5), Var(0), Var(5), Var(0))

  implicit final class StrWrap(str: String) {
    def id[A] = org.scalajs.dom.document.getElementById(str).asInstanceOf[A]
  }

  sealed abstract class Message[+A](a: A) {
    def print(): Unit = println(a)
  }

  final case class TabClick(view: ViewType) extends Message(view)
  final case class SelectChange(size: Int) extends Message(size)
  final case class SelectImageChange(size: Int) extends Message(size)
  final case class ButtonNavClick(incOrDec: Int) extends Message(incOrDec)
  final case class ButtonNavImageClick(incOrDec: Int) extends Message(incOrDec)

  def update[A](msg: Message[A]): Unit = {
    msg match {
      case TabClick(v) => model.view.value = v
      case SelectChange(v) =>
        model.listPageSize.value = Array(5, 25, 50, 100, 500, 1000)(v)
        model.listIdx.value = 0
      case ButtonNavClick(v) => model.listIdx.value += v
      case SelectImageChange(v) =>
        model.imagePageSize.value = Array(5, 25, 50, 100, 500, 1000)(v)
        model.imageIdx.value = 0
      case ButtonNavImageClick(v) => model.imageIdx.value += v
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
      case ("select-list", "change") => update(SelectChange("select-list".id[Select].selectedIndex))
      case ("list-prev", "click") => update(ButtonNavClick(-1))
      case ("list-next", "click") => update(ButtonNavClick(1))
      case ("select-image", "change") => update(SelectImageChange("select-image".id[Select].selectedIndex))
      case ("image-prev", "click") => update(ButtonNavImageClick(1))
      case ("image-next", "click") => update(ButtonNavImageClick(1))
      case _ => throw new Exception("Bad event")
    }
  }

  @dom def alphaView: Binding[Div] = {
    <div>
      <p>Alphabet view - To come...</p>
    </div>
  }

  @dom def buttonImage: Binding[Span] = {
    <span>
      <button disabled={model.imageIdx.bind == 0} onclick={emit[Button] _} id="image-prev">Previous</button>
      <button disabled={Math.ceil(model.store.bind.length / model.imagePageSize.bind) == model.imageIdx.bind} onclick={emit[Button] _} id="image-next">Next</button>
    </span>
  }

  @dom def selectImage: Binding[Select] = {
    <select id="select-image" onchange={emit[Select] _}>
      <option selected={model.imagePageSize.bind == 5} value="5">5</option>
      <option selected={model.imagePageSize.bind == 25} value="25">25</option>
      <option selected={model.imagePageSize.bind == 50} value="50">50</option>
      <option selected={model.imagePageSize.bind == 100} value="100">100</option>
      <option selected={model.imagePageSize.bind == 500} value="500">500</option>
      <option selected={model.imagePageSize.bind == 1000} value="1000">1000</option>
    </select>
  }

  @dom def imageView: Binding[Div] = {
    val groupings = model.store.bind.grouped(model.imagePageSize.bind).map(a => Vars(a: _*)).toList

    <div>
      {buttonImage.bind}
      {selectImage.bind}
      <ul>
        {
          for (i <- if (model.store.bind.nonEmpty) groupings(model.imageIdx.bind) else model.store) yield {
            <li class="anime-list-item">
              <div class="div-item">
                <p>{i.getAnime.getSummary.title}</p>
                <img class="thumbnail" src={s"https://www.anime-planet.com${i.getAnime.getSummary.img}"} />
              </div>
            </li>
          }
        }
      </ul>
    </div>
  }

  @dom def listView: Binding[Div] = {
    val groupings = model.store.bind.grouped(model.listPageSize.bind).map(a => Vars(a: _*)).toList

    <div>
      {selectList.bind}
      {buttonList.bind}
      <table>
        <tr>
          <th>Id</th>
          <th>Title</th>
          <th>Year</th>
          <th>Rating</th>
          <th>Show Type</th>
          <th>Studio</th>
          <th>Genres</th>
          <th>Alt title</th>
          <th>Rank</th>
          <th>Watched</th>
          <th>Watching</th>
          <th>Want To Watch</th>
          <th>Stalled</th>
          <th>Dropped</th>
          <th>Won't Watch</th>
        </tr>
        {
          for (i <- if (model.store.bind.nonEmpty) groupings(model.listIdx.bind) else model.store) yield {
            <tr>
              <td>{i.getAnime.id.toString}</td>
              <td>{i.getAnime.getSummary.title}</td>
              <td>{i.getAnime.getSummary.year}</td>
              <td>{i.getAnime.getSummary.rating.toString}</td>
              <td>{i.getAnime.getSummary.showType}</td>
              <td>{i.getAnime.getSummary.studio}</td>
              <td>{i.getAnime.getSummary.genres.mkString(" ")}</td>
              <td>{i.getAnime.alt}</td>
              <td>{i.getAnime.rank.toString}</td>
              <td>{i.getUser.watched.toString}</td>
              <td>{i.getUser.watching.toString}</td>
              <td>{i.getUser.wantToWatch.toString}</td>
              <td>{i.getUser.stalled.toString}</td>
              <td>{i.getUser.dropped.toString}</td>
              <td>{i.getUser.wontWatch.toString}</td>
            </tr>
          }
        }
      </table>
    </div>
  }

  @dom def buttonList: Binding[Span] = {
    <span>
      <button disabled={model.listIdx.bind == 0} onclick={emit[Button] _} id="list-prev">Previous</button>
      <button disabled={Math.ceil(model.store.bind.length / model.listPageSize.bind) == model.listIdx.bind} onclick={emit[Button] _} id="list-next">Next</button>
    </span>
  }

  @dom def selectList: Binding[Select] = {
    <select id="select-list" onchange={emit[Select] _}>
      <option selected={model.listPageSize.bind == 5} value="5">5</option>
      <option selected={model.listPageSize.bind == 25} value="25">25</option>
      <option selected={model.listPageSize.bind == 50} value="50">50</option>
      <option selected={model.listPageSize.bind == 100} value="100">100</option>
      <option selected={model.listPageSize.bind == 500} value="500">500</option>
      <option selected={model.listPageSize.bind == 1000} value="1000">1000</option>
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
