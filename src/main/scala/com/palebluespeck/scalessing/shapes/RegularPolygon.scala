package com.palebluespeck.scalessing.shapes

import com.palebluespeck.scalessing.ScalessingApp
import com.palebluespeck.scalessing.common._

object RegularPolygon {
  def apply(n: Int, side: Float)(implicit app: ScalessingApp): Polygon = {
    val α: Double = math.Pi / n
    val r: Double = side / (2 * math.sin(α))
    withRadius(n, r)
  }
  def apply(n: Int, side: Double)(implicit app: ScalessingApp): Polygon = RegularPolygon(n, side.toFloat)

  def withRadius(n: Int, radius: Float)(implicit app: ScalessingApp): Polygon = {
    val α: Double = math.Pi / n
    val offset: Double = if (n % 2 == 0) α else 0
    val vertices = (1 to n).map(i => {
      val Θ: Double = 2 * i * α + offset
      Position(radius * math.sin(Θ), -radius * math.cos(Θ))
    }).toList
    Polygon(vertices)
  }
  def withRadius(n: Int, radius: Double)(implicit app: ScalessingApp): Polygon = withRadius(n, radius.toFloat)

  def triangle(side: Float)(implicit app: ScalessingApp): Polygon = RegularPolygon(3, side)
  def triangle(side: Double)(implicit app: ScalessingApp): Polygon = RegularPolygon(3, side)
  def triangleWithRadius(radius: Float)(implicit app: ScalessingApp): Polygon = withRadius(3, radius)
  def triangleWithRadius(radius: Double)(implicit app: ScalessingApp): Polygon = withRadius(3, radius)

  def square(side: Float)(implicit app: ScalessingApp): Polygon = RegularPolygon(4, side)
  def square(side: Double)(implicit app: ScalessingApp): Polygon = RegularPolygon(4, side)
  def squareWithRadius(radius: Float)(implicit app: ScalessingApp): Polygon = withRadius(4, radius)
  def squareWithRadius(radius: Double)(implicit app: ScalessingApp): Polygon = withRadius(4, radius)

  def pentagon(side: Float)(implicit app: ScalessingApp): Polygon = RegularPolygon(5, side)
  def pentagon(side: Double)(implicit app: ScalessingApp): Polygon = RegularPolygon(5, side)
  def pentagonWithRadius(radius: Float)(implicit app: ScalessingApp): Polygon = withRadius(5, radius)
  def pentagonWithRadius(radius: Double)(implicit app: ScalessingApp): Polygon = withRadius(5, radius)

  def hexagon(side: Float)(implicit app: ScalessingApp): Polygon = RegularPolygon(6, side)
  def hexagon(side: Double)(implicit app: ScalessingApp): Polygon = RegularPolygon(6, side)
  def hexagonWithRadius(radius: Float)(implicit app: ScalessingApp): Polygon = withRadius(6, radius)
  def hexagonWithRadius(radius: Double)(implicit app: ScalessingApp): Polygon = withRadius(6, radius)

  def heptagon(side: Float)(implicit app: ScalessingApp): Polygon = RegularPolygon(7, side)
  def heptagon(side: Double)(implicit app: ScalessingApp): Polygon = RegularPolygon(7, side)
  def heptagonWithRadius(radius: Float)(implicit app: ScalessingApp): Polygon = withRadius(7, radius)
  def heptagonWithRadius(radius: Double)(implicit app: ScalessingApp): Polygon = withRadius(7, radius)

  def octagon(side: Float)(implicit app: ScalessingApp): Polygon = RegularPolygon(8, side)
  def octagon(side: Double)(implicit app: ScalessingApp): Polygon = RegularPolygon(8, side)
  def octagonWithRadius(radius: Float)(implicit app: ScalessingApp): Polygon = withRadius(8, radius)
  def octagonWithRadius(radius: Double)(implicit app: ScalessingApp): Polygon = withRadius(8, radius)

  def nonagon(side: Float)(implicit app: ScalessingApp): Polygon = RegularPolygon(9, side)
  def nonagon(side: Double)(implicit app: ScalessingApp): Polygon = RegularPolygon(9, side)
  def nonagonWithRadius(radius: Float)(implicit app: ScalessingApp): Polygon = withRadius(9, radius)
  def nonagonWithRadius(radius: Double)(implicit app: ScalessingApp): Polygon = withRadius(9, radius)

  def decagon(side: Float)(implicit app: ScalessingApp): Polygon = RegularPolygon(10, side)
  def decagon(side: Double)(implicit app: ScalessingApp): Polygon = RegularPolygon(10, side)
  def decagonWithRadius(radius: Float)(implicit app: ScalessingApp): Polygon = withRadius(10, radius)
  def decagonWithRadius(radius: Double)(implicit app: ScalessingApp): Polygon = withRadius(10, radius)
}
