package com.palebluespeck.scalessing.shapes

import com.palebluespeck.scalessing.ScalessingApp
import processing.core.PConstants

class Circle(var radius: Float) extends Shape[Circle] {
  override def self: Circle = this

  def setRadius(r: Float): Circle = {
    radius = r
    self
  }

  override protected def clonedShape: Circle = Circle(radius)
  override def scaled(s: Float): Circle = cloned.setRadius(s * radius)

  override def drawShape(): Unit = {
    app.ellipseMode(PConstants.RADIUS)
    app.ellipse(position.x, position.y, radius, radius)
    app.ellipseMode(PConstants.CENTER)
  }
}
object Circle {
  def apply(radius: Float)(implicit app: ScalessingApp): Circle = new Circle(radius).withApp(app)
  def apply(radius: Double)(implicit app: ScalessingApp): Circle = new Circle(radius.toFloat).withApp(app)
}