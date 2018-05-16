package com.palebluespeck.scalessing.charts

import com.palebluespeck.scalessing.ScalessingApp
import com.palebluespeck.scalessing.common.Color
import com.palebluespeck.scalessing.shapes.Circle
import processing.core.PConstants

class PieChart(var radius: Double, var colors: List[Color], var data: List[Double]) extends Chart[PieChart]{
  private var TwiceRadius: Float = (2 * radius).toFloat

  override def self: PieChart = this

  def withColors(colors: List[Color]): PieChart = {
    this.colors = colors
    self
  }

  def withData(data: List[Double]): PieChart = {
    this.data = data
    self
  }

  override def drawChart(): Unit = {
    if (data.isEmpty) {
      Circle(radius).withFill(Color("404040")).draw()
      app.textAlign(PConstants.CENTER, PConstants.CENTER)
      app.fill(Color("F2F2F2"))
      app.text("NO\nDATA", 0, 0)
    } else {
      var angle = -PConstants.HALF_PI
      val total = data.sum
      data.zip(colors).foreach(pair => {
        val (d, c) = pair
        val p = d / total
        val angleSize = (PConstants.TWO_PI * p).toFloat
        app.fill(c)
        app.arc(0, 0, TwiceRadius, TwiceRadius, angle, angle + angleSize, PConstants.PIE)
        angle = angle + angleSize
      })
    }
  }
}
object PieChart {
  def apply(radius: Double)(implicit app: ScalessingApp): PieChart = new PieChart(radius, List(), List()).withApp(app)
}