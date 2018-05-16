package com.palebluespeck.scalessing.charts

import com.palebluespeck.scalessing.ScalessingApp
import com.palebluespeck.scalessing.common.{Color, Position}
import com.palebluespeck.scalessing.shapes.Circle
import processing.core.PConstants

class DonutChart(var radius: Double,
                 holePercent: Double,
                 background: Color,
                 var title: Option[String],
                 var colors: List[Color],
                 var data: List[Double],
                 var labels: List[String]) extends Chart[DonutChart]{
  require(holePercent <= 1D)

  private val twiceRadius: Float = (2 * radius).toFloat
  private val innerRadius: Double = holePercent * radius

  override def self: DonutChart = this

  def withTitle(title: String): DonutChart = {
    this.title = Some(title)
    self
  }

  def withColors(colors: List[Color]): DonutChart = {
    this.colors = colors
    self
  }

  def withData(data: List[Double]): DonutChart = {
    this.data = data
    self
  }

  def withLabels(labels: List[String]): DonutChart = {
    this.labels = labels.map(_.toUpperCase)
    self
  }

  var multiplier: Double = 0
  var step: Double = 0.015
  val labelDistance = 10

  override def drawChart(): Unit = {
    if (data.isEmpty) {
      Circle(radius).withFill(Color("595959")).draw()
    } else {
      if (multiplier >= 1) multiplier = 1
      else multiplier = multiplier + step
      var startAngle = -PConstants.HALF_PI
      val total = data.sum
      data.zip(labels).zip(colors).foreach(entry => {
        val ((d, l), c) = entry
        if (d > 0) {
          val p = (d * multiplier) / total
          val angleSize = (PConstants.TWO_PI * p).toFloat
          app.fill(c)
          val endAngle = startAngle + angleSize
          app.arc(0, 0, twiceRadius, twiceRadius, startAngle, endAngle, PConstants.PIE)

          val labelAngle = startAngle + (angleSize / 2)
          app.rotate(labelAngle)
          Circle(3).withPosition(Position(radius, 0)).withFill(Color("A6A6A6")).draw()
          app.translate(Position(radius, 0))
          app.stroke(Color("A6A6A6"))
          app.line(0, 0, 50, 0)
          Circle(3).withPosition(Position(50, 0)).withFill(Color("A6A6A6")).draw()
          app.translate(Position(50 + labelDistance, 0))
          app.rotate(-labelAngle)
          app.fill(Color("A6A6A6"))
          app.textSize(9)
          if (labelAngle < PConstants.HALF_PI) {
            app.textAlign(PConstants.LEFT, PConstants.CENTER)
            app.text(l, 0, 0)
          }
          else {
            app.textAlign(PConstants.RIGHT, PConstants.CENTER)
            app.text(l, 0, 0)
          }
          app.rotate(labelAngle)
          app.translate(-Position(50 + labelDistance, 0))
          app.translate(-Position(radius, 0))
          app.rotate(-labelAngle)

          startAngle = endAngle
        }
      })
      Circle(innerRadius).withFill(background).draw()
    }
    if (title.isDefined) {
      app.textAlign(PConstants.CENTER, PConstants.CENTER)
      app.textSize(14)
      if (data.isEmpty) app.fill(Color("A6A6A6"))
      else app.fill(Color("F2F2F2"))
      app.text(title.get.toUpperCase, 0, 0)
    }
  }
}
object DonutChart {
  def apply(radius: Double, holePercent: Double, background: Color)(implicit app: ScalessingApp): DonutChart =
    new DonutChart(radius, holePercent, background, None, List(), List(), List()).withApp(app)
}