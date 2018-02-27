package com.palebluespeck.scalessing.common

case class Position(x: Float, y: Float) {
  def +(that: Position): Position = Position(this.x + that.x, this.y + that.y)
  def -(that: Position): Position = Position(this.x - that.x, this.y - that.y)

  def distanceTo(that: Position): Float = {
    val xDiff = this.x - that.x
    val yDiff = this.y - that.y
    val x2 = xDiff * xDiff
    val y2 = yDiff * yDiff
    math.sqrt(x2 + y2).toFloat
  }

  override def toString: String = f"($x%.3f, $y%.3f)"
}
object Position {
  def apply(x: Double, y: Double): Position = new Position(x.toFloat, y.toFloat)

  implicit def intPairToPosition(p: (Int, Int)): Position = Position(p._1, p._2)
  implicit def floatPairToPosition(p: (Float, Float)): Position = Position(p._1, p._2)
  implicit def doublePairToPosition(p: (Double, Double)): Position = Position(p._1, p._2)
}