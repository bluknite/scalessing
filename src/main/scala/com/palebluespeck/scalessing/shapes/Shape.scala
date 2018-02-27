package com.palebluespeck.scalessing.shapes

import com.palebluespeck.scalessing.ScalessingApp
import com.palebluespeck.scalessing.common.{Color, NoColor}

trait Shape[T <: Shape[T]] {
  val app: ScalessingApp

  var fill: Color = NoColor
  var stroke: Color = NoColor

  def self: T

  def setFill(color: Color): T = {
    fill = color
    self
  }

  def setStroke(color: Color): T = {
    stroke = color
    self
  }

  def draw(): Unit = {
    app.fill(fill)
    app.stroke(stroke)
    drawShape()
  }

  def drawShape(): Unit
}
