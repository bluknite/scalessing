package com.palebluespeck.scalessing.test

import com.palebluespeck.scalessing.common._
import com.palebluespeck.scalessing.shapes._
import com.palebluespeck.scalessing.{ScalessingApp, ScalessingRunner}

class SolarEclipse extends ScalessingApp {
  implicit val app: ScalessingApp = this

  override def settings(): Unit = {
    fullScreen()
  }

  override def setup(): Unit = {
    noStroke()
  }

  val size: Double = 100

  private val sun = Glow(Circle(size).withFill(Rgb(255, 255, 180)), 1.5).withFill(Rgb(255, 255, 220)).withPosition((80, 80))
  private val moon = Circle(1.35 * size).withPosition((130, -20))

  val darkSkyDistance: Double = size/4
  val numberOfStars: Int = 1000

  lazy val starPositions: Seq[Position] = (1 to numberOfStars).map(_ => randomScreenPosition())
  lazy val starSizes: Seq[Float] = (1 to numberOfStars).map(_ => randomFloat(1, 2))
  lazy val stars: Seq[Circle] = starPositions.zip(starSizes).map(s => Circle(s._2).withPosition(s._1).withFill(Gray(200)))

  private def randomScreenPosition(): Position = (randomFloat(width), randomFloat(height))
  private def randomFloat(max: Float): Float = (math.random() * max).toFloat
  private def randomFloat(min: Float, max: Float): Float = min + randomFloat(max - min)

  override def draw(): Unit = {
    clear()

    val distance = math.max(sun.position.distanceTo(moon.position) - size/20, 0)
    lazy val distanceRatio = distance / darkSkyDistance
    val darken = if (distance > darkSkyDistance) 1 else distanceRatio

    //sky
    fill(Rgb(150 * darken, 200 * darken, 255 * darken))
    rect(0, 0, width, height)

    //stars
    if (distance < darkSkyDistance) {
      val alpha: Double = 255 * (1 - distanceRatio)
      stars.foreach(star => star.withFill(star.fill.withAlpha(alpha)).draw())
    }

    sun.draw()
    moon.withFill(Rgb(150 * darken, 200 * darken, 255 * darken)).draw()

    sun + (1, 1)
    moon + (0.9F, 1.2F)
  }
}

object SolarEclipse extends ScalessingRunner[SolarEclipse]