package com.palebluespeck.scalessing.shapes

import com.palebluespeck.scalessing.ScalessingApp
import com.palebluespeck.scalessing.common.{Color, NoColor, Position}

trait Shape[T <: Shape[T]] {
  implicit var app: ScalessingApp = _
  var fill: Color = NoColor
  var stroke: Color = NoColor
  var position: Position = Position(0, 0)
  var angle: Float = 0

  def self: T

  final def withApp(app: ScalessingApp): T = {
    this.app = app
    self
  }

  def withFill(color: Color): T = {
    fill = color
    self
  }

  def withStroke(color: Color): T = {
    stroke = color
    self
  }

  def withPosition(position: Position): T = {
    this.position = position
    self
  }
  def +(position: Position): T = withPosition(this.position + position)

  def withAngle(angle: Float): T = {
    this.angle = angle
    self
  }
  def rotate(a: Float): T = {
    withAngle(this.angle + a)
    self
  }
  def rotate(a: Double): T = rotate(a.toFloat)

  def draw(): Unit = {
    app.fill(fill)
    app.stroke(stroke)
    app.translate(position)
    app.rotate(angle)
    drawShape()
    app.rotate(-angle)
    app.translate(-position)
  }

  final def cloned: T = clonedShape
    .withApp(app)
    .withFill(fill)
    .withStroke(stroke)
    .withPosition(position)
  protected def clonedShape: T

  def scaled(s: Float): T
  def scaled(s: Double): T = scaled(s.toFloat)

  def drawShape(): Unit
}