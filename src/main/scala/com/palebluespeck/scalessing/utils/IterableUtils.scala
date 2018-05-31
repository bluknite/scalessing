package com.palebluespeck.scalessing.utils

object IterableUtils {

  implicit class SeqEnhancers[A](seqA: Seq[A]) {
    def meld[B, T](seqB: Seq[B])(melder: (A, B) => T): Seq[T] =
      seqA.toStream.zip(seqB).map(entry => melder(entry._1, entry._2))

    def flatZip[B, C](seqB: Seq[B], seqC: Seq[C]): Seq[(A, B, C)] =
      seqA.zip(seqB).meld(seqC)((ab, c) => (ab._1, ab._2, c))

    def flatZip[B, C, D](seqB: Seq[B], seqC: Seq[C], seqD: Seq[D]): Seq[(A, B, C, D)] =
      seqA.flatZip(seqB, seqC).meld(seqD)((abc, d) => (abc._1, abc._2, abc._3, d))
  }
}