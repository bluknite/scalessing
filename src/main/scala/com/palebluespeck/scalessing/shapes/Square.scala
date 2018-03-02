package com.palebluespeck.scalessing.shapes

import com.palebluespeck.scalessing.ScalessingApp
import processing.core.PConstants

class Square(var side: Float) extends Shape[Square] {
  override def self: Square = this

  def setSize(size: Float): Square = {
    this.side = size
    self
  }

  override protected def clonedShape: Square = Square(side)
  override def scaled(s: Float): Square = cloned.setSize(s * side)

  override def drawShape(): Unit = {
    app.rectMode(PConstants.CENTER)
    app.rect(position.x, position.y, side, side)
    app.rectMode(PConstants.CORNER)
  }
}
object Square {
  def apply(side: Float)(implicit app: ScalessingApp): Square = new Square(side).withApp(app)
  def apply(side: Double)(implicit app: ScalessingApp): Square = new Square(side.toFloat).withApp(app)
}