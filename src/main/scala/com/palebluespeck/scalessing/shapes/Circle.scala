package com.palebluespeck.scalessing.shapes

import com.palebluespeck.scalessing.ScalessingApp
import com.palebluespeck.scalessing.common.Position
import processing.core.PConstants

class Circle(var center: Position, var radius: Float, override val app: ScalessingApp) extends Shape[Circle] {
  override def self: Circle = this

  def setCenter(position: Position): Circle = {
    center = position
    self
  }

  def setRadius(r: Float): Circle = {
    radius = r
    self
  }

  override def drawShape(): Unit = {
    app.ellipseMode(PConstants.RADIUS)
    app.ellipse(center.x, center.y, radius, radius)
  }
}
object Circle {
  def apply(center: Position, radius: Float)(implicit app: ScalessingApp): Circle = new Circle(center, radius, app)
  def apply(center: Position, radius: Double)(implicit app: ScalessingApp): Circle = new Circle(center, radius.toFloat, app)
}