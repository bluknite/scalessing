package com.palebluespeck.scalessing.charts

import com.palebluespeck.scalessing.ScalessingApp
import com.palebluespeck.scalessing.common.Position

trait Chart[T <: Chart[T]] {
  implicit var app: ScalessingApp = _
  var position: Position = (0, 0)

  def self: T

  final def withApp(app: ScalessingApp): T = {
    this.app = app
    self
  }

  def withPosition(position: Position): T = {
    this.position = position
    self
  }
  def +(position: Position): T = withPosition(this.position + position)

  def draw(): Unit = {
    app.translate(position)
    drawChart()
    app.translate(-position)
  }

  def drawChart(): Unit
}
