package com.palebluespeck.scalessing.shapes

import com.palebluespeck.scalessing.ScalessingApp
import com.palebluespeck.scalessing.common.Position
import processing.core.PConstants

class GlowCircle(var center: Position, var radius: Float, glowRatio: Float, override implicit val app: ScalessingApp)
  extends Shape[GlowCircle] {
  override def self: GlowCircle = this
  require(glowRatio >= 1, "Glow Ratio must be greater than or equal to 1")
  private val ratioDifference: Float = glowRatio - 1

  def setCenter(position: Position): GlowCircle = {
    center = position
    self
  }

  def setRadius(r: Float): GlowCircle = {
    radius = r
    self
  }

  override def drawShape(): Unit = {
    app.ellipseMode(PConstants.RADIUS)

    val step = -(ratioDifference / 20)

    (glowRatio to 1 by step).foreach(ratio => {
      val r = ratio * radius
      val alpha = 255 * (glowRatio - ratio) / ratioDifference
      Circle(center, r).withFill(fill.withAlpha(alpha)).draw()
    })

    Circle(center, radius).withFill(fill).draw()
  }
}
object GlowCircle {
  def apply(center: Position, radius: Float, glowRatio: Float)(implicit app: ScalessingApp): GlowCircle =
    new GlowCircle(center, radius, glowRatio, app)
  def apply(center: Position, radius: Double, glowRatio: Double)(implicit app: ScalessingApp): GlowCircle =
    new GlowCircle(center, radius.toFloat, glowRatio.toFloat, app)

}