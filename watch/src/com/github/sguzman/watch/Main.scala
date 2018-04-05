package com.github.sguzman.watch

import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.html.{Div, Html}

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
    com.thoughtworks.binding.dom.render(org.scalajs.dom.document, render)
  }
}
