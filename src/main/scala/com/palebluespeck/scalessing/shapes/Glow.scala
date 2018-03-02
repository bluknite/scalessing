package com.palebluespeck.scalessing.shapes

import com.palebluespeck.scalessing.ScalessingApp
import com.palebluespeck.scalessing.common.Position

class Glow[T <: Shape[T]](var shape: Shape[T], glowRatio: Float) extends Shape[Glow[T]] {
  withFill(shape.fill)

  override def self: Glow[T] = this

  require(glowRatio >= 1, "Glow Ratio must be greater than or equal to 1")
  private val ratioDifference: Float = glowRatio - 1

  def withShape(shape: Shape[T]): Glow[T] = {
    this.shape = shape
    self
  }

  override def withPosition(position: Position): Glow[T] = {
    super.withPosition(position)
    shape.withPosition(position)
    self
  }

  override protected def clonedShape: Glow[T] = Glow(shape.cloned, glowRatio)

  override def scaled(s: Float): Glow[T] = cloned.withShape(shape.scaled(s))

  override def drawShape(): Unit = {
    val step = -(ratioDifference / 20)

    (glowRatio to 1 by step).foreach(ratio => {
      val alpha = 255 * (glowRatio - ratio) / ratioDifference
      shape.scaled(ratio).withFill(fill.withAlpha(alpha)).draw()
    })

    shape.draw()
  }
}
object Glow {
  def apply[T <: Shape[T]](shape: Shape[T], glowRatio: Float)(implicit app: ScalessingApp): Glow[T] =
    new Glow[T](shape, glowRatio).withApp(app)
  def apply[T <: Shape[T]](shape: Shape[T], glowRatio: Double)(implicit app: ScalessingApp): Glow[T] =
    new Glow[T](shape, glowRatio.toFloat).withApp(app)
}