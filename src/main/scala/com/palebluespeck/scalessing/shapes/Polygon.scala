package com.palebluespeck.scalessing.shapes

import com.palebluespeck.scalessing.ScalessingApp
import com.palebluespeck.scalessing.common._
import processing.core.PShape

class Polygon(var vertices: List[Position]) extends Shape[Polygon] {
  override def self: Polygon = this

  private def setVertices(vertices: List[Position]): Polygon = {
    this.vertices = vertices
    self
  }

  override def withPosition(position: Position): Polygon = {
    super.withPosition(position)
    setVertices(vertices.map(_ + position))
  }

  override protected def clonedShape: Polygon = Polygon(vertices)

  override def scaled(s: Float): Polygon = {
    val adjustedVertices = vertices.map(v => {
      val vector = v - position
      val adjustedVector = vector * s
      position + adjustedVector
    })
    cloned.setVertices(adjustedVertices)
  }

  override def drawShape(): Unit = {
    val shape = app.createShape()
    shape.beginShape()
    shape.fill(fill)
    shape.stroke(stroke)
    vertices.foreach(v => shape.vertex(v))
    shape.endShape()
    app.shape(shape)
  }

  private implicit class PShapeHelper(s: PShape) {
    def fill(c: Color): Unit = c match {
      case NoColor => s.noFill()
      case g: Gray => s.fill(g.gray, g.alpha)
      case c: Rgb => s.fill(c.r, c.g, c.b, c.alpha)
    }

    def stroke(c: Color): Unit = c match {
      case NoColor => s.noStroke()
      case g: Gray => s.stroke(g.gray, g.alpha)
      case c: Rgb => s.stroke(c.r, c.g, c.b, c.alpha)
    }

    def vertex(p: Position): Unit = s.vertex(p.x, p.y)
  }
}
object Polygon {
  def apply(vertices: List[Position])(implicit app: ScalessingApp): Polygon = new Polygon(vertices).withApp(app)
}