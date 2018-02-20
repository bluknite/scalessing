package com.palebluespeck.scalessing

import processing.core.PApplet

import scala.reflect.runtime.universe._

abstract class ScalessingRunner[T <: ScalessingApp: TypeTag] {
  def main(args: Array[String]): Unit = {
    PApplet.main(typeTag[T].tpe.toString)
  }
}
