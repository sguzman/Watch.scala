package com.github.sguzman.watch

import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.html.Html

object Main {
  @dom def render: Binding[Html] = {
    <html>
      <head>
        <meta charset="UTF-8" />
        <link rel="shortcut icon" href="./favicon.ico" type="image/x-icon" />
        <title>Watch Anime</title>
      </head>
      <body>
        <div id="container">
          <header>
            <h1>Hello</h1>
          </header>
        </div>
      </body>
    </html>
  }

  def main(args: Array[String]): Unit = {
    com.thoughtworks.binding.dom.render(org.scalajs.dom.document, render)
  }
}
