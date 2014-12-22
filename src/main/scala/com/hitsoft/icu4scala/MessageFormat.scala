package com.hitsoft.icu4scala

import java.util.Locale

import com.ibm.icu.text.{MessageFormat => MF}
import scala.collection.JavaConversions._



object MessageFormat {

  def format(pattern: String, arguments: Map[String, Any], locale: Locale): String = {
    val args = arguments.map(item => item._1 -> item._2.asInstanceOf[Object]).toMap
    val mf = new MF(pattern, locale)
    mf.format(mapAsJavaMap[java.lang.String, java.lang.Object](args))
  }

  def format(pattern: String, arguments: Map[String, Any]): String = format(pattern, arguments, Locale.getDefault)

  def format(pattern: String, arguments: Any*): String = {
    val args = arguments.map(_.asInstanceOf[Object])
    val mf = new MF(pattern)
    mf.format(args.toArray)
  }

}