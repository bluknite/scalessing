package com.palebluespeck.scalessing.common

sealed trait Color {
  val r: Float
  val g: Float
  val b: Float

  def withAlpha(a: Float): Color
  def withAlpha(a: Double): Color = withAlpha(a.toFloat)
}
object Color {
  def apply(hex: String, alpha: Double): Color = {
    val (r, g, b) = hexToTriple(hex)
    Rgb(r, g, b, alpha)
  }

  def apply(hex: String): Color = Color(hex, 255)

  private def hexToTriple(hex: String): (Int, Int, Int) = {
    val pieces = hex.grouped(2).map(g => Integer.parseInt(g, 16)).toList
    val r::g::b::Nil = pieces
    (r, g, b)
  }
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
  val r: Float = gray
  val g: Float = gray
  val b: Float = gray

  override def withAlpha(a: Float): Color = Gray(gray, a)
}
object Gray {
  def apply(gray: Float): Gray = Gray(gray, 255)

  def apply(gray: Double, alpha: Double): Gray = Gray(gray.toFloat, alpha.toFloat)
  def apply(gray: Double): Gray = Gray(gray, 255)
}

object NoColor extends Color {
  override def withAlpha(a: Float): Color = ??? // not applicable
  override val r: Float = 0 // not applicable
  override val g: Float = 0 // not applicable
  override val b: Float = 0 // not applicable
}
