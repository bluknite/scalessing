package com.palebluespeck.scalessing.test

import com.palebluespeck.scalessing.charts.DonutChart
import com.palebluespeck.scalessing.common.{Color, Position}
import com.palebluespeck.scalessing.{ScalessingApp, ScalessingRunner}

class Charts extends ScalessingApp {
  implicit val app: ScalessingApp = this

  override def settings(): Unit = fullScreen()

  override def setup(): Unit = noStroke()

  lazy val donuts: List[DonutChart] = List(
    chart(width / 5).withTitle("category\n1").withData(List(3.333333333, 1.666666667, 0)),
    chart(2 * (width / 5)).withTitle("category\nwithout\ndata"),
    chart(3 * (width / 5)).withTitle("what\nis\nthis?").withData(List(2.166666667, 2.833333333, 3.14)),
    chart(4 * (width / 5)).withData(List(0.1808219178, 0.2438356164, 4.575342466))
  )

  override def draw(): Unit = {
    clear()
    background(Color("666666"))
    donuts.foreach(_.draw())
  }

  def chart(x: Double): DonutChart = {
    DonutChart(100, 0.85, Color("666666"))
      .withPosition(Position(x, height / 2))
      .withColors(List(Color("6aa84f"), Color("cc4125"), Color("434343")))
      .withLabels(List("green", "red", "no data"))
  }
}

object Charts extends ScalessingRunner[Charts]