package com.palebluespeck.scalessing.common

sealed trait Color {
  def withAlpha(a: Float): Color
}

case class Rgb(r: Float, g: Float, b: Float, alpha: Float) extends Color {
  override def withAlpha(a: Float): Color = Rgb(r, g, b, a)
}
object Rgb {
  def apply(r: Float, g: Float, b: Float): Rgb = Rgb(r, g, b, 255)

  def apply(r: Double, g: Double, b: Double, alpha: Double): Rgb = Rgb(r.toFloat, g.toFloat, b.toFloat, alpha.toFloat)
  def apply(r: Double, g: Double, b: Double): Rgb = Rgb(r, g, b, 255)
}

case class Gray(gray: Float, alpha: Float) extends Color {
  override def withAlpha(a: Float): Color = Gray(gray, a)
}
object Gray {
  def apply(gray: Float): Gray = Gray(gray, 255)

  def apply(gray: Double, alpha: Double): Gray = Gray(gray.toFloat, alpha.toFloat)
  def apply(gray: Double): Gray = Gray(gray, 255)
}

object NoColor extends Color {
  override def withAlpha(a: Float): Color = ??? // not applicable
}
