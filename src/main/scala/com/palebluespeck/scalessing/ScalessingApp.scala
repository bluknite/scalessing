package com.palebluespeck.scalessing

import com.palebluespeck.scalessing.common._
import processing.core.PApplet

class ScalessingApp extends PApplet {
  override def settings(): Unit = {
    smooth(4)
  }

  def fill(color: Color): Unit = color match {
    case NoColor => noFill()
    case g:Gray => fill(g.gray, g.alpha)
    case c:Rgb => fill(c.r, c.g, c.b, c.alpha)
  }

  def stroke(color: Color): Unit = color match {
    case NoColor => noStroke()
    case g: Gray => stroke(g.gray, g.alpha)
    case c: Rgb => stroke(c.r, c.g, c.b, c.alpha)
  }

  def translate(position: Position): Unit =
    translate(position.x, position.y)
}
